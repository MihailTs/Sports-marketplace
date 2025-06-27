package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.notification;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Notification;

import java.time.LocalDateTime;

public record NotificationDto(
        String type,
        String message
) {
    public static NotificationDto fromEntity(Notification notification) {
        return new NotificationDto(
                notification.getType(),
                notification.getMessage()
        );
    }
}
