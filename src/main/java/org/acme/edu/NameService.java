package org.acme.edu;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class NameService {

    @Inject
    public NameService(CarService carService) {
        carService.doSomeWorkOnBrokenCar(carService.buildCar("VW", "Golf MK3"), "a parameter");
    }
    // List<String> getSomeNames(Type type) {
    //     return switch (type) {
    //         case PERSON -> List.of("Jack", "Rose", "Mike");
    //         case ANIMAL -> List.of("Lessie", "Fluffy", "Tutu");
    //     };
    // }
}
