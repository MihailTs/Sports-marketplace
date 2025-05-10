package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.product;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Product;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Review;

import java.math.BigDecimal;

public record ProductSummaryDto(
        Long id,
        String name,
        BigDecimal price,
        String imageUrl,
        Double averageRating
) {
    public static ProductSummaryDto fromEntity(Product product) {
        // Calculate average rating from reviews
        Double avgRating = product.getReviews() != null && !product.getReviews().isEmpty() ?
                product.getReviews().stream()
                        .mapToInt(Review::getRating)
                        .average()
                        .orElse(0.0) : 0.0;

        return new ProductSummaryDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getImageUrl(),
                avgRating
        );
    }
}
