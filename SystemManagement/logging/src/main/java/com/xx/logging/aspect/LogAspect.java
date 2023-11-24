package com.xx.logging.aspect;

import cn.hutool.http.HttpUtil;
import com.xx.logging.annotation.OpLog;
import com.xx.logging.entity.SysLog;
import com.xx.logging.service.SysLogService;
import com.xx.tools.utils.IpUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 切面处理类，操作日志异常日志记录处理
 *
 * @author wu
 * @date 2019/03/21
 */
@Aspect
@Component
public class LogAspect {

    /**
     * 计算操作消耗时间
     */
    private static final ThreadLocal<Long> TIME_THREADLOCAL = new NamedThreadLocal<>("Cost Time");

    @Autowired
    private SysLogService sysLogService;

    /**
     * 处理请求前执行
     */
    @Before(value = "@annotation(opLog)")
    public void boBefore(JoinPoint joinPoint, OpLog opLog) {
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    /**
     * 记录正常日志
     * @param joinPoint
     * @param opLog
     */
    @AfterReturning(pointcut = "@annotation(opLog)")
    public void saveSysLog(JoinPoint joinPoint, OpLog opLog) {
        handleLog(joinPoint,opLog,null);
    }

    /**
     * 记录异常日志
     * @param joinPoint
     * @param opLog
     * @param e
     */
    @AfterThrowing(value = "@annotation(opLog)",throwing = "e")
    public void saveExceptionLog(JoinPoint joinPoint, OpLog opLog,Exception e) {
        handleLog(joinPoint,opLog,e);
    }

    private void handleLog(JoinPoint joinPoint, OpLog opLog, Exception e) {
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);

        try {
            // 从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            // 获取切入点所在的方法
            Method method = signature.getMethod();
            // 获取操作
//            OpLog opLog = method.getAnnotation(OpLog.class);
            SysLog sysLog = new SysLog();
            if (opLog != null) {
                String operType = opLog.opType();
                String operDesc = opLog.opDesc();
                sysLog.setLogType(operType); // 操作类型
                sysLog.setDescription(operDesc); // 操作描述
            }
            // 获取请求的类名
            String className = joinPoint.getTarget().getClass().getName();
            // 获取请求的方法名
            String methodName = method.getName();
            methodName = className + "." + methodName;
            sysLog.setMethod(methodName); // 请求方法

//            // 请求的参数
//            Map<String, String> rtnMap = HttpUtil.toParams(request.getParameterMap());
//            // 将参数所在的数组转换成json
//            String params = JSON.toJSONString(rtnMap);

            sysLog.setParams(HttpUtil.toParams(request.getParameterMap())); // 请求参数
            sysLog.setRequestIp(IpUtils.getHostIp());//请求IP
            sysLog.setTime(System.currentTimeMillis() - TIME_THREADLOCAL.get()); // 请求时间
//            sysLog.setUsername(UserShiroUtil.getCurrentUserName()); // 请求用户名称
            sysLog.setUsername("admin"); // 请求用户名称
            sysLog.setAddress(request.getRequestURI()); // 请求URI
            sysLog.setBrowser(request.getHeader(HttpHeaders.USER_AGENT)); // 请求URI
            sysLog.setCreateTime(LocalDateTime.now()); // 创建时间
            if (e != null) {
                sysLog.setExceptionDetail(stackTraceToString(e.getClass().getName(), e.getMessage(), e.getStackTrace()));
            }
            sysLogService.asyncSaveLog(sysLog);
        } catch (Exception exp) {
            exp.printStackTrace();
        } finally {
            TIME_THREADLOCAL.remove();
        }
    }

    /**
     * 转换request 请求参数
     *
     * @param paramMap request获取的参数数组
     */
    public Map<String, String> converMap(Map<String, String[]> paramMap) {
        Map<String, String> rtnMap = new HashMap<>();
        for (String key : paramMap.keySet()) {
            rtnMap.put(key, paramMap.get(key)[0]);
        }
        return rtnMap;
    }

    /**
     * 转换异常信息为字符串
     *
     * @param exceptionName    异常名称
     * @param exceptionMessage 异常信息
     * @param elements         堆栈信息
     */
    public String stackTraceToString(String exceptionName, String exceptionMessage, StackTraceElement[] elements) {
        StringBuilder strbuff = new StringBuilder();
        for (StackTraceElement stet : elements) {
            strbuff.append(stet).append("\n");
        }
        return exceptionName + ":" + exceptionMessage + "\n\t" + strbuff;
    }
}