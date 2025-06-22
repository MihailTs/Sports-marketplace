package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.forum;

import java.util.UUID;

public record ForumPostCreationDto(UUID userId, String title, String content) {
}