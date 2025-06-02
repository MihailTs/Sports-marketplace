package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.user;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.address.AddressDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Address;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.User;

public record UserDto(
        Long id,
        String email,
        String role,
        String firstName,
        String lastName,
        String gender,
        String phoneNumber,
        AddressDto address
) {
    public static UserDto fromEntity(User user) {
        return new UserDto(
                user.getId(),
                user.getEmail(),
                user.getRole(),
                user.getFirstName(),
                user.getLastName(),
                user.getGender(),
                user.getPhoneNumber(),
                user.getAddress()==null?null:AddressDto.fromEntity(user.getAddress())
        );
    }
}