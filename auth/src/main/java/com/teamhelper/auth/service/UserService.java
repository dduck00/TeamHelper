package com.teamhelper.auth.service;

import com.teamhelper.auth.dto.User;

public interface UserService {
    public User login(String id, String pw);
    public boolean checkIdExist(String id);
}
