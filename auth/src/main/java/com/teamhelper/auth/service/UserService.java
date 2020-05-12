package com.teamhelper.auth.service;

import com.teamhelper.auth.dto.User;

public interface UserService {
    public User login(String id, String pw) throws IllegalAccessException;

    public boolean isIdExist(String id);

    public void signUp(User user);
}
