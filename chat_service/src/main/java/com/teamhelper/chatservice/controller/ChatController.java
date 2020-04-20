package com.teamhelper.chatservice.controller;

import com.teamhelper.chatservice.dto.ChatMessage;
import com.teamhelper.chatservice.feign.ViewFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/chat/")
public class ChatController {

    private final SimpMessageSendingOperations messagingTemplate;
    private final ViewFeign viewFeign;

    @Autowired
    public ChatController(SimpMessageSendingOperations messagingTemplate, ViewFeign viewFeign){
        this.messagingTemplate = messagingTemplate;
        this.viewFeign = viewFeign;
    }

    @GetMapping("/")
    public ModelAndView mainPage(){
        return viewFeign.chatView();
    }

    @MessageMapping("/message")
    public void message(ChatMessage message) {
        if (ChatMessage.MessageType.ENTER.equals(message.getType()))
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }

    @GetMapping("/room")
    public String rooms(Model model){
        return "/chat/room";
    }

    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable String roomId){
        model.addAttribute("roomId", roomId);
        return "/roomdetail";
    }

}
