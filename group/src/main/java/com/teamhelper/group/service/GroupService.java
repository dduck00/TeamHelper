package com.teamhelper.group.service;

import com.teamhelper.group.dto.Group;

import java.util.List;

public interface GroupService {
    Group addGroupMember(String gid, String uid);
    Group removeGroupMember(String gid, String uid);

    List<Group> getGroupsContainUser(String uid);
    List<Group> getGroupsByName(String groupName);
    List<Group> getGroups();

    Group makeGroup(Group group);
}
