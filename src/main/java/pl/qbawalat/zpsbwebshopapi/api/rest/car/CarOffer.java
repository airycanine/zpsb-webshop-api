package pl.qbawalat.zpsbwebshopapi.api.rest.car;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class CarOffer {
    @Id
    private String offerNumber;
    private String licenceNumber;
    private String model;
    private String brand;
    private LocalDateTime creationDate;
    @ElementCollection
    private List<String> images;
    @ElementCollection
    @Value("#{T(java.util.Arrays).asList('${my.list.of.strings:}')}")
    private Set<String> tags;
    private Double price;
    private String currency;
    private String seller;
    private String buyer;
    private String description;
    private String equipment;
}
