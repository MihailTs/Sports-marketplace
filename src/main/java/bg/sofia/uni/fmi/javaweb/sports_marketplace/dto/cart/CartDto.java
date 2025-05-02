package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.cart;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.user.UserDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Cart;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public record CartDto(
        Long id,
        UserDto user,
        List<CartItemDto> items,
        BigDecimal totalAmount
) {
    public static CartDto fromEntity(Cart cart) {
        List<CartItemDto> cartItems = cart.getCartItems() != null ?
                cart.getCartItems().stream()
                        .map(CartItemDto::fromEntity)
                        .collect(Collectors.toList()) :
                List.of();

        BigDecimal total = cartItems.stream()
                .map(CartItemDto::totalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new CartDto(
                cart.getId(),
                cart.getUser() != null ? UserDto.fromEntity(cart.getUser()) : null,
                cartItems,
                total
        );
    }
}
