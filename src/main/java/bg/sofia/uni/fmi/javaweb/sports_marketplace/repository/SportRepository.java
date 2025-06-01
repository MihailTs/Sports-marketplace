package bg.sofia.uni.fmi.javaweb.sports_marketplace.repository;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Sport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SportRepository extends JpaRepository<Sport, UUID> {
    Optional<Sport> findByName(String name);
}
