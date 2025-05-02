package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.promotion;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.product.ProductSummaryDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Promotion;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record PromotionCreateDto (
    String name,
    String description,
    BigDecimal discountAmount,
    String discountType,
    LocalDateTime startDate,
    LocalDateTime endDate,
    Boolean isActive,
    List<Long> productIds
){}