package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.user;

public record UserRegistrationDto(
        String email,
        String password,
        String confirmPassword
) {}