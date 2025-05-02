package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.inventory_movement;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.product.ProductSummaryDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.warehouse.WarehouseDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.InventoryMovement;

import java.time.LocalDateTime;

public record InventoryMovementDto(
        Long id,
        String movementType,
        Integer quantity,
        LocalDateTime movementDate,
        String reference,
        ProductSummaryDto product,
        WarehouseDto warehouse
) {
    public static InventoryMovementDto fromEntity(InventoryMovement movement) {
        return new InventoryMovementDto(
                movement.getId(),
                movement.getMovementType(),
                movement.getQuantity(),
                movement.getMovementDate(),
                movement.getReference(),
                movement.getProduct() != null ?
                        ProductSummaryDto.fromEntity(movement.getProduct()) : null,
                movement.getWarehouse() != null ?
                        WarehouseDto.fromEntity(movement.getWarehouse()) : null
        );
    }
}
