package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.forum;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.ForumPost;

import java.util.UUID;

public record ForumPostDto(UUID id, String title, String content, String userName, UUID userId) {
    public static ForumPostDto fromEntity(ForumPost forumPost){
        return new ForumPostDto(forumPost.getId(), forumPost.getTitle(), forumPost.getContent(), forumPost.getUser().getFirstName()+" "+forumPost.getUser().getLastName(), forumPost.getUser().getId());
    }
}
