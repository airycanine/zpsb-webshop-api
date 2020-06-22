package pl.qbawalat.zpsbwebshopapi.api.rest.tags;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Builder
@Data
@Entity
public class Tag {
    @Id
    private String name;

    public Tag() {
    }

    public Tag(String name) {
        this.name = name;
    }
}
