package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.user;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserRegistrationDto(
        String email,
        String firstName,
        String lastName,
        LocalDate birthdate,
        String password,
        String confirmPassword,
        String phone,
        String gender,
        String role
) {}