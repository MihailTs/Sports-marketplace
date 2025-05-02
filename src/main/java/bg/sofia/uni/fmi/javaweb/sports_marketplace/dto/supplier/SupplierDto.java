package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.supplier;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Supplier;

public record SupplierDto(
        Long id,
        String name,
        String contact,
        String email,
        String phone,
        String address
) {
    public static SupplierDto fromEntity(Supplier supplier) {
        return new SupplierDto(
                supplier.getId(),
                supplier.getName(),
                supplier.getContact(),
                supplier.getEmail(),
                supplier.getPhone(),
                supplier.getAddress()
        );
    }
}

