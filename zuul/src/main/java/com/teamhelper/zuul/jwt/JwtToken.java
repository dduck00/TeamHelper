package com.teamhelper.zuul.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
public class JwtToken {

    private final String KEY = "DUCK";

    private final String SECRET_KEY;

    public JwtToken() {
        SECRET_KEY = Base64.getEncoder().encodeToString(KEY.getBytes());
    }

    public boolean hasValidJwtToken(HttpHeaders headers) {
        try {
            String token = StringUtils.defaultIfEmpty(resolveToken(headers), "");
            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
            if (validateToken(claims) == false) {
                throw new JwtException("Time Expired");
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean setHeader(HttpHeaders headers) {
        String token = resolveToken(headers);
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();

        if (StringUtils.isEmpty(token)
                || validateToken(claims) == false) {
            return false;
        }

        headers.add("JWT-UID", claims.get("uid").toString());
        headers.add("JWT-USER-NAME", claims.get("userName").toString());
        headers.add("JWT-USER-ID", claims.get("userId").toString());

        return true;
    }

    private String resolveToken(HttpHeaders headers) {
        List<String> tokenHeaders = headers.get("X-AUTH-TOKEN");
        if (tokenHeaders == null)
            return null;
        return tokenHeaders.get(0);
    }

    private boolean validateToken(Claims claims) {
        return !claims.getExpiration().before(new Date());
    }
}