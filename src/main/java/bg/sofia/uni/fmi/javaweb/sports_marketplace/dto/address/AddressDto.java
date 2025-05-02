package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.address;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Address;

public record AddressDto(
        Long id,
        String street,
        String city,
        String state,
        String zipCode,
        String country,
        Boolean isDefault
) {
    public static AddressDto fromEntity(Address address) {
        return new AddressDto(
                address.getId(),
                address.getStreet(),
                address.getCity(),
                address.getState(),
                address.getZipCode(),
                address.getCountry(),
                address.getIsDefault()
        );
    }
}
