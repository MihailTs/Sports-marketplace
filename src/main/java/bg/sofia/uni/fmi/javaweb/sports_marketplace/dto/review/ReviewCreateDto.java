package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.review;

public record ReviewCreateDto(
        Long productId,
        Integer rating,
        String comment
) {}

