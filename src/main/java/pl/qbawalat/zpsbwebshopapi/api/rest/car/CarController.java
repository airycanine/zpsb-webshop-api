package pl.qbawalat.zpsbwebshopapi.api.rest.car;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/cars")
@Slf4j
@RequiredArgsConstructor
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> findAll() {
        return ResponseEntity.ok(carService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Car car) {
        if (carService.findById(car.getLicenceNumber()).isPresent() && car.getBuyer() != null) {
            return ResponseEntity.badRequest().body("Car with given regplate is already being sold.");
        }
        return ResponseEntity.ok(carService.save(car));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        Optional<Car> car = carService.findById(id);
        if (car.isEmpty()) {
            log.error("Car with id -" + id + " - not found in repository.");
            return ResponseEntity.badRequest().body("Wrong car");
        }
        return ResponseEntity.ok(car.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> update(@PathVariable String id, @RequestBody Car car) {
        validateExistenceInRepository(id);

        return ResponseEntity.ok(carService.save(car));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        validateExistenceInRepository(id);
        carService.deleteById(id);

        return ResponseEntity.ok().build();
    }

    private void validateExistenceInRepository(String id) {
        if (carService.findById(id).isEmpty()) {
            log.error("Car with id - " + id + " - does not exist.");
            ResponseEntity.badRequest().build();
        }
    }
}
