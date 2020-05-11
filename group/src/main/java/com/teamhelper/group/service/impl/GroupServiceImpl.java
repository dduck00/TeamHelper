package com.teamhelper.group.service.impl;

import com.teamhelper.group.dao.GroupRepository;
import com.teamhelper.group.dto.Group;
import com.teamhelper.group.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository repository;

    @Autowired
    GroupServiceImpl(GroupRepository repository) {
        this.repository = repository;
    }


    @Override
    @Transactional
    public Group addGroupMember(String gid, String uid) {
        Optional<Group> groupOpt = repository.findById(gid);
        if (groupOpt.isPresent() == false) {
            //Exception
        }
        Group group = groupOpt.get();
        group.getUsers().add(uid);

        return repository.save(group);
    }

    @Override
    @Transactional
    public Group removeGroupMember(String gid, String uid) {
        Optional<Group> groupOpt = repository.findById(gid);
        if (groupOpt.isPresent() == false) {
            //Exception
        }
        Group group = groupOpt.get();
        group.getUsers().remove(uid);

        return repository.save(group);
    }

    @Override
    public List<Group> getGroupsContainUser(String uid) {
        return repository.findGroupsByUsersContains(uid);
    }

    @Override
    public List<Group> getGroupsByName(String groupName) {
        return repository.findByNameLike(groupName);
    }

    @Override
    public List<Group> getGroups() {
        return repository.findAllByOrderByGid();
    }

    @Override
    public Group makeGroup(Group group) {
        if(repository.existsByName(group.getName())){
            //exception
        }
        return repository.save(group);
    }
}
