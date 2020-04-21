package com.teamhelper.chatservice.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamhelper.chatservice.dto.Group;
import com.teamhelper.chatservice.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.*;

@Service
public class WebSocketServiceImpl implements WebSocketService {

    private final ObjectMapper objectMapper;
    private Map<String, Group> groups;

    @Autowired
    WebSocketServiceImpl(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
        groups = new LinkedHashMap<>();
    }

    @Override
    public Group findRoomById(String groupId) {
        return groups.get(groupId);
    }

    @Override
    public Group createRoom(String groupId) {
        Group group = new Group(groupId);
        groups.put(groupId, group);
        return group;
    }


}
