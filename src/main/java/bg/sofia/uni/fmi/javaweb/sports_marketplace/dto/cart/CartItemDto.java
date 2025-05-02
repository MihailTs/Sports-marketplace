package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.cart;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.product.ProductSummaryDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.CartItem;

import java.math.BigDecimal;

public record CartItemDto(
        Long id,
        ProductSummaryDto product,
        Integer quantity,
        BigDecimal totalPrice
) {
    public static CartItemDto fromEntity(CartItem cartItem) {
        ProductSummaryDto productDto = cartItem.getProduct() != null ?
                ProductSummaryDto.fromEntity(cartItem.getProduct()) : null;

        BigDecimal totalPrice = productDto != null ?
                productDto.price().multiply(new BigDecimal(cartItem.getQuantity())) :
                BigDecimal.ZERO;

        return new CartItemDto(
                cartItem.getId(),
                productDto,
                cartItem.getQuantity(),
                totalPrice
        );
    }
}
