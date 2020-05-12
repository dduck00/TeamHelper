package com.teamhelper.auth.jwt;

import com.teamhelper.auth.dto.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtToken {

    private final String KEY = "DUCK";
    private final String SECRET_KEY;
    private final long TOKEN_VALID_TIME = 30 * 60 * 1000L;

    JwtToken() {
        SECRET_KEY = Base64.getEncoder().encodeToString(KEY.getBytes());
    }

    public String getToken(User user) {
        return createToken(userToMap(user));
    }

    private Map<String, Object> userToMap(User user){
        Map<String, Object> userInfoMap = new HashMap<>();
        userInfoMap.put("uid", user.getUid());
        userInfoMap.put("userId", user.getUserId());
        userInfoMap.put("userName", user.getUserName());
        return userInfoMap;
    }


    private String createToken(Map<String, Object> userInfo) {
        Date now = new Date();
        return Jwts.builder()
                .setClaims(userInfo)
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + TOKEN_VALID_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

}
