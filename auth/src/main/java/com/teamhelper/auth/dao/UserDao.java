package com.teamhelper.auth.dao;

import com.teamhelper.auth.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {
    User selectUserByIdPw(String id, String pw);

    int countExistId(@Param("id") String id);

    int insertUser(User user);
}
