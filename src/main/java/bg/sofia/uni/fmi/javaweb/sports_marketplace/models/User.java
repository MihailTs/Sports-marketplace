package bg.sofia.uni.fmi.javaweb.sports_marketplace.models;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.address.AddressDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private LocalDate birthDate;
    private String phoneNumber;
    private String gender;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public User(String email, String firstName, String lastName, String password, Role role, String gender, String phoneNumber, Address address, LocalDate birthDate){
        this.email=email;
        this.firstName=firstName;
        this.lastName=lastName;
        this.password=password;
        this.role=role;
        this.gender=gender;
        this.phoneNumber=phoneNumber;
        this.address=address;
        this.birthDate=birthDate;
    }

    //@OneToMany(mappedBy = "user")
    //private List<Purchase> orders;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="address")
    private Address address;

    @OneToMany(mappedBy = "recepient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> receivedReviews;

    //@OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<Review> writtenReviews;

    @Getter
    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<Event> events;

    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null||getClass()!=o.getClass()) return false;

        User user=(User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }
}