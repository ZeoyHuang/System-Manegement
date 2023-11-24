package com.xx.system.security.config;

import com.xx.system.security.UserAuthenticationProvider;
import com.xx.system.security.UserPermissionEvaluator;
import com.xx.system.security.handler.UserAuthAccessDeniedHandler;
import com.xx.system.security.handler.UserAuthenticationEntryPointHandler;
import com.xx.system.security.handler.UserLoginFailureHandler;
import com.xx.system.security.handler.UserLoginSuccessHandler;
import com.xx.system.security.handler.UserLogoutSuccessHandler;
import com.xx.system.security.jwt.JWTAuthenticationTokenFilter;
import com.xx.tools.utils.RsaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * SpringSecurity配置类
 * @Author Sans
 * @CreateTime 2019/10/1 9:40
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //开启权限注解,默认是关闭的
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 放行的路径
     */
    private final String[] PATH_RELEASE = {
            "/static/**",
            "/upload/**",
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html/**",
            "/api/register",
            "/api/login",
            "/api/logout",
            "/api/test1",
            "/api/test2",


            "/api/getVerifyCode"
    };

    /**
     * 自定义登录成功处理器
     */
    @Autowired
    private UserLoginSuccessHandler userLoginSuccessHandler;

    /**
     * 自定义登录失败处理器
     */
    @Autowired
    private UserLoginFailureHandler userLoginFailureHandler;

    /**
     * 自定义注销成功处理器
     */
    @Autowired
    private UserLogoutSuccessHandler userLogoutSuccessHandler;

    /**
     * 自定义暂无权限处理器
     */
    @Autowired
    private UserAuthAccessDeniedHandler userAuthAccessDeniedHandler;

    /**
     * 自定义未登录的处理器
     */
    @Autowired
    private UserAuthenticationEntryPointHandler userAuthenticationEntryPointHandler;

    /**
     * 自定义登录逻辑验证器
     */
    @Autowired
    private UserAuthenticationProvider userAuthenticationProvider;

    /**
     * 加密方式
     * @Author Sans
     * @CreateTime 2019/10/1 14:00
     */
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder(){
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public RsaPasswordEncoder rsaPasswordEncoder(){
        return new RsaPasswordEncoder();
    }

    @Autowired
    private ValidateCodeFilter validateCodeFilter;

    /**
     * 注入自定义PermissionEvaluator
     */
    @Bean
    public DefaultWebSecurityExpressionHandler userSecurityExpressionHandler(){
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setPermissionEvaluator(new UserPermissionEvaluator());
        return handler;
    }

    /**
     * 配置登录验证逻辑
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth){
        //这里可启用我们自己的登陆验证逻辑
        auth.authenticationProvider(userAuthenticationProvider);
    }


    /**
     * 配置security的控制逻辑
     * @Author Sans
     * @CreateTime 2019/10/1 16:56
     * @Param  http 请求
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 不进行权限验证的请求或资源(从配置文件中读取)
               .antMatchers(PATH_RELEASE).permitAll()
                // 其他的需要登陆后才能访问
                .anyRequest().authenticated()
                .and()
                // 配置未登录自定义处理类
                .httpBasic().authenticationEntryPoint(userAuthenticationEntryPointHandler)
                .and()
                // 配置登录地址
                .formLogin()
                .loginProcessingUrl("/api/login")
                // 配置登录成功自定义处理类
                .successHandler(userLoginSuccessHandler)
                // 配置登录失败自定义处理类
                .failureHandler(userLoginFailureHandler)
                .and()
                // 配置登出地址
                .logout()
                .logoutUrl("/api/logout")
                // 配置用户登出自定义处理类
                .logoutSuccessHandler(userLogoutSuccessHandler)
                .and()
                // 配置没有权限自定义处理类
                .exceptionHandling().accessDeniedHandler(userAuthAccessDeniedHandler)
                .and()
                // 开启跨域
                .cors()
                .and()
                // 取消跨站请求伪造防护
                .csrf().disable();

        // 基于Token不需要session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 禁用缓存
        http.headers().cacheControl();
        // 添加JWT过滤器
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilter(new JWTAuthenticationTokenFilter(authenticationManager()));

    }
}