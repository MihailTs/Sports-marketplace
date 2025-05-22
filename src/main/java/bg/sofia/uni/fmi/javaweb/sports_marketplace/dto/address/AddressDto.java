package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.address;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Address;

public record AddressDto(
        String street,
        String city,
        String state,
        String zipCode,
        String country
) {
    public static AddressDto fromEntity(Address address) {
        return new AddressDto(
                address.getStreet(),
                address.getCity(),
                address.getState(),
                address.getZipCode(),
                address.getCountry()
        );
    }

    public static Address toEntity(AddressDto addressDto) {
        Address address = new Address();
        address.setCity(addressDto.city);
        address.setStreet(addressDto.street);
        address.setCountry(addressDto.country);
        address.setZipCode(addressDto.zipCode);
        address.setState(addressDto.state);
        return address;
    }
}
