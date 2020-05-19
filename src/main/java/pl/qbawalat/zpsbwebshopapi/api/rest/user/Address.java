package pl.qbawalat.zpsbwebshopapi.api.rest.user;

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

    public Address() {
    }

    public Address(String city, String voivodeship, String zip) {
        this.city = city;
        this.voivodeship = voivodeship;
        this.zip = zip;
    }
}
