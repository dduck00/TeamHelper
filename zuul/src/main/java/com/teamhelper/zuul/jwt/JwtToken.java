package com.teamhelper.zuul.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
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
    private final ObjectMapper objectMapper;

    public JwtToken() {
        SECRET_KEY = Base64.getEncoder().encodeToString(KEY.getBytes());
        objectMapper = new ObjectMapper();
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

        System.out.println(headers);
        return true;
    }

    private String resolveToken(HttpHeaders headers) {
        List<String> header = headers.get("X-AUTH-TOKEN");
        if (header == null)
            return null;
        return header.get(0);
    }

    private boolean validateToken(Claims claims) {
        return !claims.getExpiration().before(new Date());
    }
}