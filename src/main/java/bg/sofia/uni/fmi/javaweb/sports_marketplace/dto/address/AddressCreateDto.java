package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.address;

public record AddressCreateDto(
        String street,
        String city,
        String state,
        String zipCode,
        String country,
        Boolean isDefault
) {}
