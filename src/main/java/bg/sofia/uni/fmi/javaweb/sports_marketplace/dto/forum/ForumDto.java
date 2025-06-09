package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.forum;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Forum;

import java.util.UUID;

public record ForumDto(UUID id, String title, String description, String sport) {
    public static ForumDto fromEntity(Forum forum){
        return new ForumDto(forum.getId(), forum.getTitle(), forum.getDescription(), forum.getSport().getName());
    }
}
