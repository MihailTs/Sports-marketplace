package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.cart;

public record CartItemCreateDto(
        Long productId,
        Integer quantity
) {}
