package com.teamhelper.auth.service;

import com.teamhelper.auth.dto.User;

public interface UserManageService {
    public boolean signUp(User user);
    public boolean changeUserData(User user);
}
