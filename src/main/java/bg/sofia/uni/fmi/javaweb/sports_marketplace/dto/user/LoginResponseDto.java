package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.user;

public record LoginResponseDto(String token, Long id, String email, String firstName, String lastName){
}
