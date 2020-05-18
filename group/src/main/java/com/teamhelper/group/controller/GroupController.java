package com.teamhelper.group.controller;

import com.teamhelper.group.dto.Group;
import com.teamhelper.group.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GroupController {

    private final GroupService groupService;

    @Autowired
    GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping("/group/{groupName}/user/{uid}")
    public Group makeGroup(@PathVariable String groupName,
                           @PathVariable String uid) {
        return groupService.makeGroup(new Group(groupName, uid));
    }

    @PutMapping("/group/{gid}/user/{uid}")
    public Group addGroupMember(@PathVariable String gid,
                                @PathVariable String uid) {
        return groupService.addGroupMember(gid, uid);
    }

    @DeleteMapping("/group/{gid}/user/{uid}")
    public Group removeGroupMember(@PathVariable String gid,
                                   @PathVariable String uid) {
        return groupService.removeGroupMember(gid, uid);
    }

    @GetMapping("/groups")
    public List<Group> getGroups() {
        return groupService.getGroups();
    }

    @GetMapping("/group/{groupName}")
    public List<Group> getGroupsByName(@PathVariable String groupName) {
        return groupService.getGroupsByName(groupName);
    }

    @GetMapping("/user/{uid}/groups")
    public List<Group> getGroupsContainUser(@PathVariable String uid) {
        return groupService.getGroupsContainUser(uid);
    }
}
