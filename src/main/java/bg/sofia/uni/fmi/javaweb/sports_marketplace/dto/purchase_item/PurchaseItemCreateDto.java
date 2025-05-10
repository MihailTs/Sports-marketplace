package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.purchase_item;

import java.math.BigDecimal;

public record PurchaseItemCreateDto(
        Long productId,
        Integer quantity,
        BigDecimal price
) {}
