package com.jwt.demo.commons;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

/**
 * jwt工具类
 */
public class JWTUtils {
    // 服务器的秘钥,用于做加解密的秘钥
    private static final String JWT_SECERT = "jwt_demo";
    private static final ObjectMapper MAPPER = new ObjectMapper();
    public static final int JWT_ERRCODE_EXPIRE = 1005;                 // TOKEN过期
    public static final int JWT_ERRCODE_FAIL = 1006;                   // 验证不通过

    /**
     * 生成秘钥
     * @return
     * @throws Exception
     */
    public static SecretKey generalKey() throws Exception{
        byte[] encodedKey = JWT_SECERT.getBytes("UTF-8");
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * 创建token的方法
     * @param id jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击。如可以使用用户的id作为唯一标记
     * @param iss jwt签发者
     * @param subject jwt所面向的客户。payload中记录的public claims（公开数据）
     * @param ttlMillis 有效期，单位为毫秒
     * @return token
     */
    public static String createJWT(String id, String iss, String subject, long ttlMillis) throws Exception{
        // 设置加密算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        // 当前时间，用于记录token生成的时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        // 生成秘钥
        SecretKey secretKey = generalKey();
        // 创建jwt构建器，就是使用指定的信息和加密算法，生成token
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setIssuer(iss)
                .setSubject(subject)
                .setIssuedAt(now)
                .signWith(signatureAlgorithm, secretKey);
        if(ttlMillis > 0 ){
            // 设置token失效时间
            long expMillis = nowMillis + ttlMillis;
            Date expDate = new Date(expMillis);
            builder.setExpiration(expDate);
        }
        return builder.compact();
    }

    /**
     * 验证JWT
     */
    public static JWTResult validateJWT(String jwtStr){
        JWTResult checkResult = new JWTResult();
        Claims claims = null;
        try{
            claims = parseJWT(jwtStr);
            checkResult.setSuccess(true);
            checkResult.setClaims(claims);
        }catch(ExpiredJwtException e){
            checkResult.setErrCode(JWT_ERRCODE_EXPIRE);
            checkResult.setSuccess(false);
        }catch(SignatureException e){
            checkResult.setErrCode(JWT_ERRCODE_FAIL);
            checkResult.setSuccess(false);
        }catch (Exception e){
            checkResult.setErrCode(JWT_ERRCODE_FAIL);
            checkResult.setSuccess(false);
        }
        return checkResult;
    }

    /**
     * 解析jwt字符串
     * @param str
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String str) throws Exception{
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(str)
                .getBody();
    }

    /**
     * 生成subject信息,将对象转换成json字符串
     * @param subObj - 要转换的对象
     * @return java对象 -》json字符串解析失败时返回null
     * @throws Exception
     */
    public static String generalSubject(Object subObj){
        try {
            return MAPPER.writeValueAsString(subObj);
        }catch (JsonProcessingException e){
            e.printStackTrace();
            return null;
        }
    }
}
