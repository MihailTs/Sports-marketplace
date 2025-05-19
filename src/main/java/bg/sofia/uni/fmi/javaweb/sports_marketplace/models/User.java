package bg.sofia.uni.fmi.javaweb.sports_marketplace.models;

import jakarta.persistence.*;
import lombok.*;

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
    private String name;
    @Setter
    @Getter
    private String password;
    @Setter
    @Getter
    private String role;
    @Getter
    @Setter
    private Date birthDate;
    @Getter
    @Setter
    private String phoneNumber;
    @Getter
    @Setter
    private String gender;

    public User(String email, String name, String password, String role){
        this.email=email;
        this.name=name;
        this.password=password;
        this.role=role;
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