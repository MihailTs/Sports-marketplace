package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.product;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Product;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.ProductImage;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Review;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public record ProductSummaryDto(
    UUID id,
    String name,
    BigDecimal price,
    ProductImageDto primaryImage,
    Double averageRating
) {
    public static ProductSummaryDto fromEntity(Product product) {
        Objects.requireNonNull(product, "Product cannot be null");
        
        ProductImageDto primaryImage = product.getImages() != null ? 
            product.getImages().stream()
                .filter(ProductImage::isPrimary)
                .findFirst()
                .map(ProductImageDto::fromEntity)
                .orElse(null) : null;
                
        Double avgRating = product.getReviews() != null && !product.getReviews().isEmpty() ?
            product.getReviews().stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0) : 0.0;

        return new ProductSummaryDto(
            product.getId(),
            product.getName(),
            product.getPrice(),
            primaryImage,
            avgRating
        );
    }
}
