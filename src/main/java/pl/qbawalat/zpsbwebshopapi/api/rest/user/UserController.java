package pl.qbawalat.zpsbwebshopapi.api.rest.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll().stream().map(user -> {
            user.setPassword("nicetry"); //should create dto which doesnt consist password but i'm lazy ;(
            return user;
        }).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity create(@RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @GetMapping("/")
    public ResponseEntity<User> getCustomer(@RequestParam String email, @RequestParam String password) {
        Optional<User> user = userService.findById(email);
        if (user.isEmpty()) {
            log.error("User with email -" + email + " - not found in repository.");
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(user.get());
    }


    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable String id, @RequestBody User user) {
        validateExistenceInRepository(id);

        return ResponseEntity.ok(userService.save(user));
    }

    @DeleteMapping("/{email}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity delete(@PathVariable String email) {
        validateExistenceInRepository(email);
        userService.deleteById(email);

        return ResponseEntity.ok().build();
    }

    private void validateExistenceInRepository(String id) {
        if (userService.findById(id).isEmpty()) {
            log.error("User with id - " + id + " - does not exist.");
            ResponseEntity.badRequest().build();
        }
    }
}