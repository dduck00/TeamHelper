package com.teamhelper.auth.service.Impl;

import com.teamhelper.auth.dao.UserDao;
import com.teamhelper.auth.dto.User;
import com.teamhelper.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User login(String id, String pw) {
        return userDao.selectUserByIdPw(id, pw);
    }

    @Override
    public boolean checkIdExist(String id) {
        if(id == null)
            return false;

        return userDao.selectExistId(id) != 0;
    }
}
