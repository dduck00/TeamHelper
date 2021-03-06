package com.teamhelper.group.dao;

import com.teamhelper.group.dto.Group;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GroupRepository extends MongoRepository<Group, String> {
    public List<Group> findGroupsByUsersContains(String uid);
    public List<Group> findByNameLike(String name);
    public List<Group> findAllByOrderByGid();
    public boolean existsByName(String name);
}
