package com.teamhelper.auth.service.Impl;

import com.teamhelper.auth.dao.UserDao;
import com.teamhelper.auth.dto.User;
import com.teamhelper.auth.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User login(String id, String pw) throws IllegalAccessException {

        if (StringUtils.isBlank(id)
                || StringUtils.isBlank(pw)) {
            throw new IllegalArgumentException("ID or PW is Empty or Blank");
        }

        User user = userDao.selectUserByIdPw(id, pw);

        if (user == null) {
            throw new IllegalAccessException("Wrong ID or PW");
        }

        return user;
    }

    @Override
    public boolean isIdExist(String id) {
        if (StringUtils.isBlank(id)) {
            throw new IllegalArgumentException("Empty ID");
        }

        return userDao.countExistId(id) != 0;
    }

    @Override
    public void signUp(User user) {

        if (isValidUser(user) == false) {
            throw new IllegalArgumentException("Wrong User Data");
        }

        if (isIdExist(user.getUserId())) {
            throw new IllegalArgumentException("Already has Id");
        }

        user.setUid(UUID.randomUUID().toString());
        System.out.println(user.getUid().length());
        int result = userDao.insertUser(user);

        if (result == 0) {
            throw new IllegalArgumentException("Fail SignUp");
        }
    }

    private boolean isValidUser(User user) {
        if (StringUtils.isBlank(user.getUserId())
                && StringUtils.isBlank(user.getPassword())
                && StringUtils.isBlank(user.getUserName())) {
            return false;
        }
        return true;
    }


}
