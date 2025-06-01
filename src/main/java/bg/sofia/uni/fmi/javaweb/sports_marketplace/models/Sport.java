package bg.sofia.uni.fmi.javaweb.sports_marketplace.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name="sport")
@Data
@NoArgsConstructor
public class Sport {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;

    public Sport(String name){
        this.name=name;
    }
}
