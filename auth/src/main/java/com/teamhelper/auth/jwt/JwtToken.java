package com.teamhelper.auth.jwt;

import com.teamhelper.auth.dto.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
public class JwtToken {

    private final String KEY = "DUCK";
    private final String SECRET_KEY;
    private final long TOKEN_VALID_TIME = 30 * 60 * 100L;

    JwtToken() {
        SECRET_KEY = Base64.getEncoder().encodeToString(KEY.getBytes());
        System.out.println(SECRET_KEY);
    }

    public String getToken(User user){
        String id = user.getUserId();
        String name = user.getUserName();
        return createToken(id+"/"+name);
    }


    private String createToken(String userPk) {
        Claims claims = Jwts.claims().setSubject(userPk);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + TOKEN_VALID_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

}
