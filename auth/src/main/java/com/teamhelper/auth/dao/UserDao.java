package com.teamhelper.auth.dao;

import com.teamhelper.auth.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {
    User selectUserByIdPw(@Param("id") String id, @Param("pw") String pw);
    int insertUser(@Param("id") String id, @Param("pw") String pw, @Param("name") String name);
}
