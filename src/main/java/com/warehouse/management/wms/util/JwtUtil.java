package com.warehouse.management.wms.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

public class JwtUtil {
    public static final Long JWT_TTL = 60 * 60 * 1000L;

    public static final String JWT_KEY = "gent";

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 生成jwt
     *
     * @param subject token中要存放的数据(json格式)
     * @return A compact URL-safe JWT string
     */
    public static String createJwt(String subject) {
        JwtBuilder jwtBuilder = getJwtBuilder(subject, null, getUUID());
        return jwtBuilder.compact();
    }

    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String UUID) {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long timeMillis = System.currentTimeMillis();
        //token创建时间
        Date createDate = new Date(timeMillis);
        if (ttlMillis == null) {
            ttlMillis = JwtUtil.JWT_TTL;
        }
        long expMillis = timeMillis + ttlMillis;
        //token失效时间
        Date expirationDate = new Date(expMillis);
        return Jwts.builder().setId(UUID).setSubject(subject).setIssuer("gent").setIssuedAt(createDate).signWith(signatureAlgorithm, secretKey).setExpiration(expirationDate);


    }

    /**
     * 生成jwt
     *
     * @param subject   token中要存放的数据(json格式)
     * @param ttlMillis token存放时间
     * @return A compact URL-safe JWT string
     */
    public static String createJwt(String subject, Long ttlMillis) {
        JwtBuilder jwtBuilder = getJwtBuilder(subject, ttlMillis, getUUID());
        return jwtBuilder.compact();
    }

    /**
     * 生成加密后的密钥
     *
     * @return 密钥
     */
    private static SecretKey generalKey() {
        byte[] decode = Base64.getDecoder().decode(JWT_KEY);
        return new SecretKeySpec(decode, 0, decode.length, "AES");
    }

    public static Claims parseJwt(String jwt) {
        SecretKey secretKey = generalKey();
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
    }

    public static void main(String[] args) throws Exception {
//        String jwt = createJwt("222");
//        System.out.println(jwt);
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyMWYzZDY4Mjk2NDA0YjExYTEzZDViNmMyY2Q4YjA2NCIsInN1YiI6IjIyMiIsImlzcyI6ImdlbnQiLCJpYXQiOjE3MDUxNTM5NDcsImV4cCI6MTcwNTE1NzU0N30.TDw06tIdsJSr_hDr5aS1sQnanvcKembJ5m5kJtk_4E8";
        Claims claims = parseJwt(token);
        String subject = claims.getSubject();

        System.out.println(subject);
    }

}
