package pl.qbawalat.zpsbwebshopapi.api.rest.car;

import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.qbawalat.zpsbwebshopapi.api.rest.tags.Tag;
import pl.qbawalat.zpsbwebshopapi.api.rest.tags.TagService;
import pl.qbawalat.zpsbwebshopapi.api.rest.user.User;
import pl.qbawalat.zpsbwebshopapi.api.rest.user.UserService;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/cars")
@Slf4j
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    private final UserService userService;

    private final TagService tagService;

    @GetMapping("/all")
    public ResponseEntity<List<CarOffer>> findAll() {
        return ResponseEntity.ok(carService.findAll());//@TODO handle not found exception properly
    }

    @GetMapping("/active")
    public ResponseEntity<List<CarOffer>> findActiveOffers() {
        List<CarOffer> offers = collectToActiveOffers(carService.findAll());
        return ResponseEntity.ok(offers);
    }

    @GetMapping("/active/ascending")
    public ResponseEntity<List<CarOffer>> findAllAscendingByPrice() {
        List<CarOffer> offers = collectToActiveOffers(carService.findAscendingByPrice());
        return ResponseEntity.ok(offers);
    }

    @GetMapping("active/descending")
    public ResponseEntity<List<CarOffer>> findAllDescendingByPrice() {
        List<CarOffer> offers = collectToActiveOffers(carService.findDescendingByPriceAll());
        return ResponseEntity.ok(offers);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<?> create(@Valid @RequestBody CarOffer carOffer) {
        List<String> wordsFromDescription = Arrays.asList(carOffer.getDescription().split(" "));
        wordsFromDescription.stream().filter(word -> word.contains("@")).forEach(tag -> {
            String tagWithoutMonkey = tag.replaceAll("@", "");
            tagService.save(Tag.builder().name(tagWithoutMonkey).build());
            carOffer.getTags().add(tagWithoutMonkey);
        });
        carOffer.setDescription(carOffer.getDescription().replaceAll("@", ""));
        return ResponseEntity.ok(carService.save(carOffer));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        Optional<CarOffer> car = carService.findById(id);
        if (car.isEmpty()) {
            log.error("Car with id -" + id + " - not found in repository.");
            return ResponseEntity.badRequest().body("Wrong car");
        }
        return ResponseEntity.ok(car.get());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<CarOffer> update(@PathVariable String id, @RequestBody CarOffer carOffer) {
        validateExistenceInRepository(id);
        if (carOffer.getBuyer() != null && carOffer.getSeller() != null) {
            User user = userService.findById(carOffer.getSeller()).get();
            user.getOffers().remove(carOffer.getLicenceNumber());
            userService.save(user);
        }
        return ResponseEntity.ok(carService.update(carOffer));
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

    private List<CarOffer> collectToActiveOffers(List<CarOffer> all) {
        return all.stream().filter(carOffer -> Strings.isNullOrEmpty(carOffer.getBuyer())).collect(Collectors.toList());
    }

}
