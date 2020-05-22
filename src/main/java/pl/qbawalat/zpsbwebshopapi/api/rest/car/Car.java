package pl.qbawalat.zpsbwebshopapi.api.rest.car;

import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
@Data
public class Car {
    @Id
    private String licenceNumber;
    private String model;
    private String brand;
    @ElementCollection
    private List<String> images;
    private Float price;
    private String currency;
    private String seller;
    private String buyer;
}
