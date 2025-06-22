package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Message;

import java.time.LocalDateTime;
import java.util.UUID;

public record MessageResponseDto (UUID chatId, String senderName, String content, LocalDateTime sentAt){
    public static MessageResponseDto fromEntity(Message message){
        return new MessageResponseDto(message.getChat().getId(), message.getSender().getFirstName()+" "+message.getSender().getLastName(), message.getContent(), message.getSentAt());
    }
}
