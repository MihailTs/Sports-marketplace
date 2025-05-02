package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.order;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.address.AddressDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.order_item.OrderItemDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.payment.PaymentDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.shipment.ShipmentDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.user.UserDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record OrderDto(
        Long id,
        LocalDateTime orderDate,
        String status,
        BigDecimal totalAmount,
        UserDto user,
        AddressDto shippingAddress,
        List<OrderItemDto> orderItems,
        PaymentDto payment,
        ShipmentDto shipment
) {
    public static OrderDto fromEntity(Order order) {
        return new OrderDto(
                order.getId(),
                order.getOrderDate(),
                order.getStatus(),
                order.getTotalAmount(),
                order.getUser() != null ? UserDto.fromEntity(order.getUser()) : null,
                order.getShippingAddress() != null ? AddressDto.fromEntity(order.getShippingAddress()) : null,
                order.getOrderItems() != null ?
                        order.getOrderItems().stream()
                                .map(OrderItemDto::fromEntity)
                                .collect(Collectors.toList()) :
                        List.of(),
                order.getPayment() != null ? PaymentDto.fromEntity(order.getPayment()) : null,
                order.getShipment() != null ? ShipmentDto.fromEntity(order.getShipment()) : null
        );
    }
}
