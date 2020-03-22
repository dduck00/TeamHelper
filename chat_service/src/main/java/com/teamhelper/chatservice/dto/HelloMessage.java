package com.teamhelper.chatservice.dto;

public class HelloMessage {
    private String name;

    HelloMessage(){

    }
    HelloMessage(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
