package bg.sofia.uni.fmi.javaweb.sports_marketplace.service;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.notification.NotificationDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.exceptions.UserDoesntExistException;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Notification;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.User;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.repository.NotificationRepository;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.repository.UserRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class NotificationService {
    private UserRepository userRepository;
    private NotificationRepository notificationRepository;
    private SimpMessagingTemplate messagingTemplate;
    public NotificationService(NotificationRepository notificationRepository, UserRepository userRepository, SimpMessagingTemplate messagingTemplate){
        this.notificationRepository=notificationRepository;
        this.userRepository=userRepository;
        this.messagingTemplate=messagingTemplate;
    }

    public List<Notification> getAllNotifications(UUID userId){
        return notificationRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    public List<Notification> getAllUnreadNotification(UUID userId){
        return notificationRepository.findAllByUserIdAndIsReadFalseOrderByCreatedAtDesc(userId);
    }


    public void sendNotification(UUID userId, NotificationDto notificationDto){

        User user=userRepository.findById(userId).orElseThrow(UserDoesntExistException::new);
        Notification notification=notificationRepository.save(new Notification(null, notificationDto.type(), notificationDto.message(), false, LocalDateTime.now(), user));
        messagingTemplate.convertAndSend("/topic/notifications/"+user.getId(), notification);
    }
}
