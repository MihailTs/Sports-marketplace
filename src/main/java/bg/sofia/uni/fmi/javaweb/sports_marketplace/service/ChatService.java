package bg.sofia.uni.fmi.javaweb.sports_marketplace.service;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.MessageDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.MessageResponseDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.exceptions.ChatNotFoundException;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.exceptions.UserDoesntExistException;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Chat;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.ChatParticipant;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Message;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.User;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.repository.ChatParticipantRepository;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.repository.ChatRepository;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.repository.MessageRepository;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ChatService {
    private ChatRepository chatRepository;
    private ChatParticipantRepository chatParticipantRepository;
    private MessageRepository messageRepository;
    private UserRepository userRepository;
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    public ChatService(ChatRepository chatRepository, ChatParticipantRepository chatParticipantRepository, MessageRepository messageRepository, UserRepository userRepository, SimpMessagingTemplate messagingTemplate){
        this.chatRepository=chatRepository;
        this.chatParticipantRepository=chatParticipantRepository;
        this.messageRepository=messageRepository;
        this.userRepository=userRepository;
        this.messagingTemplate=messagingTemplate;
    }

    public List<Message> getMessages(UUID chatId, LocalDateTime before, int size){
        if (before == null) {
            before = LocalDateTime.now();
        }

        Pageable limit= PageRequest.of(0, size, Sort.by("sentAt").descending());

        return messageRepository.findByChatIdAndSentAtBefore(chatId, before, limit);
    }

    public Chat createChat(List<UUID> usersId, String initializerEmail){

        System.out.println("email:"+initializerEmail);
        Chat chat=new Chat();
        chat.setCreatedAt(LocalDateTime.now());
        chatRepository.save(chat);

        User initializer=userRepository.findByEmail(initializerEmail).orElseThrow(UserDoesntExistException::new);
        chatParticipantRepository.save(new ChatParticipant(null, chat, initializer));

        System.out.println("initializer:"+initializer.getFirstName());

        for(UUID userId: usersId){
            User user=userRepository.findById(userId).orElseThrow(UserDoesntExistException::new);
            chatParticipantRepository.save(new ChatParticipant(null, chat, user));
            System.out.println("firstname"+user.getFirstName());
        }


        return chat;
    }

    public void deleteChat(UUID chatId){
        if(!chatRepository.existsById(chatId)){
            throw new ChatNotFoundException();
        }
        chatRepository.deleteById(chatId);
    }

    public Chat getChat(UUID chatId){
        return chatRepository.findById(chatId).orElseThrow(ChatNotFoundException::new);
    }

    public void sendMessage(MessageDto messageDto, String senderEmail){
        Chat chat=chatRepository.findById(messageDto.chatId()).orElseThrow(ChatNotFoundException::new);
        User sender=userRepository.findByEmail(senderEmail).orElseThrow(UserDoesntExistException::new);

        Message message=messageRepository.save(new Message(null, chat, sender, messageDto.content(), LocalDateTime.now(), null));

        MessageResponseDto response=MessageResponseDto.fromEntity(message);

        System.out.println(messageDto);

        messagingTemplate.convertAndSend("/topic/chat." + messageDto.chatId(), response);

    }

    @Transactional
    public Chat findChatBetweenUsers(String initializerEmail, UUID userId2) {
        User user=userRepository.findByEmail(initializerEmail).orElseThrow(UserDoesntExistException::new);
        return chatRepository.findOneOnOneChat(user.getId(), userId2).orElseThrow(ChatNotFoundException::new);

    }

}
