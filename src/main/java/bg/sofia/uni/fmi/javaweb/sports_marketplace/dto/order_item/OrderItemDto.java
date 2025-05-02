package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.order_item;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.product.ProductSummaryDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.OrderItem;

import java.math.BigDecimal;

public record OrderItemDto(
        Long id,
        ProductSummaryDto product,
        Integer quantity,
        BigDecimal price
) {
    public static OrderItemDto fromEntity(OrderItem orderItem) {
        return new OrderItemDto(
                orderItem.getId(),
                orderItem.getProduct() != null ?
                        ProductSummaryDto.fromEntity(orderItem.getProduct()) : null,
                orderItem.getQuantity(),
                orderItem.getPrice()
        );
    }
}
