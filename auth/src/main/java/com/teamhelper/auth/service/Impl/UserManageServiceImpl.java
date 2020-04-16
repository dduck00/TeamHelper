package com.teamhelper.auth.service.Impl;


import com.teamhelper.auth.dao.UserDao;
import com.teamhelper.auth.dto.User;
import com.teamhelper.auth.service.UserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManageServiceImpl implements UserManageService {
    private final UserDao userDao;

    @Autowired
    public UserManageServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean signUp(User user) {
        /*
        * User 데이터 유효성 검사
        * */
        int result = userDao.insertUser(user);

        if(result == 0)
            return false;

        return true;
    }

    @Override
    public boolean changeUserData(User user) {

        int result = userDao.updateUser(user);

        if(result == 0)
            return false;

        return true;
    }
}
