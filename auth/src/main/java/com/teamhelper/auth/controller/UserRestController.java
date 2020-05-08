package com.teamhelper.auth.controller;

import com.teamhelper.auth.dto.User;
import com.teamhelper.auth.jwt.JwtToken;
import com.teamhelper.auth.service.UserManageService;
import com.teamhelper.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/")
public class UserRestController {

    private final UserService userService;
    private final UserManageService userManageService;
    private final JwtToken jwtToken;

    @Autowired
    UserRestController(UserService userService, UserManageService userManageService, JwtToken jwtToken){
        this.userService = userService;
        this.userManageService = userManageService;
        this.jwtToken = jwtToken;
    }

    @GetMapping("/checkIdDuplicate/{checkId}")
    public boolean checkIdDuplicate(@PathVariable String checkId){
        return userService.checkIdExist(checkId);
    }

    @PutMapping("/updateUserInfo")
    public void updateUserInfo(User user){
        if(userManageService.changeUserData(user) == false){
            throw new IllegalArgumentException("Fail Update Check Data");
        }
    }

    @PostMapping("/signUp")
    public void signUp(User user){

        if(userService.checkIdExist(user.getUserId())) {
            throw new IllegalArgumentException("Duplicate Id");
        }

        if(userManageService.signUp(user) == false){
            throw new IllegalArgumentException("Fail SignUp check Data");
        }
    }

    @GetMapping("/login")
    public User login(HttpServletResponse httpServletResponse,
                        @RequestParam String id,
                        @RequestParam String pw) throws IllegalAccessException, UnsupportedEncodingException {
        User user = userService.login(id, pw);

        if(user == null){
            throw new IllegalAccessException("Login Fail");
        }

        Cookie[] cookies = {
                new Cookie("userId", user.getUserId()),
                new Cookie("password", user.getPassword()),
                new Cookie("userName", URLEncoder.encode(user.getUserName(), "UTF-8"))
        };

        for(Cookie cookie: cookies){
            cookie.setMaxAge(-1);
            cookie.setPath("/");
            httpServletResponse.addCookie(cookie);
        }

        return user;
    }
}
