package bg.sofia.uni.fmi.javaweb.sports_marketplace.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(name = "event_participant")
public class EventParticipant {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    private LocalDateTime joinedAt;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    private Status status;

    public EventParticipant(User user, Event event, Status status){
        this.user=user;
        this.event=event;
        this.status=status;
    }

}
