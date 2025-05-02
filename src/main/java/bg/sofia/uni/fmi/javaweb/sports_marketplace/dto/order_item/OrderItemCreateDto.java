package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.order_item;

public record OrderItemCreateDto(
        Long productId,
        Integer quantity
) {}