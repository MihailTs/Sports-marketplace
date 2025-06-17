package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.product;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.ProductImage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

import java.util.Objects;
import java.util.UUID;

public record ProductImageDto(
    UUID id,
    
    @NotBlank
    @URL
    String url,
    
    @NotNull
    Boolean isPrimary
) {
    public static ProductImageDto fromEntity(ProductImage image) {
        Objects.requireNonNull(image, "ProductImage cannot be null");
        return new ProductImageDto(
            image.getId(),
            image.getUrl(),
            image.getIsPrimary()
        );
    }

    public ProductImage toEntity() {
        ProductImage image = new ProductImage();
        image.setUrl(url);
        image.setIsPrimary(isPrimary != null ? isPrimary : false);
        return image;
    }
} 