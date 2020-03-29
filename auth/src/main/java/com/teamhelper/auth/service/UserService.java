package com.teamhelper.auth.service;

import com.teamhelper.auth.dao.UserDao;
import com.teamhelper.auth.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User login(String id, String pw){
        User user =userDao.selectUserByIdPw(id, pw);
        if(user == null){
            throw new IllegalArgumentException("No User Data");
        }
        return user;
    }
}
