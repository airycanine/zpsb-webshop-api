package pl.qbawalat.zpsbwebshopapi.payload.response;

import lombok.Data;
import pl.qbawalat.zpsbwebshopapi.api.rest.user.address.Address;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.OneToOne;
import java.util.List;

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String email;
    private List<String> roles;
    private String firstName;
    private String lastName;
    @OneToOne(cascade = {CascadeType.ALL})
    private Address address;
    @ElementCollection
    private List<String> offers;
    @ElementCollection
    private List<String> likedCars;

    public JwtResponse(String accessToken, String email, String firstName, String lastName, Address address, List<String> offers, List<String> likedCars, List<String> roles) {
        this.token = accessToken;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.offers = offers;
        this.likedCars = likedCars;
        this.roles = roles;
    }
}
