package com.teamhelper.chatservice.dao;

import com.teamhelper.chatservice.dto.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.Map;

@Repository
public class GroupRepository {

    private Map<String, Group> groupMap;

    @Autowired
    GroupRepository(){
        this.groupMap = new LinkedHashMap<>();
    }

    public Group findGroupById(String id){
        return groupMap.get(id);
    }

    public Group creatGroup(String id){
        Group group = new Group(id);
        groupMap.put(id, group);
        return group;
    }

}
