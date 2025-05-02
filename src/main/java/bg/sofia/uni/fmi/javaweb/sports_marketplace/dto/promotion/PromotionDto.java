package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.promotion;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.product.ProductSummaryDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Promotion;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record PromotionDto(
        Long id,
        String name,
        String description,
        BigDecimal discountAmount,
        String discountType,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Boolean isActive,
        List<ProductSummaryDto> products
) {
    public static PromotionDto fromEntity(Promotion promotion) {
        return new PromotionDto(
                promotion.getId(),
                promotion.getName(),
                promotion.getDescription(),
                promotion.getDiscountAmount(),
                promotion.getDiscountType(),
                promotion.getStartDate(),
                promotion.getEndDate(),
                promotion.getIsActive(),
                promotion.getProducts() != null ?
                        promotion.getProducts().stream()
                                .map(ProductSummaryDto::fromEntity)
                                .collect(Collectors.toList()) :
                        List.of()
        );
    }
}
