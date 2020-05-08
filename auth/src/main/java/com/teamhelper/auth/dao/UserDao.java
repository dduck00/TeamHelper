package com.teamhelper.auth.dao;

import com.teamhelper.auth.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {
    User selectUserByIdPw(String id, String pw);
    int selectExistId(@Param("id") String id);

    int insertUser(User user);

    int updateUser(User user);
}
