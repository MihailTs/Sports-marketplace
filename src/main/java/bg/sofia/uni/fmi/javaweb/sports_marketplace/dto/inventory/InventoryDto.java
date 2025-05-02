package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.inventory;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.product.ProductSummaryDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Inventory;

public record InventoryDto(
        Long id,
        ProductSummaryDto product,
        Integer quantity,
        String location
) {
    public static InventoryDto fromEntity(Inventory inventory) {
        return new InventoryDto(
                inventory.getId(),
                inventory.getProduct() != null ?
                        ProductSummaryDto.fromEntity(inventory.getProduct()) : null,
                inventory.getQuantity(),
                inventory.getLocation()
        );
    }
}

