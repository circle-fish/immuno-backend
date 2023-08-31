//package com.kingmed.immuno.util;
//
//import com.kingmed.kmhrg.constants.CommonConstants;
//import com.kingmed.kmhrg.exception.NoAuthException;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//public class AuthUtil {
//
//    @Value("${authentication.enabled}")
//    private Boolean enableAuth;
//
//    public void checkLogin() throws IOException {
//        //可以通过这种方式获取请求头中的信息执行鉴权
//        JwtUtil jwtUtil = SpringUtils.getBean(JwtUtil.class);
//        HttpServletResponse response = ServletUtils.getResponse();
//        String token = ServletUtils.getRequest().getHeader(CommonConstants.X_ACCESS_TOKEN);
//
//        if (enableAuth && jwtUtil.verifyToken(token)) {
//            response.sendError(401, "no auth");
//            throw new NoAuthException("no auth");
//        }
//    }
//
//    /**
//     * 临时鉴权
//     * @param token: String
//     * @return Boolean
//     */
//    public boolean checkToken(String token) {
//        // 获取RedisUtil
//        RedisUtil redisUtil = SpringUtils.getBean(RedisUtil.class);
//        //
//        String tokenInRedis = redisUtil.getCacheObject("kmhrg_tempToken");
//        if (token == null || tokenInRedis == null) {
//            return false;
//        }
//        return token.equals(tokenInRedis);
//    }
//
//}