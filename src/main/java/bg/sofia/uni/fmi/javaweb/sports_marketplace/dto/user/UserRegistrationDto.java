package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.user;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.address.AddressCreateDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

public record UserRegistrationDto(
        @NotBlank
        @Email
        String email,
        @NotBlank
        String name,
        @NotBlank
        String password,
        @NotBlank
        String confirmPassword,
        Role role,
        String gender,
        String phoneNumber,
        AddressCreateDto addressCreateDto,
        Date birthDate
) {}