package pl.qbawalat.zpsbwebshopapi.payload.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import pl.qbawalat.zpsbwebshopapi.api.rest.user.address.Address;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class SignupRequest {
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    private String firstName;
    private String lastName;
    @OneToOne(cascade = {CascadeType.ALL})
    private Address address;
    @JsonIgnore
    private Set<String> role;

}
