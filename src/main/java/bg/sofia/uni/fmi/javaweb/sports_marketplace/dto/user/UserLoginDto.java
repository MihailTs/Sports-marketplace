package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.user;

import jakarta.validation.constraints.NotBlank;

public record UserLoginDto(
        @NotBlank
        String email,
        @NotBlank
        String password
) {}
