package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.notification;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Notification;

import java.time.LocalDateTime;

public record NotificationDto(
        Long id,
        String type,
        String message,
        Boolean isRead,
        LocalDateTime createdAt
) {
    public static NotificationDto fromEntity(Notification notification) {
        return new NotificationDto(
                notification.getId(),
                notification.getType(),
                notification.getMessage(),
                notification.getIsRead(),
                notification.getCreatedAt()
        );
    }
}
