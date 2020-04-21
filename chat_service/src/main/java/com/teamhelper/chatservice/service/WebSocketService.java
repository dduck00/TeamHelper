package com.teamhelper.chatservice.service;

import com.teamhelper.chatservice.dto.Group;

public interface WebSocketService {
    public Group findRoomById(String roomId);
    public Group createRoom(String name);
}
