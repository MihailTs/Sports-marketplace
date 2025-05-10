package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.review;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.user.UserDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Review;

import java.time.LocalDateTime;

public record ReviewDto(
        Long id,
        Integer rating,
        String comment,
        LocalDateTime reviewDate,
        UserDto user
) {
    public static ReviewDto fromEntity(Review review) {
        return new ReviewDto(
                review.getId(),
                review.getRating(),
                review.getComment(),
                review.getReviewDate(),
                review.getUser() != null ? UserDto.fromEntity(review.getUser()) : null
        );
    }
}