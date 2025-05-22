package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.user;

public record UserRegistrationDto(
        String email,
        String name,
        String password,
        String confirmPassword,
        String role
) {}