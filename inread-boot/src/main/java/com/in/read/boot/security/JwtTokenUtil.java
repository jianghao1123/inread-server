package com.in.read.boot.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.in.read.framework.exception.TokenExpiredException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by luyun on 2019/1/6.
 */
@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    private static final String ISSUER = "inread";
    private static final String UID = "uid";

    /**
     * 返回新的token
     *
     * @param userId
     * @return
     */
    public final String sign(int userId) {
        Date date = new Date(System.currentTimeMillis() + expiration);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        // 附带username信息
        return JWT.create()
                .withIssuer(ISSUER)
                .withClaim(UID, userId)
                .withExpiresAt(date)
                .sign(algorithm);
    }

    /**
     * 校验token
     *
     * @param token
     * @return
     * @throws TokenExpiredException
     */
    public int validate(String token) throws TokenExpiredException {
        int uid = 0;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            do {
                Date expiresAt = jwt.getExpiresAt();
                if (expiresAt == null) {
                    break;
                }
                if (expiresAt.before(new Date())) {
                    throw new TokenExpiredException("请重新登录");
                }
                Claim claim = jwt.getClaim(UID);
                uid = claim.asInt();
            } while (false);
        } catch (JWTVerificationException exception) {
            uid = 0;
        }
        return uid;
    }
}
