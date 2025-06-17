package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.product;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.ProductVariant;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public record ProductVariantDto(
    UUID id,
    
    @NotBlank
    @Size(max = 50)
    String size,
    
    @NotBlank
    @Size(max = 50)
    String color,
    
    @NotNull
    @DecimalMin("0.0")
    BigDecimal price,
    
    @NotNull
    @Min(0)
    Integer stock
) {
    public static ProductVariantDto fromEntity(ProductVariant variant) {
        Objects.requireNonNull(variant, "ProductVariant cannot be null");
        return new ProductVariantDto(
            variant.getId(),
            variant.getSize(),
            variant.getColor(),
            variant.getPrice(),
            variant.getStock()
        );
    }

    public ProductVariant toEntity() {
        ProductVariant variant = new ProductVariant();
        variant.setSize(size);
        variant.setColor(color);
        variant.setPrice(price);
        variant.setStock(stock != null ? stock : 0);
        return variant;
    }
} 