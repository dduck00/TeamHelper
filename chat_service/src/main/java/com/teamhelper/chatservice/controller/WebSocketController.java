package com.teamhelper.chatservice.controller;

import com.teamhelper.chatservice.dto.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    private final SimpMessageSendingOperations messagingTemplate;

    @Autowired
    public WebSocketController(SimpMessageSendingOperations messagingTemplate ){
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/get/msg")
    public void MessageMapping(Message message) {
        if (Message.MessageType.ENTER.equals(message.getType()))
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
        messagingTemplate.convertAndSend("/sub/group/" + message.getRoomId(), message);
    }

}
