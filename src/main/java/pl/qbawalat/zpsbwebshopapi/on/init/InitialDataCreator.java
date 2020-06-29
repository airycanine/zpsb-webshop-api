package pl.qbawalat.zpsbwebshopapi.on.init;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.qbawalat.zpsbwebshopapi.api.rest.car.CarOffer;
import pl.qbawalat.zpsbwebshopapi.api.rest.car.CarRepository;
import pl.qbawalat.zpsbwebshopapi.api.rest.user.User;
import pl.qbawalat.zpsbwebshopapi.api.rest.user.UserRepository;
import pl.qbawalat.zpsbwebshopapi.api.rest.user.address.Address;
import pl.qbawalat.zpsbwebshopapi.api.rest.user.role.Role;
import pl.qbawalat.zpsbwebshopapi.constants.Roles;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

import static pl.qbawalat.zpsbwebshopapi.utils.DoubleUtils.roundDoubleToTwoPlaces;

@Component
@Order(0)
@RequiredArgsConstructor
class InitialDataCreator implements ApplicationListener<ApplicationReadyEvent> {

    private final UserRepository userRepository;

    private final CarRepository carRepository;

    private final PasswordEncoder encoder;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        createAdmins();
        createOffers();
    }

    private void createOffers() {
        CarOffer firstCarOffer = new CarOffer();
        firstCarOffer.setBrand("Ford");
        firstCarOffer.setOfferNumber("First car");
        firstCarOffer.setCurrency("$");
        firstCarOffer.setLicenceNumber("Polo");
        firstCarOffer.setModel("Mustang");
        firstCarOffer.setSeller("kubawalat@gmail.com");
        firstCarOffer.setPrice(12500d);
        firstCarOffer.setCreationDate(LocalDateTime.now());
        firstCarOffer.setImages(Arrays.asList(
                "https://upload.wikimedia.org/wikipedia/commons/2/2d/1964_12_Ford_Mustang.jpg",
                "https://lh3.googleusercontent.com/proxy/PDIfVVYz8uCF6jgwAVHbxrqja5luCCWbdHNG_zQTxuW6SNlz-ICoOT_DKIgnIOfeqb2HT_dEVVqHl6sxgaBFIzhkSBs868YM4SDsmRatjtA0pLIWgljuW9aiytWg3yXNwXNFBTTDGeo6WvJLZNWp"));
        carRepository.save(firstCarOffer);
        CarOffer carOffer = new CarOffer();
        carOffer.setBrand("Test brand");
        carOffer.setCurrency("PLN");
        carOffer.setSeller("kubawalat@gmail.com");
        carOffer.setModel("TEST MODEL");
        var tags = new HashSet<String>();
        tags.add("mock");
        carOffer.setTags(tags);
        carOffer.setDescription(
                "Vw Polo Bluemotion 1.4 w wersji R-line nakładki na zderzaki, progi i spojler bardzo zadbany egzemplarz, wersja poliftowa z nową stylizacją deski oraz nowym wzorem kierownicy, auto bez wkładu finansowego świeżo po serwisie oleje.");
        carOffer.setEquipment("ABS, " + "Gniazdo AUX, " + "Isofix, " + "Kurtyny powietrzne, " + "Podgrzewane lusterka boczne, " + "Poduszki boczne tylne, " + "Światła do jazdy dziennej, " + "Tapicerka welurowa");
        carOffer.setImages(Arrays.asList(
                "https://d-mf.ppstatic.pl/art/17/5e/xbucyrk00wsccsc848k00/mclaren-speedtail-concludes-high-speed-testing_00.1200.jpg",
                "https://lh3.googleusercontent.com/proxy/PDIfVVYz8uCF6jgwAVHbxrqja5luCCWbdHNG_zQTxuW6SNlz-ICoOT_DKIgnIOfeqb2HT_dEVVqHl6sxgaBFIzhkSBs868YM4SDsmRatjtA0pLIWgljuW9aiytWg3yXNwXNFBTTDGeo6WvJLZNWp"));
        IntStream.range(0, 100).forEach(i -> {
            carOffer.setCreationDate(LocalDateTime.now());
            carOffer.setOfferNumber(String.valueOf(i));
            double randomPrice = Math.random() * 200000;
            carOffer.setPrice(roundDoubleToTwoPlaces(randomPrice));
            carOffer.setLicenceNumber(String.valueOf(Math.random()));
            carRepository.save(carOffer);
        });
    }


    private void createAdmins() {
        User admin = new User("kubawalat@gmail.com",
                              "Jakub",
                              "Walat",
                              new Address("Szczecin", "Windy 43", "Zachodniopomorskie", "70-731"),
                              encoder.encode("admin"));
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(Roles.ADMIN));
        admin.setRoles(roles);
        userRepository.save(admin);
        User admin2 = new User("admin@gmail.com",
                               "Jakub",
                               "Walat",
                               new Address("Szczecin", "Windy 43", "Zachodniopomorskie", "70-731"),
                               encoder.encode("admin"));
        admin2.setRoles(roles);
        userRepository.save(admin2);
        User admin3 = new User("admin1@gmail.com",
                               "Jakub",
                               "Walat",
                               new Address("Szczecin", "Windy 43", "Zachodniopomorskie", "70-731"),
                               encoder.encode("admin"));
        admin3.setRoles(roles);
        userRepository.save(admin3);
        User admin4 = new User("admin2@gmail.com",
                               "Jakub",
                               "Walat",
                               new Address("Szczecin", "Windy 43", "Zachodniopomorskie", "70-731"),
                               encoder.encode("admin"));
        admin4.setRoles(roles);
        userRepository.save(admin4);
    }

}
