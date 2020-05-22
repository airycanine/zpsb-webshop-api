package pl.qbawalat.zpsbwebshopapi.on.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.qbawalat.zpsbwebshopapi.api.rest.user.User;
import pl.qbawalat.zpsbwebshopapi.api.rest.user.UserRepository;
import pl.qbawalat.zpsbwebshopapi.api.rest.user.address.Address;
import pl.qbawalat.zpsbwebshopapi.api.rest.user.role.Role;
import pl.qbawalat.zpsbwebshopapi.constants.Roles;

import java.util.HashSet;
import java.util.Set;

@Component
@Order(0)
class AdminCreator implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        User admin = new User("kubawalat@gmail.com", "Jakub", "Walat", new Address("Szczecin", "Windy 43", "Zachodniopomorskie", "70-731"), encoder.encode("admin"));
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(Roles.ADMIN));
        admin.setRoles(roles);
        userRepository.save(admin);
        User admin2 = new User("admin@gmail.com", "Jakub", "Walat", new Address("Szczecin", "Windy 43", "Zachodniopomorskie", "70-731"), encoder.encode("admin"));
        admin2.setRoles(roles);
        userRepository.save(admin2);
        User admin3 = new User("admin1@gmail.com", "Jakub", "Walat", new Address("Szczecin", "Windy 43", "Zachodniopomorskie", "70-731"), encoder.encode("admin"));
        admin3.setRoles(roles);
        userRepository.save(admin3);
        User admin4 = new User("admin2@gmail.com", "Jakub", "Walat", new Address("Szczecin", "Windy 43", "Zachodniopomorskie", "70-731"), encoder.encode("admin"));
        admin4.setRoles(roles);
        userRepository.save(admin4);
    }

}
