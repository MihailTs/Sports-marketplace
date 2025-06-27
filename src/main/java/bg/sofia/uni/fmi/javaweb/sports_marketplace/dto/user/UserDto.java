package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.user;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.address.AddressDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Address;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Role;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.User;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserDto(
        UUID id,
        String email,
        Role role,
        String firstName,
        String lastName,
        String gender,
        String phoneNumber,
        AddressDto address,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String profileImageURL
) {
    public static UserDto fromEntity(User user) {
        return new UserDto(
                user.getId(),
                user.getEmail(),
                user.getRole(),
                user.getFirstName(),
                user.getLastName(),
                user.getGender(),
                user.getPhone(),
                user.getAddress()==null?null:AddressDto.fromEntity(user.getAddress()),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.getProfileImageUrl()
        );
    }
}