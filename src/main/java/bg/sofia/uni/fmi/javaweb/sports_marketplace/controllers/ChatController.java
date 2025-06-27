package bg.sofia.uni.fmi.javaweb.sports_marketplace.controllers;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.ChatDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.MessageResponseDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Chat;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Message;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.service.ChatService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/chat")
public class ChatController {
    private ChatService chatService;
    public ChatController(ChatService chatService){
        this.chatService=chatService;
    }

    @GetMapping("/{chatId}/messages")
    public ResponseEntity<List<MessageResponseDto>> getMessagesForChat(@PathVariable UUID chatId,
                                                             @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime before,
                                                             @RequestParam(defaultValue = "50") int size){

        return ResponseEntity.ok(chatService.getMessages(chatId, before, size).stream().map(MessageResponseDto::fromEntity).toList());
    }

    @DeleteMapping("/{chatId}")
    public ResponseEntity<String> deleteChat(@PathVariable UUID chatId){
        chatService.deleteChat(chatId);
        return ResponseEntity.ok("Successfully deleted");
    }

    @GetMapping("/{chatId}")
    public ResponseEntity<Chat> getChat(@PathVariable UUID chatId){
        return ResponseEntity.ok(chatService.getChat(chatId));
    }

    @PostMapping
    public ResponseEntity<Chat> createChat(@RequestBody ChatDto chatDto, Authentication authentication){
        return ResponseEntity.ok(chatService.createChat(chatDto.users(), authentication.getName()));
    }

    @GetMapping("/with-user/{userId}")
    public ResponseEntity<Chat> getChatWithUser(@PathVariable UUID userId, Authentication authentication) {
        System.out.println(chatService.findChatBetweenUsers(authentication.getName(), userId));
        return ResponseEntity.ok(chatService.findChatBetweenUsers(authentication.getName(), userId));
    }
}
