package pl.qbawalat.zpsbwebshopapi.user;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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

//    private String password;
//    private UserType type;
}
