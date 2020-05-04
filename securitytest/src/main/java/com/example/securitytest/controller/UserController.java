package com.example.securitytest.controller;

import com.example.securitytest.dto.User;
import com.example.securitytest.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserController(PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/join")
    public Long join(@RequestBody String email,
                     @RequestBody String password) {
        User user = new User(email, passwordEncoder.encode(password), Collections.singletonList("ROLE_USER"));
        return user.getId();
    }

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");

        User user = new User("1111", "duck@naver.com");
        return jwtTokenProvider.createToken(user.getId() + "/" + user.getUsername(), user.getRoles());
    }

}
