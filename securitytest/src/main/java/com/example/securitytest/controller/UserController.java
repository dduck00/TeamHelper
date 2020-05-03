package com.example.securitytest.controller;

import com.example.securitytest.dao.UserDao;
import com.example.securitytest.dto.User;
import com.example.securitytest.jwt.JwtTokenProvider;
import org.apache.commons.lang3.ObjectUtils;
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
    private final UserDao userDao;

    @Autowired
    public UserController(PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, UserDao userDao) {
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDao = userDao;
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

        User user = userDao.selectUserByEmail(email);
        if (ObjectUtils.isEmpty(user)) {
            new IllegalArgumentException("Wrong Email");
        }
        if (passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Wrong Password");
        }

        return jwtTokenProvider.createToken(user.getId() + "/" + user.getUsername(), user.getRoles());
    }

}
