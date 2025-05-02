package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.supplier;

public record SupplierCreateDto(
        String name,
        String contact,
        String email,
        String phone,
        String address
) {}
