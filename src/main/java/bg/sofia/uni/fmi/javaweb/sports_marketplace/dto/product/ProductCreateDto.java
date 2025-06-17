package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.product;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.ProductCondition;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record ProductCreateDto(
    @NotBlank
    @Size(min = 3, max = 255)
    String name,
    @Size(max = 1000)
    String description,
    @NotNull
    @DecimalMin("0.0")
    BigDecimal price,
    @NotNull
    ProductCondition condition,
    @NotNull
    UUID categoryId,
    @Valid
    List<ProductVariantDto> variants,
    List<@URL String> imageUrls
) {}

