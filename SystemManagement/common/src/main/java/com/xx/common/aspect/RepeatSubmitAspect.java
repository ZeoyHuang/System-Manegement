package com.xx.common.aspect;

import com.xx.common.annotation.RepeatSubmit;
import com.xx.common.response.CommonResponse;
import com.xx.tools.utils.IpAdrressUtils;
import com.xx.tools.utils.RedisUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class RepeatSubmitAspect {
    /** 日志对象 */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RedisUtils redisUtils;

    /**
     *  定义切入点
     */
    @Pointcut("@annotation(repeatSubmit)")
    public void pointNoRepeatSubmit(RepeatSubmit repeatSubmit) {
    }

    /**
     *  环绕通知, 围绕着方法执行
     * @param joinPoint 连接点
     * @param repeatSubmit 重复提交注解
     * @return 结果集
     * @throws Throwable 异常处理
     */
    @Around(value = "pointNoRepeatSubmit(repeatSubmit)", argNames = "joinPoint,repeatSubmit")
    public Object repeatSubmit(ProceedingJoinPoint joinPoint, RepeatSubmit repeatSubmit) throws Throwable {
        logger.info("-----------防止重复提交开始----------");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = (HttpServletRequest) attributes.getRequest();
        // 这里是唯一标识 根据情况而定
        StringBuilder key = new StringBuilder();
        // 防重提交类型
        String limitType = repeatSubmit.limitType().name();
        // 根据防重提交类型处理 默认防重提交是方法参数
        if (limitType.equalsIgnoreCase(RepeatSubmit.Type.PARAM.name())) {
            // 获取用户电脑信息
            key.append(request.getHeader("User-Agent"));
            // 获取请求IP地址
            key.append(IpAdrressUtils.getIpAdrress(request));
            key.append("-");
            // 获取请求Url
            key.append(request.getRequestURI());
        } else {
            // 获取请求里的token
            String authorization = request.getHeader("Authorization");
            key.append(authorization);
            key.append("-");
            // 获取请求Url
            key.append(request.getRequestURI());
        }
        logger.info("防止重复提交Key:{}",key.toString());
        // 如果缓存中有这个IP地址，URL视为重复提交
        int maxAccesses = 10;
        int timeWindow = 60;

        long accessCount = redisUtils.getAccessCount(key.toString());
        long remainingTime = redisUtils.getRemainingTime(key.toString());

        if (accessCount < maxAccesses) {
            // Increment the access count
            redisUtils.set(key.toString(), accessCount + 1);
            // Set the time window if it's not already set
            if (remainingTime <= 0) {
                redisUtils.setWithTime(key.toString(), accessCount + 1, timeWindow);
            }
            // Proceed with the method execution
            Object o = joinPoint.proceed();
            // Return the result
            return o;
        } else {
            // Return error response due to exceeding access limit
            String repeatMsg = "请勿重复提交或者操作过于频繁！ 请在" + remainingTime + "秒后重试";
            boolean flag = false;
            logger.info(repeatMsg);
            CommonResponse<String> response = new CommonResponse<>(
                    500, repeatMsg, flag);
            return response;
        }
    }
}
