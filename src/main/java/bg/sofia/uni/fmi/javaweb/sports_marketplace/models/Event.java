package bg.sofia.uni.fmi.javaweb.sports_marketplace.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;
    @Getter
    private String title;
    @Getter
    private String description;
    @Getter
    private String location;
    @Getter
    private LocalDateTime startTime;
    @Getter
    private LocalDateTime endTime;
    @Getter
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="userId", nullable = false)
    private User user;

    public Event(String title, String description, String location, LocalDateTime startTime, LocalDateTime endTime, User user){
        this.title=title;
        this.description=description;
        this.location=location;
        this.startTime=startTime;
        this.endTime=endTime;
        this.user=user;
    }

}
