package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.forum;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Forum;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Sport;

import java.util.UUID;

public record ForumDto(UUID id, String title, String description, Sport sport) {
    public static ForumDto fromEntity(Forum forum){
        return new ForumDto(forum.getId(), forum.getTitle(), forum.getDescription(), forum.getSport());
    }
}
