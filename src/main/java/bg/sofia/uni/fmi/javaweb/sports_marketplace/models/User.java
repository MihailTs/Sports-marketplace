package bg.sofia.uni.fmi.javaweb.sports_marketplace.models;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.address.AddressDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.Date;
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
    private String name;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private Date birthDate;
    private String phoneNumber;
    private String gender;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public User(String email, String name, String password, Role role, String gender, String phoneNumber, Address address, Date birthDate){
        this.email=email;
        this.name=name;
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