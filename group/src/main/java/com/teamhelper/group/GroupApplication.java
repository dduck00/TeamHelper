package com.teamhelper.group;

import com.teamhelper.group.dao.GroupRepository;
import com.teamhelper.group.dto.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class GroupApplication implements CommandLineRunner {

    private final GroupRepository groupRepository;

    @Autowired
    GroupApplication(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(GroupApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {


    }
}
