package com.teamhelper.auth.controller;

import com.teamhelper.auth.dto.User;
import com.teamhelper.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("login")
    public User login(@RequestParam("id") String id,
                            @RequestParam("pw") String pw){
        return userService.login(id, pw);
    }


}
