package com.teamhelper.chatservice.dto;

import java.util.UUID;

public class ChatRoom {
    private String roomId;
    private String name;

    public String getName() {
        return name;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public static ChatRoom create(String name){
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.roomId = UUID.randomUUID().toString();
        chatRoom.name= name;
        return chatRoom;
    }
}
