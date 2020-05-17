package pl.qbawalat.zpsbwebshopapi.rest.user;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    @OneToOne(cascade = {CascadeType.ALL})
    private Address address;
    @ElementCollection
    private List<String> offers;
    @ElementCollection
    private List<String> likedCars;
//    private UserType type;
}
