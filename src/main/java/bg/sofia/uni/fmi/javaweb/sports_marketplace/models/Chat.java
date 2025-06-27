package bg.sofia.uni.fmi.javaweb.sports_marketplace.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "chats")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private LocalDateTime createdAt;

    @JsonManagedReference
    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL)
    List<Message> messages;

    @JsonManagedReference
    @OneToMany(mappedBy = "chat", cascade = CascadeType.REMOVE)
    List<ChatParticipant> participants;

    @Override
    public String toString() {
        return "Chat{id=" + id + ", participantCount=" + (participants != null ? participants.size() : 0) + "}";
    }
}
