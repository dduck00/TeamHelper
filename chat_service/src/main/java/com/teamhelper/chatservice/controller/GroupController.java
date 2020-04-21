package com.teamhelper.chatservice.controller;

import com.teamhelper.chatservice.dto.Group;
import com.teamhelper.chatservice.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class GroupController {

    private final WebSocketService webSocketService;

    @Autowired
    public GroupController(WebSocketService webSocketService){
        this.webSocketService = webSocketService;
    }

    @PostMapping
    public Group createGroup(@RequestParam String gid){
        return webSocketService.createRoom(gid);
    }

}
