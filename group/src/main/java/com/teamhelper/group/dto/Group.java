package com.teamhelper.group.dto;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Group {
    @Id
    public String gid;

    public List<String> users;

    public Group(){

    }
    public Group(String gid, List<String> users) {
        this.gid = gid;
        this.users = users;
    }

    @Override
    public String toString() {
        return "Group{" +
                "gid='" + gid + '\'' +
                ", users=" + users +
                '}';
    }
}
