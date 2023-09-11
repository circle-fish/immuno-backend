package com.kingmed.immuno.util;


import com.kingmed.immuno.common.CommonConstants;
import com.kingmed.immuno.entity.KmcsUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {
    /**
     * Token有效期为2小时（Token在redis中缓存时间为两倍）
     *
     */
    private final long EXPIRE_TIME = 2 * 60 * 60 * 1000;

    private final String secret = "--]]wdq%hDfo+2m]f%tkh783vu%vu]ye7f8a73yg2g87ga8f7g4ag[[+2few3564624g--+few%dd--]]wd";

    @Resource
    private RedisUtil redisUtil;

    /**
     * 校验token是否正确
     * @TODO 如果是一个乱的token怎么办，要加无效token校验逻辑
     * @param token 密钥
     * @return 是否正确
     */
    public boolean verifyToken(String token) {
        if (token == null) {
            return false;
        }
        KmcsUser user = getKmcsUserFromToken(token);
        if (user == null) {
            return false;
        }
        refreshToken(user);
        return true;
    }

    public String getTokenKey(String userId) {
        return "token_userId:" + userId;
    }

    /**
     * 生成token
     *
     * @param user KmcsUser
     * @return 加密的token
     */
//    public String createToken(KmcsUser user) {
//        String uuid = UUID.randomUUID().toString();
//        user.setUuid(uuid);
//        refreshToken(user);
//
//        // Jwt存储信息
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("user_key", uuid);
//        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
//        // 附带username信息
//        return Jwts.builder().setClaims(claims).setExpiration(date).signWith(SignatureAlgorithm.HS512, secret).compact();
//    }

    public String createTestToken(KmcsUser user) {
        // Jwt存储信息
        Map<String, Object> claims = new HashMap<>();
        claims.put("user_key", "eyJhbGciOiJIUzUxMiJ9");
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        return Jwts.builder().setClaims(claims).setExpiration(date).signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    /**
     * 刷新令牌有效期
     *
     * @param user 登录信息
     */
    public void refreshToken(KmcsUser user) {

        Date regDate = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        user.setRegTime(regDate);
        // 根据uuid将loginUser缓存 ---> 改成token ???
        String userKey = getTokenKey(user.getToken());
        redisUtil.setCacheObject(userKey, user, EXPIRE_TIME, TimeUnit.MILLISECONDS);
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    public Claims parseToken(String token)
    {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public KmcsUser getLoginUserFromRequest(HttpServletRequest request) {
        String token = request.getHeader(CommonConstants.X_ACCESS_TOKEN);
        return getKmcsUserFromToken(token);
    }

    public KmcsUser getKmcsUserFromToken(String token) {
        KmcsUser user;
        try {
            if (StringUtils.isNotEmpty(token)) {
                String userKey = getUserKey(token);
                user = redisUtil.getCacheObject(getTokenKey(userKey));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getUserKey(String token) {
        Claims claims = parseToken(token);
        return getValue(claims, "user_key");
    }
    /**
     * 根据身份信息获取键值
     *
     * @param claims 身份信息
     * @param key 键
     * @return 值
     */
    public String getValue(Claims claims, String key) {
        return Convert.toStr(claims.get(key), "");
    }
}
