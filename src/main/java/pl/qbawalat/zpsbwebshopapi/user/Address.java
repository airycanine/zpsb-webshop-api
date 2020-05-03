package pl.qbawalat.zpsbwebshopapi.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Address {
    @Id
    @GeneratedValue
    private Long id;
    private String city;
    private String voivodeship;
    private String zip;
}
