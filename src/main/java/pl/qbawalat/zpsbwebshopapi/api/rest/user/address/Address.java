package pl.qbawalat.zpsbwebshopapi.api.rest.user.address;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Address {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

    private String city;
    private String voivodeship;
    private String zip;
    private String street;

    public Address() {
    }

    public Address(String city, String voivodeship, String zip, String street) {
        this.city = city;
        this.voivodeship = voivodeship;
        this.zip = zip;
        this.street = street;
    }
}
