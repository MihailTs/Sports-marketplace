package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.warehouse;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Warehouse;

public record WarehouseDto(
        Long id,
        String name,
        String location,
        String contact
) {
    public static WarehouseDto fromEntity(Warehouse warehouse) {
        return new WarehouseDto(
                warehouse.getId(),
                warehouse.getName(),
                warehouse.getLocation(),
                warehouse.getContact()
        );
    }
}

