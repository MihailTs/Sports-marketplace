package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.forum;


import java.util.UUID;

public record ForumCreationDto(String title, String description, UUID sportId) {
}
