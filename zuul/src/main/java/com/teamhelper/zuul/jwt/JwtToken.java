package com.teamhelper.zuul.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JwtToken {

    private final String KEY = "DUCK";
    private final String SECRET_KEY;
    private final long TokenValidTime = 30 * 60 * 100L;

    JwtToken() {
        SECRET_KEY = Base64.getEncoder().encodeToString(KEY.getBytes());
        System.out.println(SECRET_KEY);
    }

    public Map<String, String> parseHeader(ServerHttpRequest request) {
        String token = resolveToken(request);

        if (StringUtils.isEmpty(token)
                || validateToken(token) == false) {
            return null;
        }
        Map<String, String> user = new HashMap<>();

        System.out.println(token);
        String[] userInfos = getUserPk(token).split("/");

        user.put("USER_CODE", userInfos[0]);
        user.put("ID", userInfos[1]);

        return user;
    }

    private String getUserPk(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    private String resolveToken(ServerHttpRequest request) {
        List<String> header = request.getHeaders().get("X-AUTH-TOKEN");
        if (header == null)
            return null;
        return header.get(0);
    }

    private boolean validateToken(String token) {
        Jws<Claims> claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
        return !claims.getBody().getExpiration().before(new Date());
    }


}
