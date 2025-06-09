package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.user;

import java.util.UUID;

public record LoginResponseDto(String token, UUID id, String email, String firstName, String lastName){
}
