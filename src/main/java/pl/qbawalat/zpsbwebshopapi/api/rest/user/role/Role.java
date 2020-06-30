package pl.qbawalat.zpsbwebshopapi.api.rest.user.role;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import pl.qbawalat.zpsbwebshopapi.constants.Roles;

import javax.persistence.*;

@Entity
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Roles name;

    @JsonCreator
    public Role(Roles role) {
        this.name = role;
    }

    public Role() {
    }


}