package bg.sofia.uni.fmi.javaweb.sports_marketplace.repository;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
    List<Event> findByCreatedById(UUID id);
    @Modifying
    @Query("UPDATE Event e SET e.createdBy = :deletedUserId WHERE e.createdBy = :originalUserId")
    void updateUserToDeleted(@Param("originalUserId") UUID originalUserId,
                             @Param("deletedUserId") UUID deletedUserId);

    @Modifying
    @Query("Delete Event e WHERE e.createdBy = :originalUserId AND e.endTime>:nowTime")
    void deleteEventsBy(@Param("originalUserId") UUID originalUserId,
                             @Param("nowTime")LocalDateTime nowTime);


}
