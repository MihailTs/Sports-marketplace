package bg.sofia.uni.fmi.javaweb.sports_marketplace.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.id.uuid.UuidGenerator;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String description;
    private String location;
    @Column(name = "start_datetime")
    private LocalDateTime startTime;
    @Column(name = "end_datetime")
    private LocalDateTime endTime;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    private int capacity;
    @ManyToOne
    @JoinColumn(name="sport_id")
    private Sport sport;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="created_by_id", nullable = false)
    private User createdBy;

    public Event(String title, String description, String location, LocalDateTime startTime, LocalDateTime endTime, User createdBy, Sport sport, int capacity){
        this.title=title;
        this.description=description;
        this.location=location;
        this.startTime=startTime;
        this.endTime=endTime;
        this.createdBy=createdBy;
        this.createdAt=LocalDateTime.now();
        this.capacity=capacity;
        this.sport=sport;
    }

}
