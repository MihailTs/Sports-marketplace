package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.inventory_movement;

public record InventoryMovementCreateDto(
        String movementType,
        Integer quantity,
        Long productId,
        Long warehouseId,
        String reference
) {}
