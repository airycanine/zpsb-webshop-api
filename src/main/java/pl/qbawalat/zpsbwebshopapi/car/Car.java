package pl.qbawalat.zpsbwebshopapi.car;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Car {
    @Id
    private String licenceNumber;
    private String model;
    private String company;
    private Byte[] image;
}
