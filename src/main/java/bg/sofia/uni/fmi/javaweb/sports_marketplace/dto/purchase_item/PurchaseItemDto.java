package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.purchase_item;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.product.ProductSummaryDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.PurchaseItem;

import java.math.BigDecimal;

public record PurchaseItemDto(
        Long id,
        ProductSummaryDto product,
        Integer quantity,
        BigDecimal price
) {
    public static PurchaseItemDto fromEntity(PurchaseItem purchaseItem) {
        return new PurchaseItemDto(
                purchaseItem.getId(),
                purchaseItem.getProduct() != null ?
                        ProductSummaryDto.fromEntity(purchaseItem.getProduct()) : null,
                purchaseItem.getQuantity(),
                purchaseItem.getPrice()
        );
    }
}
