package org.acme.edu;

import jakarta.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@ApplicationScoped
public class CarService {

    private static final Logger log = LoggerFactory.getLogger(CarService.class);

    public String getRandomModel(String brand) {
        final var bmwModels = List.of("E36", "E31", "X5", "M3");
        final var audiModels = List.of("A4", "RS4", "RS6", "RSQ5");
        final var porscheModels = List.of("911 GT3 RS", "Panamera", "911 2.8 RSR", "964");
        return switch (brand) {
            case "BMW" -> {
                Random rand = new Random();
                yield bmwModels.get(rand.nextInt(bmwModels.size()));
            }
            case "audi" -> {
                Random rand = new Random();
                yield audiModels.get(rand.nextInt(audiModels.size()));
            }
            case "porsche" -> {
                Random rand = new Random();
                yield porscheModels.get(rand.nextInt(porscheModels.size()));
            }
            default -> throw new IllegalStateException("Unexpected value: " + brand);
        };
    }

    public Car buildCar(String brand, String model) {
        return new Car(UUID.randomUUID(), brand, model);
    }

    public void fixCar(Car car) {
        log.info("repairing a car: {}", car);
    }

    public void doSomeWorkOnBrokenCar(Car model) {

        model.printUppercaseBrandToConsole();
    }

    public record Car(UUID vin, String brand, String model) {
        public void printUppercaseBrandToConsole() {
            System.out.println(brand.toUpperCase());
        }
    }

    // private String getRandomListItem(List<String> list) {
    //     Random rand = new Random();
    //     return list.get(rand.nextInt(list.size()));
    // }
}
