package org.acme.edu;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class NameService {

    // List<String> getSomeNames(Type type) {
    //     return switch (type) {
    //         case PERSON -> List.of("Jack", "Rose", "Mike");
    //         case ANIMAL -> List.of("Lessie", "Fluffy", "Tutu");
    //     };
    // }
}
