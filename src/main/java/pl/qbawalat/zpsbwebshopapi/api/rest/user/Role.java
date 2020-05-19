package pl.qbawalat.zpsbwebshopapi.api.rest.user;

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

    public Role(Roles role) {
        this.name = role;
    }

    public Role() {
        
    }
}