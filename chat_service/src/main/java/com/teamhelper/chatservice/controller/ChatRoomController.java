package com.teamhelper.chatservice.controller;

import com.teamhelper.chatservice.dao.ChatRoomRepository;
import com.teamhelper.chatservice.dto.ChatRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatRoomController {

    private final ChatRoomRepository chatRoomRepository;

    @Autowired
    public ChatRoomController(ChatRoomRepository chatRoomRepository){
        this.chatRoomRepository = chatRoomRepository;
    }

    @GetMapping("/rooms")
    public List<ChatRoom> room(){
        return chatRoomRepository.findAllRoom();
    }

    @PostMapping("/room")
    @ResponseBody
    public ChatRoom createRoom(@RequestParam String name){
        return chatRoomRepository.createChatRoom(name);
    }

    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable String roomId){
        return chatRoomRepository.findRoomById(roomId);
    }
}
