package bg.sofia.uni.fmi.javaweb.sports_marketplace.repository;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Address;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    long countByAddress(Address address);
}
