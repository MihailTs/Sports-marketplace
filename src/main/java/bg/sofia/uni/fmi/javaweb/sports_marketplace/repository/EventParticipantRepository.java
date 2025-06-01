package bg.sofia.uni.fmi.javaweb.sports_marketplace.repository;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.EventParticipant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventParticipantRepository extends JpaRepository<EventParticipant, UUID> {
    List<EventParticipant> findAllByEventId(UUID eventId);
    Optional<EventParticipant> findEventParticipantsByEvent_IdAndUser_Id(UUID eventId, UUID userId);
}
