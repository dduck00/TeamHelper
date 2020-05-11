package com.teamhelper.group.dto;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class Group {
    @Id
    private String gid;

    private String name;
    private String master;
    private List<String> users;

    public Group(){

    }
    public Group(String name, String user) {
        this.users = new ArrayList<>();
        users.add(user);
        master = user;
        this.name = name;
    }

    public String getGid() {
        return gid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaster() {
        return master;
    }

    public List<String> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return "Group{" +
                "gid='" + gid + '\'' +
                ", users=" + users +
                '}';
    }
}
