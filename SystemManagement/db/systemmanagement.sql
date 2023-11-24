/*
 Navicat Premium Data Transfer

 Source Server         : vm
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : localhost:3306
 Source Schema         : test001

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 13/08/2023 13:15:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` bigint(0) NOT NULL COMMENT 'ID',
  `pid` bigint(0) NULL DEFAULT NULL COMMENT '上级部门',
  `sub_count` int(0) NULL DEFAULT NULL COMMENT '子部门数目',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `dept_sort` int(0) NULL DEFAULT NULL COMMENT '排序',
  `enabled` bit(1) NOT NULL COMMENT '状态',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (2, 7, 1, '研发部', 3, b'1', 'admin', 'admin', '2019-03-25 09:15:32', '2020-08-02 14:48:47');
INSERT INTO `sys_dept` VALUES (5, 7, 0, '运维部', 4, b'1', 'admin', 'admin', '2019-03-25 09:20:44', '2020-05-17 14:27:27');
INSERT INTO `sys_dept` VALUES (6, 8, 0, '测试部', 6, b'1', 'admin', 'admin', '2019-03-25 09:52:18', '2020-06-08 11:59:21');
INSERT INTO `sys_dept` VALUES (7, NULL, 2, '华南分部', 0, b'1', 'admin', 'admin', '2019-03-25 11:04:50', '2020-06-08 12:08:56');
INSERT INTO `sys_dept` VALUES (8, NULL, 2, '华北分部', 1, b'1', 'admin', 'admin', '2019-03-25 11:04:53', '2020-05-14 12:54:00');
INSERT INTO `sys_dept` VALUES (15, 8, 0, 'UI部门', 7, b'1', 'admin', 'admin', '2020-05-13 22:56:53', '2020-05-14 12:54:13');
INSERT INTO `sys_dept` VALUES (17, 2, 0, '研发一组', 999, b'1', 'admin', 'admin', '2020-08-02 14:49:07', '2020-08-02 14:49:07');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `dict_id` bigint(0) NOT NULL COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dict_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (1, 'user_status', '用户状态', NULL, NULL, '2019-10-27 20:31:36', NULL);
INSERT INTO `sys_dict` VALUES (4, 'dept_status', '部门状态', NULL, NULL, '2019-10-27 20:31:36', NULL);
INSERT INTO `sys_dict` VALUES (5, 'job_status', '岗位状态', NULL, NULL, '2019-10-27 20:31:36', NULL);

-- ----------------------------
-- Table structure for sys_dict_detail
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_detail`;
CREATE TABLE `sys_dict_detail`  (
  `detail_id` bigint(0) NOT NULL COMMENT 'ID',
  `dict_id` bigint(0) NULL DEFAULT NULL COMMENT '字典id',
  `label` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典标签',
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典值',
  `dict_sort` int(0) NULL DEFAULT NULL COMMENT '排序',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新者\n',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`detail_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_detail
-- ----------------------------
INSERT INTO `sys_dict_detail` VALUES (1, 1, '激活', 'true', 1, NULL, NULL, '2019-10-27 20:31:36', NULL);
INSERT INTO `sys_dict_detail` VALUES (2, 1, '禁用', 'false', 2, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_detail` VALUES (3, 4, '启用', 'true', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_detail` VALUES (4, 4, '停用', 'false', 2, NULL, NULL, '2019-10-27 20:31:36', NULL);
INSERT INTO `sys_dict_detail` VALUES (5, 5, '启用', 'true', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_detail` VALUES (6, 5, '停用', 'false', 2, NULL, NULL, '2019-10-27 20:31:36', NULL);

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job`  (
  `job_id` bit(1) NOT NULL COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '岗位名称',
  `enabled` bit(1) NOT NULL COMMENT '岗位状态',
  `job_sort` int(0) NULL DEFAULT NULL COMMENT '排序',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`job_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `log_id` bigint(0) NOT NULL COMMENT 'ID',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `log_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `params` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `request_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `time` bigint(0) NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `browser` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `exception_detail` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`log_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES (1690391246487363585, '添加测试', 'ADD', 'com.xx.system.controller.TestController.test', 'msg=%5Bok%5D', '192.168.31.167', 33, 'admin', '/api/test', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36', NULL, '2023-08-12 23:54:14');
INSERT INTO `sys_log` VALUES (1690391815125987330, '添加测试', 'ADD', 'com.xx.system.controller.TestController.test', 'msg=%5Bok%5D', '192.168.31.167', 37, 'admin', '/api/test', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36', 'java.lang.ArithmeticException:/ by zero\n	com.xx.system.controller.TestController.test(TestController.java:23)\ncom.xx.system.controller.TestController$$FastClassBySpringCGLIB$$9edb0ec2.invoke(<generated>)\norg.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\norg.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:793)\norg.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\norg.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:763)\norg.springframework.aop.aspectj.AspectJAfterThrowingAdvice.invoke(AspectJAfterThrowingAdvice.java:64)\norg.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:175)\norg.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:763)\norg.springframework.aop.framework.adapter.AfterReturningAdviceInterceptor.invoke(AfterReturningAdviceInterceptor.java:57)\norg.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:175)\norg.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:763)\norg.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor.invoke(MethodBeforeAdviceInterceptor.java:58)\norg.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:175)\norg.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:763)\norg.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:97)\norg.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\norg.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:763)\norg.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:708)\ncom.xx.system.controller.TestController$$EnhancerBySpringCGLIB$$c144d5e7.test(<generated>)\nsun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\nsun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\nsun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\njava.lang.reflect.Method.invoke(Method.java:498)\norg.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:205)\norg.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:150)\norg.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:117)\norg.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:895)\norg.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:808)\norg.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\norg.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1072)\norg.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:965)\norg.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006)\norg.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:898)\njavax.servlet.http.HttpServlet.service(HttpServlet.java:529)\norg.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:883)\njavax.servlet.http.HttpServlet.service(HttpServlet.java:623)\norg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:209)\norg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)\norg.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:51)\norg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:178)\norg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)\norg.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:327)\norg.springframework.security.web.access.intercept.FilterSecurityInterceptor.invoke(FilterSecurityInterceptor.java:115)\norg.springframework.security.web.access.intercept.FilterSecurityInterceptor.doFilter(FilterSecurityInterceptor.java:81)\norg.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\norg.springframework.security.web.access.ExceptionTranslationFilter.doFilter(ExceptionTranslationFilter.java:121)\norg.springframework.security.web.access.ExceptionTranslationFilter.doFilter(ExceptionTranslationFilter.java:115)\norg.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\norg.springframework.security.web.session.SessionManagementFilter.doFilter(SessionManagementFilter.java:126)\norg.springframework.security.web.session.SessionManagementFilter.doFilter(SessionManagementFilter.java:81)\norg.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\norg.springframework.security.web.authentication.AnonymousAuthenticationFilter.doFilter(AnonymousAuthenticationFilter.java:105)\norg.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\norg.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter.doFilter(SecurityContextHolderAwareRequestFilter.java:149)\norg.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\norg.springframework.security.web.savedrequest.RequestCacheAwareFilter.doFilter(RequestCacheAwareFilter.java:63)\norg.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\ncom.xx.system.auth.filter.JwtAuthorizationFilter.doFilterInternal(JwtAuthorizationFilter.java:36)\norg.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\norg.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\norg.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter.doFilter(AbstractAuthenticationProcessingFilter.java:218)\norg.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter.doFilter(AbstractAuthenticationProcessingFilter.java:212)\norg.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\norg.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter.doFilter(AbstractAuthenticationProcessingFilter.java:218)\norg.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter.doFilter(AbstractAuthenticationProcessingFilter.java:212)\norg.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\norg.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:103)\norg.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:89)\norg.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\norg.springframework.web.filter.CorsFilter.doFilterInternal(CorsFilter.java:91)\norg.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\norg.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\norg.springframework.security.web.header.HeaderWriterFilter.doHeadersAfter(HeaderWriterFilter.java:90)\norg.springframework.security.web.header.HeaderWriterFilter.doFilterInternal(HeaderWriterFilter.java:75)\norg.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\norg.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\norg.springframework.security.web.context.SecurityContextPersistenceFilter.doFilter(SecurityContextPersistenceFilter.java:110)\norg.springframework.security.web.context.SecurityContextPersistenceFilter.doFilter(SecurityContextPersistenceFilter.java:80)\norg.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\norg.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter.doFilterInternal(WebAsyncManagerIntegrationFilter.java:55)\norg.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\norg.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\norg.springframework.security.web.FilterChainProxy.doFilterInternal(FilterChainProxy.java:211)\norg.springframework.security.web.FilterChainProxy.doFilter(FilterChainProxy.java:183)\norg.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:354)\norg.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:267)\norg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:178)\norg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)\norg.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)\norg.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\norg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:178)\norg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)\norg.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)\norg.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\norg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:178)\norg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)\norg.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)\norg.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\norg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:178)\norg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)\norg.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:167)\norg.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:90)\norg.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:481)\norg.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:130)\norg.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:93)\norg.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)\norg.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)\norg.apache.coyote.http11.Http11Processor.service(Http11Processor.java:390)\norg.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:63)\norg.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:926)\norg.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1791)\norg.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:52)\norg.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1191)\norg.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659)\norg.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\njava.lang.Thread.run(Thread.java:750)\n', '2023-08-12 23:56:29');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint(0) NOT NULL COMMENT 'ID',
  `pid` bigint(0) NULL DEFAULT NULL COMMENT '上级菜单ID',
  `sub_count` int(0) NULL DEFAULT NULL COMMENT '子菜单数目',
  `type` int(0) NULL DEFAULT NULL COMMENT '菜单类型',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单标题',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '组件名称',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '组件',
  `menu_sort` int(0) NULL DEFAULT NULL COMMENT '排序',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图标',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '链接地址',
  `i_frame` bit(1) NULL DEFAULT NULL COMMENT '是否外链',
  `cache` bit(1) NULL DEFAULT NULL COMMENT '缓存',
  `hidden` bit(1) NULL DEFAULT NULL COMMENT '隐藏',
  `permission` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, NULL, 7, 0, '系统管理', NULL, NULL, 1, 'system', 'system', b'0', b'0', b'0', NULL, NULL, NULL, '2018-12-18 15:11:29', NULL);
INSERT INTO `sys_menu` VALUES (2, 1, 3, 1, '用户管理', 'User', 'system/user/index', 2, 'peoples', 'user', b'0', b'0', b'0', 'user:list', NULL, NULL, '2018-12-18 15:14:44', NULL);
INSERT INTO `sys_menu` VALUES (3, 1, 3, 1, '角色管理', 'Role', 'system/role/index', 3, 'role', 'role', b'0', b'0', b'0', 'roles:list', NULL, NULL, '2018-12-18 15:16:07', NULL);
INSERT INTO `sys_menu` VALUES (5, 1, 3, 1, '菜单管理', 'Menu', 'system/menu/index', 5, 'menu', 'menu', b'0', b'0', b'0', 'menu:list', NULL, NULL, '2018-12-18 15:17:28', NULL);
INSERT INTO `sys_menu` VALUES (6, NULL, 5, 0, '系统监控', NULL, NULL, 10, 'monitor', 'monitor', b'0', b'0', b'0', NULL, NULL, NULL, '2018-12-18 15:17:48', NULL);
INSERT INTO `sys_menu` VALUES (7, 6, 0, 1, '操作日志', 'Log', 'monitor/log/index', 11, 'log', 'logs', b'0', b'1', b'0', NULL, NULL, 'admin', '2018-12-18 15:18:26', '2020-06-06 13:11:57');
INSERT INTO `sys_menu` VALUES (9, 6, 0, 1, 'SQL监控', 'Sql', 'monitor/sql/index', 18, 'sqlMonitor', 'druid', b'0', b'0', b'0', NULL, NULL, NULL, '2018-12-18 15:19:34', NULL);
INSERT INTO `sys_menu` VALUES (10, NULL, 5, 0, '组件管理', NULL, NULL, 50, 'zujian', 'components', b'0', b'0', b'0', NULL, NULL, NULL, '2018-12-19 13:38:16', NULL);
INSERT INTO `sys_menu` VALUES (11, 10, 0, 1, '图标库', 'Icons', 'components/icons/index', 51, 'icon', 'icon', b'0', b'0', b'0', NULL, NULL, NULL, '2018-12-19 13:38:49', NULL);
INSERT INTO `sys_menu` VALUES (14, 36, 0, 1, '邮件工具', 'Email', 'tools/email/index', 35, 'email', 'email', b'0', b'0', b'0', NULL, NULL, NULL, '2018-12-27 10:13:09', NULL);
INSERT INTO `sys_menu` VALUES (15, 10, 0, 1, '富文本', 'Editor', 'components/Editor', 52, 'fwb', 'tinymce', b'0', b'0', b'0', NULL, NULL, NULL, '2018-12-27 11:58:25', NULL);
INSERT INTO `sys_menu` VALUES (18, 36, 3, 1, '存储管理', 'Storage', 'tools/storage/index', 34, 'qiniu', 'storage', b'0', b'0', b'0', 'storage:list', NULL, NULL, '2018-12-31 11:12:15', NULL);
INSERT INTO `sys_menu` VALUES (19, 36, 0, 1, '支付宝工具', 'AliPay', 'tools/aliPay/index', 37, 'alipay', 'aliPay', b'0', b'0', b'0', NULL, NULL, NULL, '2018-12-31 14:52:38', NULL);
INSERT INTO `sys_menu` VALUES (21, NULL, 2, 0, '多级菜单', NULL, '', 900, 'menu', 'nested', b'0', b'0', b'0', NULL, NULL, 'admin', '2019-01-04 16:22:03', '2020-06-21 17:27:35');
INSERT INTO `sys_menu` VALUES (22, 21, 2, 0, '二级菜单1', NULL, '', 999, 'menu', 'menu1', b'0', b'0', b'0', NULL, NULL, 'admin', '2019-01-04 16:23:29', '2020-06-21 17:27:20');
INSERT INTO `sys_menu` VALUES (23, 21, 0, 1, '二级菜单2', NULL, 'nested/menu2/index', 999, 'menu', 'menu2', b'0', b'0', b'0', NULL, NULL, NULL, '2019-01-04 16:23:57', NULL);
INSERT INTO `sys_menu` VALUES (24, 22, 0, 1, '三级菜单1', 'Test', 'nested/menu1/menu1-1', 999, 'menu', 'menu1-1', b'0', b'0', b'0', NULL, NULL, NULL, '2019-01-04 16:24:48', NULL);
INSERT INTO `sys_menu` VALUES (27, 22, 0, 1, '三级菜单2', NULL, 'nested/menu1/menu1-2', 999, 'menu', 'menu1-2', b'0', b'0', b'0', NULL, NULL, NULL, '2019-01-07 17:27:32', NULL);
INSERT INTO `sys_menu` VALUES (28, 1, 3, 1, '任务调度', 'Timing', 'system/timing/index', 999, 'timing', 'timing', b'0', b'0', b'0', 'timing:list', NULL, NULL, '2019-01-07 20:34:40', NULL);
INSERT INTO `sys_menu` VALUES (30, 36, 0, 1, '代码生成', 'GeneratorIndex', 'generator/index', 32, 'dev', 'generator', b'0', b'1', b'0', NULL, NULL, NULL, '2019-01-11 15:45:55', NULL);
INSERT INTO `sys_menu` VALUES (32, 6, 0, 1, '异常日志', 'ErrorLog', 'monitor/log/errorLog', 12, 'error', 'errorLog', b'0', b'0', b'0', NULL, NULL, NULL, '2019-01-13 13:49:03', NULL);
INSERT INTO `sys_menu` VALUES (33, 10, 0, 1, 'Markdown', 'Markdown', 'components/MarkDown', 53, 'markdown', 'markdown', b'0', b'0', b'0', NULL, NULL, NULL, '2019-03-08 13:46:44', NULL);
INSERT INTO `sys_menu` VALUES (34, 10, 0, 1, 'Yaml编辑器', 'YamlEdit', 'components/YamlEdit', 54, 'dev', 'yaml', b'0', b'0', b'0', NULL, NULL, NULL, '2019-03-08 15:49:40', NULL);
INSERT INTO `sys_menu` VALUES (35, 1, 3, 1, '部门管理', 'Dept', 'system/dept/index', 6, 'dept', 'dept', b'0', b'0', b'0', 'dept:list', NULL, NULL, '2019-03-25 09:46:00', NULL);
INSERT INTO `sys_menu` VALUES (36, NULL, 7, 0, '系统工具', NULL, '', 30, 'sys-tools', 'sys-tools', b'0', b'0', b'0', NULL, NULL, NULL, '2019-03-29 10:57:35', NULL);
INSERT INTO `sys_menu` VALUES (37, 1, 3, 1, '岗位管理', 'Job', 'system/job/index', 7, 'Steve-Jobs', 'job', b'0', b'0', b'0', 'job:list', NULL, NULL, '2019-03-29 13:51:18', NULL);
INSERT INTO `sys_menu` VALUES (38, 36, 0, 1, '接口文档', 'Swagger', 'tools/swagger/index', 36, 'swagger', 'swagger2', b'0', b'0', b'0', NULL, NULL, NULL, '2019-03-29 19:57:53', NULL);
INSERT INTO `sys_menu` VALUES (39, 1, 3, 1, '字典管理', 'Dict', 'system/dict/index', 8, 'dictionary', 'dict', b'0', b'0', b'0', 'dict:list', NULL, NULL, '2019-04-10 11:49:04', NULL);
INSERT INTO `sys_menu` VALUES (41, 6, 0, 1, '在线用户', 'OnlineUser', 'monitor/online/index', 10, 'Steve-Jobs', 'online', b'0', b'0', b'0', NULL, NULL, NULL, '2019-10-26 22:08:43', NULL);
INSERT INTO `sys_menu` VALUES (44, 2, 0, 2, '用户新增', NULL, '', 2, '', '', b'0', b'0', b'0', 'user:add', NULL, NULL, '2019-10-29 10:59:46', NULL);
INSERT INTO `sys_menu` VALUES (45, 2, 0, 2, '用户编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'user:edit', NULL, NULL, '2019-10-29 11:00:08', NULL);
INSERT INTO `sys_menu` VALUES (46, 2, 0, 2, '用户删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'user:del', NULL, NULL, '2019-10-29 11:00:23', NULL);
INSERT INTO `sys_menu` VALUES (48, 3, 0, 2, '角色创建', NULL, '', 2, '', '', b'0', b'0', b'0', 'roles:add', NULL, NULL, '2019-10-29 12:45:34', NULL);
INSERT INTO `sys_menu` VALUES (49, 3, 0, 2, '角色修改', NULL, '', 3, '', '', b'0', b'0', b'0', 'roles:edit', NULL, NULL, '2019-10-29 12:46:16', NULL);
INSERT INTO `sys_menu` VALUES (50, 3, 0, 2, '角色删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'roles:del', NULL, NULL, '2019-10-29 12:46:51', NULL);
INSERT INTO `sys_menu` VALUES (52, 5, 0, 2, '菜单新增', NULL, '', 2, '', '', b'0', b'0', b'0', 'menu:add', NULL, NULL, '2019-10-29 12:55:07', NULL);
INSERT INTO `sys_menu` VALUES (53, 5, 0, 2, '菜单编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'menu:edit', NULL, NULL, '2019-10-29 12:55:40', NULL);
INSERT INTO `sys_menu` VALUES (54, 5, 0, 2, '菜单删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'menu:del', NULL, NULL, '2019-10-29 12:56:00', NULL);
INSERT INTO `sys_menu` VALUES (56, 35, 0, 2, '部门新增', NULL, '', 2, '', '', b'0', b'0', b'0', 'dept:add', NULL, NULL, '2019-10-29 12:57:09', NULL);
INSERT INTO `sys_menu` VALUES (57, 35, 0, 2, '部门编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'dept:edit', NULL, NULL, '2019-10-29 12:57:27', NULL);
INSERT INTO `sys_menu` VALUES (58, 35, 0, 2, '部门删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'dept:del', NULL, NULL, '2019-10-29 12:57:41', NULL);
INSERT INTO `sys_menu` VALUES (60, 37, 0, 2, '岗位新增', NULL, '', 2, '', '', b'0', b'0', b'0', 'job:add', NULL, NULL, '2019-10-29 12:58:27', NULL);
INSERT INTO `sys_menu` VALUES (61, 37, 0, 2, '岗位编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'job:edit', NULL, NULL, '2019-10-29 12:58:45', NULL);
INSERT INTO `sys_menu` VALUES (62, 37, 0, 2, '岗位删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'job:del', NULL, NULL, '2019-10-29 12:59:04', NULL);
INSERT INTO `sys_menu` VALUES (64, 39, 0, 2, '字典新增', NULL, '', 2, '', '', b'0', b'0', b'0', 'dict:add', NULL, NULL, '2019-10-29 13:00:17', NULL);
INSERT INTO `sys_menu` VALUES (65, 39, 0, 2, '字典编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'dict:edit', NULL, NULL, '2019-10-29 13:00:42', NULL);
INSERT INTO `sys_menu` VALUES (66, 39, 0, 2, '字典删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'dict:del', NULL, NULL, '2019-10-29 13:00:59', NULL);
INSERT INTO `sys_menu` VALUES (73, 28, 0, 2, '任务新增', NULL, '', 2, '', '', b'0', b'0', b'0', 'timing:add', NULL, NULL, '2019-10-29 13:07:28', NULL);
INSERT INTO `sys_menu` VALUES (74, 28, 0, 2, '任务编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'timing:edit', NULL, NULL, '2019-10-29 13:07:41', NULL);
INSERT INTO `sys_menu` VALUES (75, 28, 0, 2, '任务删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'timing:del', NULL, NULL, '2019-10-29 13:07:54', NULL);
INSERT INTO `sys_menu` VALUES (77, 18, 0, 2, '上传文件', NULL, '', 2, '', '', b'0', b'0', b'0', 'storage:add', NULL, NULL, '2019-10-29 13:09:09', NULL);
INSERT INTO `sys_menu` VALUES (78, 18, 0, 2, '文件编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'storage:edit', NULL, NULL, '2019-10-29 13:09:22', NULL);
INSERT INTO `sys_menu` VALUES (79, 18, 0, 2, '文件删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'storage:del', NULL, NULL, '2019-10-29 13:09:34', NULL);
INSERT INTO `sys_menu` VALUES (80, 6, 0, 1, '服务监控', 'ServerMonitor', 'monitor/server/index', 14, 'codeConsole', 'server', b'0', b'0', b'0', 'monitor:list', NULL, 'admin', '2019-11-07 13:06:39', '2020-05-04 18:20:50');
INSERT INTO `sys_menu` VALUES (82, 36, 0, 1, '生成配置', 'GeneratorConfig', 'generator/config', 33, 'dev', 'generator/config/:tableName', b'0', b'1', b'1', '', NULL, NULL, '2019-11-17 20:08:56', NULL);
INSERT INTO `sys_menu` VALUES (83, 10, 0, 1, '图表库', 'Echarts', 'components/Echarts', 50, 'chart', 'echarts', b'0', b'1', b'0', '', NULL, NULL, '2019-11-21 09:04:32', NULL);
INSERT INTO `sys_menu` VALUES (90, NULL, 5, 1, '运维管理', 'Mnt', '', 20, 'mnt', 'mnt', b'0', b'0', b'0', NULL, NULL, NULL, '2019-11-09 10:31:08', NULL);
INSERT INTO `sys_menu` VALUES (92, 90, 3, 1, '服务器', 'ServerDeploy', 'mnt/server/index', 22, 'server', 'mnt/serverDeploy', b'0', b'0', b'0', 'serverDeploy:list', NULL, NULL, '2019-11-10 10:29:25', NULL);
INSERT INTO `sys_menu` VALUES (93, 90, 3, 1, '应用管理', 'App', 'mnt/app/index', 23, 'app', 'mnt/app', b'0', b'0', b'0', 'app:list', NULL, NULL, '2019-11-10 11:05:16', NULL);
INSERT INTO `sys_menu` VALUES (94, 90, 3, 1, '部署管理', 'Deploy', 'mnt/deploy/index', 24, 'deploy', 'mnt/deploy', b'0', b'0', b'0', 'deploy:list', NULL, NULL, '2019-11-10 15:56:55', NULL);
INSERT INTO `sys_menu` VALUES (97, 90, 1, 1, '部署备份', 'DeployHistory', 'mnt/deployHistory/index', 25, 'backup', 'mnt/deployHistory', b'0', b'0', b'0', 'deployHistory:list', NULL, NULL, '2019-11-10 16:49:44', NULL);
INSERT INTO `sys_menu` VALUES (98, 90, 3, 1, '数据库管理', 'Database', 'mnt/database/index', 26, 'database', 'mnt/database', b'0', b'0', b'0', 'database:list', NULL, NULL, '2019-11-10 20:40:04', NULL);
INSERT INTO `sys_menu` VALUES (102, 97, 0, 2, '删除', NULL, '', 999, '', '', b'0', b'0', b'0', 'deployHistory:del', NULL, NULL, '2019-11-17 09:32:48', NULL);
INSERT INTO `sys_menu` VALUES (103, 92, 0, 2, '服务器新增', NULL, '', 999, '', '', b'0', b'0', b'0', 'serverDeploy:add', NULL, NULL, '2019-11-17 11:08:33', NULL);
INSERT INTO `sys_menu` VALUES (104, 92, 0, 2, '服务器编辑', NULL, '', 999, '', '', b'0', b'0', b'0', 'serverDeploy:edit', NULL, NULL, '2019-11-17 11:08:57', NULL);
INSERT INTO `sys_menu` VALUES (105, 92, 0, 2, '服务器删除', NULL, '', 999, '', '', b'0', b'0', b'0', 'serverDeploy:del', NULL, NULL, '2019-11-17 11:09:15', NULL);
INSERT INTO `sys_menu` VALUES (106, 93, 0, 2, '应用新增', NULL, '', 999, '', '', b'0', b'0', b'0', 'app:add', NULL, NULL, '2019-11-17 11:10:03', NULL);
INSERT INTO `sys_menu` VALUES (107, 93, 0, 2, '应用编辑', NULL, '', 999, '', '', b'0', b'0', b'0', 'app:edit', NULL, NULL, '2019-11-17 11:10:28', NULL);
INSERT INTO `sys_menu` VALUES (108, 93, 0, 2, '应用删除', NULL, '', 999, '', '', b'0', b'0', b'0', 'app:del', NULL, NULL, '2019-11-17 11:10:55', NULL);
INSERT INTO `sys_menu` VALUES (109, 94, 0, 2, '部署新增', NULL, '', 999, '', '', b'0', b'0', b'0', 'deploy:add', NULL, NULL, '2019-11-17 11:11:22', NULL);
INSERT INTO `sys_menu` VALUES (110, 94, 0, 2, '部署编辑', NULL, '', 999, '', '', b'0', b'0', b'0', 'deploy:edit', NULL, NULL, '2019-11-17 11:11:41', NULL);
INSERT INTO `sys_menu` VALUES (111, 94, 0, 2, '部署删除', NULL, '', 999, '', '', b'0', b'0', b'0', 'deploy:del', NULL, NULL, '2019-11-17 11:12:01', NULL);
INSERT INTO `sys_menu` VALUES (112, 98, 0, 2, '数据库新增', NULL, '', 999, '', '', b'0', b'0', b'0', 'database:add', NULL, NULL, '2019-11-17 11:12:43', NULL);
INSERT INTO `sys_menu` VALUES (113, 98, 0, 2, '数据库编辑', NULL, '', 999, '', '', b'0', b'0', b'0', 'database:edit', NULL, NULL, '2019-11-17 11:12:58', NULL);
INSERT INTO `sys_menu` VALUES (114, 98, 0, 2, '数据库删除', NULL, '', 999, '', '', b'0', b'0', b'0', 'database:del', NULL, NULL, '2019-11-17 11:13:14', NULL);
INSERT INTO `sys_menu` VALUES (116, 36, 0, 1, '生成预览', 'Preview', 'generator/preview', 999, 'java', 'generator/preview/:tableName', b'0', b'1', b'1', NULL, NULL, NULL, '2019-11-26 14:54:36', NULL);

-- ----------------------------
-- Table structure for sys_quartz_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_quartz_job`;
CREATE TABLE `sys_quartz_job`  (
  `job_id` bigint(0) NOT NULL COMMENT 'ID',
  `bean_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'Spring Bean名称',
  `cron_expression` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'cron 表达式',
  `is_pause` bit(1) NULL DEFAULT NULL COMMENT '状态: 1暂停、0启用',
  `job_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '任务名称',
  `method_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '方法名称',
  `params` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '参数',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `person_in_charge` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '负责人',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '报警邮箱',
  `sub_task` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '子任务ID',
  `pause_after_failure` bit(1) NULL DEFAULT NULL COMMENT '任务失败后是否暂停',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`job_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_quartz_job
-- ----------------------------
INSERT INTO `sys_quartz_job` VALUES (2, 'testTask', '0/5 * * * * ?', b'1', '测试1', 'run1', 'test', '带参测试，多参使用json', '测试', NULL, NULL, NULL, NULL, 'admin', '2019-08-22 14:08:29', '2020-05-24 13:58:33');
INSERT INTO `sys_quartz_job` VALUES (3, 'testTask', '0/5 * * * * ?', b'1', '测试', 'run', '', '不带参测试', 'Zheng Jie', '', '5,6', b'1', NULL, 'admin', '2019-09-26 16:44:39', '2020-05-24 14:48:12');
INSERT INTO `sys_quartz_job` VALUES (5, 'Test', '0/5 * * * * ?', b'1', '任务告警测试', 'run', NULL, '测试', 'test', '', NULL, b'1', 'admin', 'admin', '2020-05-05 20:32:41', '2020-05-05 20:36:13');
INSERT INTO `sys_quartz_job` VALUES (6, 'testTask', '0/5 * * * * ?', b'1', '测试3', 'run2', NULL, '测试3', 'Zheng Jie', '', NULL, b'1', 'admin', 'admin', '2020-05-05 20:35:41', '2020-05-05 20:36:07');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(0) NOT NULL COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `level` int(0) NULL DEFAULT NULL COMMENT '角色级别',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `data_scope` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '数据权限',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 1, '-', '全部', NULL, 'admin', '2018-11-23 11:04:37', '2020-08-06 16:10:24');
INSERT INTO `sys_role` VALUES (2, '普通用户', 2, '-', '本级', NULL, 'admin', '2018-11-23 13:09:06', '2020-09-05 10:45:12');

-- ----------------------------
-- Table structure for sys_roles_depts
-- ----------------------------
DROP TABLE IF EXISTS `sys_roles_depts`;
CREATE TABLE `sys_roles_depts`  (
  `role_id` bigint(0) NOT NULL,
  `dept_id` bigint(0) NOT NULL,
  PRIMARY KEY (`role_id`, `dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_roles_depts
-- ----------------------------
INSERT INTO `sys_roles_depts` VALUES (1, 1);
INSERT INTO `sys_roles_depts` VALUES (1, 2);
INSERT INTO `sys_roles_depts` VALUES (2, 1);
INSERT INTO `sys_roles_depts` VALUES (2, 2);
INSERT INTO `sys_roles_depts` VALUES (3, 1);
INSERT INTO `sys_roles_depts` VALUES (5, 1);
INSERT INTO `sys_roles_depts` VALUES (6, 1);
INSERT INTO `sys_roles_depts` VALUES (6, 2);
INSERT INTO `sys_roles_depts` VALUES (7, 1);
INSERT INTO `sys_roles_depts` VALUES (7, 2);
INSERT INTO `sys_roles_depts` VALUES (9, 1);
INSERT INTO `sys_roles_depts` VALUES (9, 2);
INSERT INTO `sys_roles_depts` VALUES (10, 1);
INSERT INTO `sys_roles_depts` VALUES (10, 2);
INSERT INTO `sys_roles_depts` VALUES (11, 1);
INSERT INTO `sys_roles_depts` VALUES (11, 2);
INSERT INTO `sys_roles_depts` VALUES (14, 1);
INSERT INTO `sys_roles_depts` VALUES (14, 2);
INSERT INTO `sys_roles_depts` VALUES (15, 1);
INSERT INTO `sys_roles_depts` VALUES (15, 2);
INSERT INTO `sys_roles_depts` VALUES (18, 1);
INSERT INTO `sys_roles_depts` VALUES (19, 1);
INSERT INTO `sys_roles_depts` VALUES (19, 2);
INSERT INTO `sys_roles_depts` VALUES (21, 1);
INSERT INTO `sys_roles_depts` VALUES (21, 2);
INSERT INTO `sys_roles_depts` VALUES (22, 1);
INSERT INTO `sys_roles_depts` VALUES (22, 2);
INSERT INTO `sys_roles_depts` VALUES (23, 1);
INSERT INTO `sys_roles_depts` VALUES (23, 2);
INSERT INTO `sys_roles_depts` VALUES (24, 1);
INSERT INTO `sys_roles_depts` VALUES (24, 2);
INSERT INTO `sys_roles_depts` VALUES (27, 1);
INSERT INTO `sys_roles_depts` VALUES (27, 2);
INSERT INTO `sys_roles_depts` VALUES (28, 1);
INSERT INTO `sys_roles_depts` VALUES (30, 1);
INSERT INTO `sys_roles_depts` VALUES (30, 2);
INSERT INTO `sys_roles_depts` VALUES (32, 1);
INSERT INTO `sys_roles_depts` VALUES (32, 2);
INSERT INTO `sys_roles_depts` VALUES (33, 1);
INSERT INTO `sys_roles_depts` VALUES (33, 2);
INSERT INTO `sys_roles_depts` VALUES (34, 1);
INSERT INTO `sys_roles_depts` VALUES (34, 2);
INSERT INTO `sys_roles_depts` VALUES (35, 1);
INSERT INTO `sys_roles_depts` VALUES (36, 1);
INSERT INTO `sys_roles_depts` VALUES (36, 2);
INSERT INTO `sys_roles_depts` VALUES (37, 1);
INSERT INTO `sys_roles_depts` VALUES (38, 1);
INSERT INTO `sys_roles_depts` VALUES (39, 1);
INSERT INTO `sys_roles_depts` VALUES (41, 1);
INSERT INTO `sys_roles_depts` VALUES (44, 1);
INSERT INTO `sys_roles_depts` VALUES (45, 1);
INSERT INTO `sys_roles_depts` VALUES (46, 1);
INSERT INTO `sys_roles_depts` VALUES (48, 1);
INSERT INTO `sys_roles_depts` VALUES (49, 1);
INSERT INTO `sys_roles_depts` VALUES (50, 1);
INSERT INTO `sys_roles_depts` VALUES (52, 1);
INSERT INTO `sys_roles_depts` VALUES (53, 1);
INSERT INTO `sys_roles_depts` VALUES (54, 1);
INSERT INTO `sys_roles_depts` VALUES (56, 1);
INSERT INTO `sys_roles_depts` VALUES (57, 1);
INSERT INTO `sys_roles_depts` VALUES (58, 1);
INSERT INTO `sys_roles_depts` VALUES (60, 1);
INSERT INTO `sys_roles_depts` VALUES (61, 1);
INSERT INTO `sys_roles_depts` VALUES (62, 1);
INSERT INTO `sys_roles_depts` VALUES (64, 1);
INSERT INTO `sys_roles_depts` VALUES (65, 1);
INSERT INTO `sys_roles_depts` VALUES (66, 1);
INSERT INTO `sys_roles_depts` VALUES (73, 1);
INSERT INTO `sys_roles_depts` VALUES (74, 1);
INSERT INTO `sys_roles_depts` VALUES (75, 1);
INSERT INTO `sys_roles_depts` VALUES (77, 1);
INSERT INTO `sys_roles_depts` VALUES (78, 1);
INSERT INTO `sys_roles_depts` VALUES (79, 1);
INSERT INTO `sys_roles_depts` VALUES (80, 1);
INSERT INTO `sys_roles_depts` VALUES (80, 2);
INSERT INTO `sys_roles_depts` VALUES (82, 1);
INSERT INTO `sys_roles_depts` VALUES (82, 2);
INSERT INTO `sys_roles_depts` VALUES (83, 1);
INSERT INTO `sys_roles_depts` VALUES (83, 2);
INSERT INTO `sys_roles_depts` VALUES (90, 1);
INSERT INTO `sys_roles_depts` VALUES (92, 1);
INSERT INTO `sys_roles_depts` VALUES (93, 1);
INSERT INTO `sys_roles_depts` VALUES (94, 1);
INSERT INTO `sys_roles_depts` VALUES (97, 1);
INSERT INTO `sys_roles_depts` VALUES (98, 1);
INSERT INTO `sys_roles_depts` VALUES (102, 1);
INSERT INTO `sys_roles_depts` VALUES (103, 1);
INSERT INTO `sys_roles_depts` VALUES (104, 1);
INSERT INTO `sys_roles_depts` VALUES (105, 1);
INSERT INTO `sys_roles_depts` VALUES (106, 1);
INSERT INTO `sys_roles_depts` VALUES (107, 1);
INSERT INTO `sys_roles_depts` VALUES (108, 1);
INSERT INTO `sys_roles_depts` VALUES (109, 1);
INSERT INTO `sys_roles_depts` VALUES (110, 1);
INSERT INTO `sys_roles_depts` VALUES (111, 1);
INSERT INTO `sys_roles_depts` VALUES (112, 1);
INSERT INTO `sys_roles_depts` VALUES (113, 1);
INSERT INTO `sys_roles_depts` VALUES (114, 1);
INSERT INTO `sys_roles_depts` VALUES (116, 1);
INSERT INTO `sys_roles_depts` VALUES (116, 2);
INSERT INTO `sys_roles_depts` VALUES (120, 1);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(0) NOT NULL COMMENT 'ID',
  `dept_id` bigint(0) NULL DEFAULT NULL COMMENT '部门名称',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称',
  `gender` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号码',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `avatar_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像地址',
  `avatar_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像真实路径',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `is_admin` bit(1) NULL DEFAULT NULL COMMENT '是否为admin账号',
  `enabled` bigint(0) NULL DEFAULT NULL COMMENT '状态: 1启用、0禁用',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新者',
  `pwd_reset_time` datetime(0) NULL DEFAULT NULL COMMENT '修改密码的时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 2, 'admin', '管理员', '男', '18888888888', '201507802@qq.com', 'avatar-20200806032259161.png', '/Users/jie/Documents/work/me/admin/eladmin/~/avatar/avatar-20200806032259161.png', '$2a$10$uPNu6rTC8aIFUP4krGApOeRbyVOAsTt5MKcbvBKeQqFmPvyJTYYIS', b'1', 1, NULL, 'admin', '2020-05-03 16:38:31', '2018-08-23 09:11:56', '2020-09-05 10:43:31');
INSERT INTO `sys_user` VALUES (2, 2, 'test', '测试', '男', '19999999999', '231@qq.com', NULL, NULL, '$2a$10$WZzPVEfbcXww3lL3u/xtoeJxiQbbEB7R96q4eJGthkd4E3mkSwabG', b'0', 1, 'admin', 'admin', NULL, '2020-05-05 11:15:49', '2020-09-05 10:43:38');

-- ----------------------------
-- Table structure for sys_users_jobs
-- ----------------------------
DROP TABLE IF EXISTS `sys_users_jobs`;
CREATE TABLE `sys_users_jobs`  (
  `user_id` bigint(0) NOT NULL COMMENT '用户ID',
  `job_id` bigint(0) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`, `job_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_users_jobs
-- ----------------------------
INSERT INTO `sys_users_jobs` VALUES (1, 11);
INSERT INTO `sys_users_jobs` VALUES (2, 12);

-- ----------------------------
-- Table structure for sys_users_roles
-- ----------------------------
DROP TABLE IF EXISTS `sys_users_roles`;
CREATE TABLE `sys_users_roles`  (
  `user_id` bigint(0) NOT NULL COMMENT '用户ID',
  `role_id` bigint(0) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_users_roles
-- ----------------------------
INSERT INTO `sys_users_roles` VALUES (1, 1);
INSERT INTO `sys_users_roles` VALUES (2, 2);

-- ----------------------------
-- Table structure for tool_alipay_config
-- ----------------------------
DROP TABLE IF EXISTS `tool_alipay_config`;
CREATE TABLE `tool_alipay_config`  (
  `config_id` bigint(0) NOT NULL COMMENT 'ID',
  `app_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '应用ID',
  `charset` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '编码',
  `format` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '类型 固定格式ison',
  `gateway_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '网关地址',
  `notify_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '异步回调',
  `private_key` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '私钥',
  `public_key` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '公钥',
  `return_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '回调地址',
  `sign_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '签名方式',
  `sys_service_provider_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商户号',
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tool_alipay_config
-- ----------------------------
INSERT INTO `tool_alipay_config` VALUES (1, '2016091700532697', 'utf-8', 'JSON', 'https://openapi.alipaydev.com/gateway.do', 'http://api.auauz.net/api/aliPay/notify', 'MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQC5js8sInU10AJ0cAQ8UMMyXrQ+oHZEkVt5lBwsStmTJ7YikVYgbskx1YYEXTojRsWCb+SH/kDmDU4pK/u91SJ4KFCRMF2411piYuXU/jF96zKrADznYh/zAraqT6hvAIVtQAlMHN53nx16rLzZ/8jDEkaSwT7+HvHiS+7sxSojnu/3oV7BtgISoUNstmSe8WpWHOaWv19xyS+Mce9MY4BfseFhzTICUymUQdd/8hXA28/H6osUfAgsnxAKv7Wil3aJSgaJczWuflYOve0dJ3InZkhw5Cvr0atwpk8YKBQjy5CdkoHqvkOcIB+cYHXJKzOE5tqU7inSwVbHzOLQ3XbnAgMBAAECggEAVJp5eT0Ixg1eYSqFs9568WdetUNCSUchNxDBu6wxAbhUgfRUGZuJnnAll63OCTGGck+EGkFh48JjRcBpGoeoHLL88QXlZZbC/iLrea6gcDIhuvfzzOffe1RcZtDFEj9hlotg8dQj1tS0gy9pN9g4+EBH7zeu+fyv+qb2e/v1l6FkISXUjpkD7RLQr3ykjiiEw9BpeKb7j5s7Kdx1NNIzhkcQKNqlk8JrTGDNInbDM6inZfwwIO2R1DHinwdfKWkvOTODTYa2MoAvVMFT9Bec9FbLpoWp7ogv1JMV9svgrcF9XLzANZ/OQvkbe9TV9GWYvIbxN6qwQioKCWO4GPnCAQKBgQDgW5MgfhX8yjXqoaUy/d1VjI8dHeIyw8d+OBAYwaxRSlCfyQ+tieWcR2HdTzPca0T0GkWcKZm0ei5xRURgxt4DUDLXNh26HG0qObbtLJdu/AuBUuCqgOiLqJ2f1uIbrz6OZUHns+bT/jGW2Ws8+C13zTCZkZt9CaQsrp3QOGDx5wKBgQDTul39hp3ZPwGNFeZdkGoUoViOSd5Lhowd5wYMGAEXWRLlU8z+smT5v0POz9JnIbCRchIY2FAPKRdVTICzmPk2EPJFxYTcwaNbVqL6lN7J2IlXXMiit5QbiLauo55w7plwV6LQmKm9KV7JsZs5XwqF7CEovI7GevFzyD3w+uizAQKBgC3LY1eRhOlpWOIAhpjG6qOoohmeXOphvdmMlfSHq6WYFqbWwmV4rS5d/6LNpNdL6fItXqIGd8I34jzql49taCmi+A2nlR/E559j0mvM20gjGDIYeZUz5MOE8k+K6/IcrhcgofgqZ2ZED1ksHdB/E8DNWCswZl16V1FrfvjeWSNnAoGAMrBplCrIW5xz+J0Hm9rZKrs+AkK5D4fUv8vxbK/KgxZ2KaUYbNm0xv39c+PZUYuFRCz1HDGdaSPDTE6WeWjkMQd5mS6ikl9hhpqFRkyh0d0fdGToO9yLftQKOGE/q3XUEktI1XvXF0xyPwNgUCnq0QkpHyGVZPtGFxwXiDvpvgECgYA5PoB+nY8iDiRaJNko9w0hL4AeKogwf+4TbCw+KWVEn6jhuJa4LFTdSqp89PktQaoVpwv92el/AhYjWOl/jVCm122f9b7GyoelbjMNolToDwe5pF5RnSpEuDdLy9MfE8LnE3PlbE7E5BipQ3UjSebkgNboLHH/lNZA5qvEtvbfvQ==', 'MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAut9evKRuHJ/2QNfDlLwvN/S8l9hRAgPbb0u61bm4AtzaTGsLeMtScetxTWJnVvAVpMS9luhEJjt+Sbk5TNLArsgzzwARgaTKOLMT1TvWAK5EbHyI+eSrc3s7Awe1VYGwcubRFWDm16eQLv0k7iqiw+4mweHSz/wWyvBJVgwLoQ02btVtAQErCfSJCOmt0Q/oJQjj08YNRV4EKzB19+f5A+HQVAKy72dSybTzAK+3FPtTtNen/+b5wGeat7c32dhYHnGorPkPeXLtsqqUTp1su5fMfd4lElNdZaoCI7osZxWWUo17vBCZnyeXc9fk0qwD9mK6yRAxNbrY72Xx5VqIqwIDAQAB', 'http://api.auauz.net/api/aliPay/return', 'RSA2', '2088102176044281');

-- ----------------------------
-- Table structure for tool_email_config
-- ----------------------------
DROP TABLE IF EXISTS `tool_email_config`;
CREATE TABLE `tool_email_config`  (
  `config_id` bigint(0) NOT NULL COMMENT 'ID',
  `from_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '收件人',
  `host` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮件服务器SMTP地址',
  `pass` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `port` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '端门',
  `user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '发件者用户名',
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tool_giniu_content
-- ----------------------------
DROP TABLE IF EXISTS `tool_giniu_content`;
CREATE TABLE `tool_giniu_content`  (
  `content_id` bigint(0) NOT NULL COMMENT 'ID',
  `bucket` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'Bucket 识别符',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件名称',
  `size` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件大小',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件类型: 私有或公开',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件url',
  `suffix` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件后缀',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '上传或同步的时间',
  PRIMARY KEY (`content_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tool_local_storage
-- ----------------------------
DROP TABLE IF EXISTS `tool_local_storage`;
CREATE TABLE `tool_local_storage`  (
  `storage_id` bigint(0) NOT NULL COMMENT 'ID',
  `real_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件真实的名称',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件名',
  `suffix` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '后缀',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '路径',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '类型',
  `size` varchar(0) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '大小',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`storage_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role_id` bigint(0) NULL DEFAULT NULL COMMENT '角色id',
  `menu_id` bigint(0) NULL DEFAULT NULL COMMENT '菜单id',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;
