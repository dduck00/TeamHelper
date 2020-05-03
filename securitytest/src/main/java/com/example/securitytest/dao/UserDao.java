package com.example.securitytest.dao;

import com.example.securitytest.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {
    User selectUserByEmail(String email);
}
