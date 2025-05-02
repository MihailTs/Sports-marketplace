package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.order;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderSummaryDto(
        Long id,
        LocalDateTime orderDate,
        String status,
        BigDecimal totalAmount
) {
    public static OrderSummaryDto fromEntity(Order order) {
        return new OrderSummaryDto(
                order.getId(),
                order.getOrderDate(),
                order.getStatus(),
                order.getTotalAmount()
        );
    }
}
