package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.product;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.category.CategoryDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Product;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Review;

import java.math.BigDecimal;

public record ProductDto(
        Long id,
        String name,
        String description,
        BigDecimal price,
        Integer stockQuantity,
        String imageUrl,
        CategoryDto category,
        Double averageRating
) {
    public static ProductDto fromEntity(Product product) {
        // Calculate average rating from reviews
        Double avgRating = product.getReviews() != null && !product.getReviews().isEmpty() ?
                product.getReviews().stream()
                        .mapToInt(Review::getRating)
                        .average()
                        .orElse(0.0) : 0.0;

        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStockQuantity(),
                product.getImageUrl(),
                product.getCategory() != null ? CategoryDto.fromEntity(product.getCategory()) : null,
                avgRating
        );
    }
}
