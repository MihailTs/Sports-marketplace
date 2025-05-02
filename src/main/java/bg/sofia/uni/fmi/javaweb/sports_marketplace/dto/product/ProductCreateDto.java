package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.product;

import java.math.BigDecimal;

public record ProductCreateDto(
        String name,
        String description,
        BigDecimal price,
        Integer stockQuantity,
        String imageUrl,
        Long categoryId
) {}

