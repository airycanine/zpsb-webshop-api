package pl.qbawalat.zpsbwebshopapi.api.rest.car;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;

    public List<CarOffer> findAll() {
        return carRepository.findAll();
    }

    public List<CarOffer> findAscendingByPrice() {
        List<CarOffer> allOffers = carRepository.findAll();
        return allOffers.stream().sorted(Comparator.comparing(CarOffer::getPrice)).collect(Collectors.toList());
    }

    public List<CarOffer> findDescendingByPriceAll() {
        List<CarOffer> allOffers = carRepository.findAll();
        List<CarOffer> sortedOffers = allOffers.stream()
                .sorted(Comparator.comparing(CarOffer::getPrice))
                .collect(Collectors.toList());
        Collections.reverse(sortedOffers);
        return sortedOffers;
    }

    public Optional<CarOffer> findById(String id) {
        return carRepository.findById(id);
    }

    public CarOffer save(CarOffer carOffer) {
        //TODO: really bad, I should use some random generator
        String offerNumber = new StringBuilder().append("Offer number: ")
                .append(carRepository.findAll().size())
                .toString();
        List<CarOffer> allOffers = carRepository.findAll();
        while (allOffers.contains(offerNumber)) {
            offerNumber = String.valueOf(Math.ceil(Math.random()));
        }
        carOffer.setOfferNumber(offerNumber);
        return carRepository.save(carOffer);
    }

    public CarOffer update(CarOffer carOffer) {
        return carRepository.save(carOffer);
    }

    public void deleteById(String id) {
        carRepository.deleteById(id);
    }
}
