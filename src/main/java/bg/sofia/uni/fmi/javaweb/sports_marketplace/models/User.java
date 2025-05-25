package bg.sofia.uni.fmi.javaweb.sports_marketplace.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Getter
    private String email;
    @Setter
    @Getter
    private String firstName;
    @Setter
    @Getter
    private String lastName;
    @Setter
    @Getter
    private String password;
    @Setter
    @Getter
    private String role;
    @Getter
    @Setter
    private LocalDate birthDate;
    @Getter
    @Setter
    private String phoneNumber;
    @Getter
    @Setter
    private String gender;

    public User(String email, String password, String firstName, String lastName, LocalDate birthDate, String phoneNumber, String gender, String role){
        this.email=email;
        this.firstName=firstName;
        this.lastName=lastName;
        this.password=password;
        this.role=role;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
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

}