package com.teamhelper.auth.controller;

import com.teamhelper.auth.dto.User;
import com.teamhelper.auth.jwt.JwtToken;
import com.teamhelper.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@RestController
public class UserRestController {

    private final UserService userService;
    private final JwtToken jwtToken;

    @Autowired
    UserRestController(UserService userService, JwtToken jwtToken) {
        this.userService = userService;
        this.jwtToken = jwtToken;
    }

    @GetMapping("/checkIdDuplicate/{checkId}")
    public boolean checkIdDuplicate(@PathVariable String checkId) {
        return userService.isIdExist(checkId) == false;
    }

    @PostMapping("/signUp")
    public void signUp(User user) {
        userService.signUp(user);
    }

    @GetMapping("/login/{id}/{pw}")
    public String login(HttpServletResponse httpServletResponse,
                        @PathVariable String id,
                        @PathVariable String pw) throws IllegalAccessException, UnsupportedEncodingException {

        User user = userService.login(id, pw);

        return jwtToken.getToken(user);

    }
}
