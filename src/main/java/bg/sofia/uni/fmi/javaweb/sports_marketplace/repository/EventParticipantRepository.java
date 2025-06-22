package bg.sofia.uni.fmi.javaweb.sports_marketplace.repository;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.EventParticipant;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Status;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Stack;
import java.util.UUID;

public interface EventParticipantRepository extends JpaRepository<EventParticipant, UUID> {
    List<EventParticipant> findAllByEventId(UUID eventId);
    Optional<EventParticipant> findEventParticipantsByEvent_IdAndUser_Id(UUID eventId, UUID userId);

    Optional<EventParticipant> findFirstByEventIdAndStatusOrderByJoinedAtAsc(UUID id, Status status);

    @Modifying
    @Query("DELETE FROM EventParticipant e WHERE e.user.id = :userId")
    void deleteAllByUserId(@Param("userId") UUID userId);
}
