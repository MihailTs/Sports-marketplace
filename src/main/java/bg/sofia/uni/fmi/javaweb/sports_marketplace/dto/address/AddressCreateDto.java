package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.address;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Address;

public record AddressCreateDto(
        String street,
        String city,
        String state,
        String zipCode,
        String country
){

        public static Address toEntity(AddressCreateDto addressCreateDto){
        Address address = new Address();
        address.setCity(addressCreateDto.city);
        address.setStreet(addressCreateDto.street);
        address.setCountry(addressCreateDto.country);
        address.setZipCode(addressCreateDto.zipCode);
        address.setState(addressCreateDto.state);
        return address;
}}
