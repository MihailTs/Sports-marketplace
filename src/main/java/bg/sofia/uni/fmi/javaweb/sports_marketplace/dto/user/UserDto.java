package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.user;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.User;

public record UserDto(
        Long id,
        String email,
        String role
) {
    public static UserDto fromEntity(User user) {
        return new UserDto(
                user.getId(),
                user.getEmail(),
                user.getRole()
        );
    }
}