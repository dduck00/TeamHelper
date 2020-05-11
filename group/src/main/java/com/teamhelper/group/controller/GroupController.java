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

    @PostMapping("/make/{groupName}")
    public Group makeGroup(@PathVariable String groupName,
                           @CookieValue("uid") String uid) {
        return groupService.makeGroup(new Group(groupName, uid));
    }

    @PostMapping("/add/{gid}")
    public Group addGroupMember(@PathVariable String gid,
                                @CookieValue("uid") String uid) {
        return groupService.addGroupMember(gid, uid);
    }

    @DeleteMapping("/delete/{gid}")
    public Group removeGroupMember(@PathVariable String gid,
                                   @CookieValue("uid") String uid) {
        return groupService.removeGroupMember(gid, uid);
    }

    @GetMapping("/get")
    public List<Group> getGroups() {
        return groupService.getGroups();
    }

    @GetMapping("/get/name/{groupName}")
    public List<Group> getGroupsByName(@PathVariable String groupName) {
        return groupService.getGroupsByName(groupName);
    }

    @GetMapping("/get/user/{uid}")
    public List<Group> getGroupsContainUser(@PathVariable String uid) {
        return groupService.getGroupsContainUser(uid);
    }
}
