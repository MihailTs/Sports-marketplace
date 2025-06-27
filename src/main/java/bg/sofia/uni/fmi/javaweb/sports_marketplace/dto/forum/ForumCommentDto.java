package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.forum;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.ForumComment;

import java.time.LocalDateTime;
import java.util.UUID;

public record ForumCommentDto(UUID id, String content, UUID userId, String userName, LocalDateTime createdAt, UUID userId) {
    public static ForumCommentDto fromEntity(ForumComment forumComment){
        return new ForumCommentDto(
                forumComment.getId(),
                forumComment.getContent(),
                forumComment.getUser().getId(),
                forumComment.getUser().getFirstName()+" "+forumComment.getUser().getLastName(),
                forumComment.getCreatedAt(),
                forumComment.getUser().getId());
    }
}
