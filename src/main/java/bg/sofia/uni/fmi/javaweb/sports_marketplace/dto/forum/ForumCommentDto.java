package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.forum;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.ForumComment;

import java.time.LocalDateTime;
import java.util.UUID;

public record ForumCommentDto(UUID id, String content, String name, LocalDateTime createdAt) {
    public static ForumCommentDto fromEntity(ForumComment forumComment){
        return new ForumCommentDto(
                forumComment.getId(),
                forumComment.getContent(),
                forumComment.getUser().getFirstName()+" "+forumComment.getUser().getLastName(),
                forumComment.getCreatedAt());
    }
}
