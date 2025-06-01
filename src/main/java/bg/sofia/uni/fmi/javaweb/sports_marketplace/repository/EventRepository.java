package bg.sofia.uni.fmi.javaweb.sports_marketplace.repository;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
    List<Event> findByCreatedById(UUID id);
}
