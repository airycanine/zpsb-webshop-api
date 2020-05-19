package pl.qbawalat.zpsbwebshopapi.api.rest.user;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Role> roles = new HashSet<>();

    public User() {

    }

    public User(String email, String firstName, String lastName, Address address, String password) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.password = password;
        this.offers = new ArrayList<>();
        this.likedCars = new ArrayList<>();
    }


}
