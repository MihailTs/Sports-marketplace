package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.order;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.order_item.OrderItemCreateDto;

import java.util.List;

public record OrderCreateDto(
        Long userId,
        Long shippingAddressId,
        List<OrderItemCreateDto> items,
        String paymentMethod
) {}
