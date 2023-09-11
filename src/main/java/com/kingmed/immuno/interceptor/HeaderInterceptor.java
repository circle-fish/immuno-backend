package com.kingmed.immuno.interceptor;

import com.kingmed.immuno.common.CommonConstants;
import com.kingmed.immuno.entity.KmcsUser;
import com.kingmed.immuno.util.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HeaderInterceptor implements AsyncHandlerInterceptor {

    /**
     * 默认开启跨域设置（使用单体）
     * 微服务情况下，此属性设置为false
     */
    private final boolean allowOrigin = true;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        JwtUtil jwtUtil = SpringUtils.getBean(JwtUtil.class);
        String token = ServletUtils.getRequest().getHeader(CommonConstants.X_ACCESS_TOKEN);
        // 无token时逻辑 ----
        if (StringUtils.isNotEmpty(token)) {
            KmcsUser loginUser = jwtUtil.getKmcsUserFromToken(token);
            if (StringUtils.isNotNull(loginUser)) {
                jwtUtil.refreshToken(loginUser);
                SecurityContextHolder.set("user", loginUser);
            }
        }
        if (allowOrigin) {
            response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, request.getHeader(HttpHeaders.ORIGIN));
            // 允许客户端请求方法
            response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET,POST,OPTIONS,PUT,DELETE");
            // 允许客户端提交的Header
            String requestHeaders = request.getHeader(HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS);
            if (StringUtils.isNotEmpty(requestHeaders)) {
                response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, requestHeaders);
            }
            // 允许客户端携带凭证信息(是否允许发送Cookie)
            response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
        }
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (RequestMethod.OPTIONS.name().equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpStatus.OK.value());
            return false;
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        SecurityContextHolder.remove();
    }

}
