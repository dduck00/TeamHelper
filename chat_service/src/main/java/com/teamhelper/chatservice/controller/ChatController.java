package com.teamhelper.chatservice.controller;

import com.teamhelper.chatservice.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    private final SimpMessageSendingOperations messagingTemplate;

    @Autowired
    public ChatController(SimpMessageSendingOperations messagingTemplate ){
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/get/msg")
    public void message(MessageDto messageDto) {
        if (MessageDto.MessageType.ENTER.equals(messageDto.getType()))
            messageDto.setMessage(messageDto.getSender() + "님이 입장하셨습니다.");
        messagingTemplate.convertAndSend("/sub/group/" + messageDto.getRoomId(), messageDto);
    }

}
