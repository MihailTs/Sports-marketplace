package bg.sofia.uni.fmi.javaweb.sports_marketplace.controllers;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.MessageDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.exceptions.UserDoesntExistException;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Message;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.repository.UserRepository;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.service.ChatService;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.service.UserService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.time.LocalDateTime;


@Controller
public class ChatWebSocketController {
    private ChatService chatService;
    public ChatWebSocketController(ChatService chatService){
        this.chatService=chatService;
    }
    @MessageMapping("chat.sendMessage")
    public void sendMessage(@Payload MessageDto message, Principal principal) {
        String senderEmail=principal.getName();
        System.out.println("Received message: " + message.content());
        chatService.sendMessage(message, senderEmail);
    }
}
