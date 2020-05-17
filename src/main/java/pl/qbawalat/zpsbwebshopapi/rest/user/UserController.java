package pl.qbawalat.zpsbwebshopapi.rest.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin()
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
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

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        validateExistenceInRepository(id);
        userService.deleteById(id);

        return ResponseEntity.ok().build();
    }

    private void validateExistenceInRepository(String id) {
        if (userService.findById(id).isEmpty()) {
            log.error("User with id - " + id + " - does not exist.");
            ResponseEntity.badRequest().build();
        }
    }
}