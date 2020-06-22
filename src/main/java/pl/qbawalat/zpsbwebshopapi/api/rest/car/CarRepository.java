package pl.qbawalat.zpsbwebshopapi.api.rest.car;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<CarOffer, String> {
}
