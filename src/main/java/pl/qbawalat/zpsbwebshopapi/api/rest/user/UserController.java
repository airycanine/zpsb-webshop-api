package pl.qbawalat.zpsbwebshopapi.api.rest.user;

import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.qbawalat.zpsbwebshopapi.api.rest.car.CarService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin()
public class UserController {

    private final UserService userService;
    private final CarService carService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll().stream().map(user -> {
//            user.setPassword("nicetry"); //should create dto which doesnt consist password but i'm lazy ;(
            return user;
        }).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity create(@RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @GetMapping("/")
    public ResponseEntity<User> getCustomer(@RequestParam String email, @RequestParam String password) {
        User user = findOrThrowBadRequest(email);
        return ResponseEntity.ok(user);
    }

    private User findOrThrowBadRequest(@RequestParam String email) {
        Optional<User> user = userService.findById(email);
        if (user.isEmpty()) {
            log.error("User with email -" + email + " - not found in repository.");
            ResponseEntity.badRequest().build();
        }
        return user.get();
    }


    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable String id, @RequestBody User user) {
        validateExistenceInRepository(id);

        return ResponseEntity.ok(userService.save(user));
    }

    @PutMapping("/{id}/address")
    public ResponseEntity<User> updateAddress(@PathVariable String id, @RequestBody User user) {
        User foundUser = findOrThrowBadRequest(user.getEmail());
        foundUser.setAddress(user.getAddress());
        User save = userService.save(foundUser);
        List<User> all = userService.findAll();
        return ResponseEntity.ok(userService.save(foundUser));
    }

    @DeleteMapping("/{email}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<User> delete(@PathVariable String email) {
        validateExistenceInRepository(email);
        userService.deleteById(email);
        carService.findAll()
                .stream()
                .filter(carOffer -> carOffer.getSeller().equals(email) && Strings.isNullOrEmpty(carOffer.getBuyer()))
                .forEach(carOffer -> carService.deleteById(carOffer.getOfferNumber()));

        return ResponseEntity.ok().build();
    }

    private void validateExistenceInRepository(String id) {
        if (userService.findById(id).isEmpty()) {
            log.error("User with id - " + id + " - does not exist.");
            ResponseEntity.badRequest().build();
        }
    }
}