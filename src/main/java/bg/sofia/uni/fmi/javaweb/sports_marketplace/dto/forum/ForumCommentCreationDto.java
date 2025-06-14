package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.forum;

import java.util.UUID;

public record ForumCommentCreationDto(UUID userId, String content) {
}
