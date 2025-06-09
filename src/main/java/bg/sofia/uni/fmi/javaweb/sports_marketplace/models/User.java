package bg.sofia.uni.fmi.javaweb.sports_marketplace.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique = true)
    private String email;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(name="birth_date")
    private LocalDate birthDate;
    private String phone;
    private String gender;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name="updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name="address_id")
    private Address address;

    @OneToMany(mappedBy = "recepient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> receivedReviews;

    public User(
            String email,
            String firstName,
            String lastName,
            String password,
            Role role,
            String gender,
            String phone,
            Address address,
            LocalDate birthDate,
            String profileImageUrl
            ){
        this.email=email;
        this.firstName=firstName;
        this.lastName=lastName;
        this.password=password;
        this.role=role;
        this.gender=gender;
        this.phone=phone;
        this.address=address;
        this.birthDate=birthDate;
        this.profileImageUrl=profileImageUrl;
    }

    //@OneToMany(mappedBy = "user")
    //private List<Purchase> orders;

    //@OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<Review> writtenReviews;

    //@Getter
    //@JsonManagedReference
    //@OneToMany(mappedBy = "created_by_id")
    //private List<Event> events;

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