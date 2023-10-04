package org.acme.edu;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
@SuppressWarnings("unused")
public class KnowYourIde {
    private static final Logger log = LoggerFactory.getLogger(KnowYourIde.class);

    private final CarService carService;
    private final ObjectMapper objectMapper;

    // region IDE Setup
    // @formatter:off
    public record RecordWithManyArgumentsWhichYouWantInOneLine(String var1, String var2, String var3, String var4, String var5, String var6) {}
    // @formatter:on
    // endregion

    // region Navigation
    /*
    go to class (Ctrl+N)
     - Capital letter navigation (ImageTest, UniApi in core-directory)
    go to file (Ctrl+Shift+N)
     - readResourceFileAsString("json-files/some.json")
     - select text and navigate json file
    go to symbol (Ctrl+Alt+Shift+N)
     - buildCar
    go to action (Ctrl+Shift+A)
     - Split and move right
     - rename
    go to test (Ctrl+Shift+T)
    recent files (Ctrl+E)
    file structure (Ctrl+F12)
    breakpoint (Ctrl+Shift+<number>, Ctrl+<Number>)
    last edited location (Ctrl+Shift+Backspace)
    Next highlighted error (F2)
    Search (F3)
    Back/Forward (Ctrl+Alt+Left/Right)
    Next/Previous method (Alt+Up/Down)
    Split and move right
    Find usages
    Analyze data flow to here (printUppercaseBrandToConsole)

    IDE sections & actions:
    - project explorer (Alt+1)
    - terminal (Alt+F12)
    - git (Alt+9)

    - Build/rebuild (Alt+b -> p/r)

    GIT:
    - fetch (Ctl+Alt+1)
    - pull (Ctrl+Alt+2)
    - branches (Ctrl+Alt+3)
    - commit (Ctrl+k)
    - push (Ctrl+Alt+W)
     */
    // endregion
    @Inject
    public KnowYourIde(NameService nameService, CarService carService, ObjectMapper objectMapper) {
        this.carService = carService;
        this.objectMapper = objectMapper;
        readResourceFileAsString("json-files/some.json");
    }

    // region Show Context
    /*
    add introduce field for nameService parameter
    remove redundant casts
    introduce local variable from List.of()
    fixStream
    fix typo in autocompelete
     */

    public List<String> getSomeNames(Type type) {
        var name = type instanceof Type ?
                ((Type) type).name()
                : null;
        return switch (type) {
            case PERSON -> List.of("Jack", "Rose", "Mike");
            case ANIMAL -> List.of("Lessie", "Fluffy", "Tutu");
        };
    }

    public void fixStream() {
        final var cars = List.of(carService.buildCar("BMW", "E36"),
                carService.buildCar("MB", "E190"));
        final var carModels = List.of("E36", "W213", "A4");
        final var missingCarModels = cars.stream()
                .filter(car -> !carModels.contains(car.model()))
                .map(CarService.Car::model)
                .toList();
        log.info("missing car models: {}", String.join(", ", missingCarModels));

    }
    // endregion

    // region Refactor: Rename
    /*
    Refactor: rename
    rename dummyVariable
    demonstrateManualRenameFail
    rename getRandomModel method
    rename Car record
     */

    public void renameExample(String dummyVariableName) {
        final var randomModel = carService.getRandomModel(dummyVariableName);
        log.info("dummyVariableName: {}, model: {}", dummyVariableName, randomModel);

        final var car = carService.buildCar(dummyVariableName, randomModel);
        carService.fixCar(car);
    }

    public void anotherMethodUsingGetRandomModel() {
        final var randomModel = carService.getRandomModel("porsche");
        log.info("random porsche model: {}", randomModel);
    }

    private String state = "OK";

    public String demonstrateManualRenameFail(String status) {
        if (status == null) {
            log.info("status is null");
            throw new IllegalArgumentException("status is null");
        }
        /*
        some long block of code

















        lorem ipsum
         */
        final var isFoo = status.contains("foo");
        if (isFoo) {
            status = "some very ugly bug";
        }

        return status;
    }

    public boolean isServiceOK() {
        return state.equals("OK");
    }

    // endregion

    // region Refactor & Code manipulation
    /*
    duplicate line (Ctrl+D)
    delete line (Ctrl+Y)
    extend/shrink selection (Ctrl(+Shift)+W)
    move statement up/down (Ctrl+Shift+Up/Down)
    move line up/down (Alt+Shift+Up/Down
    change case (Ctrl+Shift+U)
    - org.acme.edu.KnowYourIde.TypeEnum
    column selection mode (
    introduce variable/constant (Ctrl+Shift+V/M)
    extract method (Ctrl+Shift+M)
    - getRandomModel
    move method
     - getSomeNames
    extract parameter object
     - calculateAreaOfTrapezoid
    change signature (Ctrl+F6)
    generate (Alt+Insert)
    - calculateAreaOfTrapezoid
     */

    public void codeManipulation() {
        carService.fixCar(new CarService.Car(UUID.randomUUID(), "VW", "Multivan"));
        calculateAreaOfTrapezoid(10, 15, 8);
    }

    public double calculateAreaOfTrapezoid(int base1, int base2, int height) {
        return 0.5 * (base1 + base2) * height;
    }

    public enum TypeEnum {
        foo,
        bar,
        foobar
    }
    // endregion

    // region Autocomplete
    /*
    autocomplete (Ctrl+Space)
    - autocomplete from project
    - autocomplete line (Ctrl+Shift+Enter)
    - autocomplete from third party library
    - stream
     */

    public String autocompelete() {
        // List.of(PERSO)
        // Foo.values().
        return "autocomplete";
    }

    // endregion

    // region IDE Utils
    /*
    analyze stacktrace
    code coverage
    database integration (core directory)
    github integration (core directory)
    debug (conditional)
     */
    // endregion

    public void methodThrowingException(TypeEnum type, String param) {
        carService.doSomeWorkOnBrokenCar(new CarService.Car(UUID.randomUUID(), null, "model"), param);
    }

    // region Utils

    public String readResourceFileAsString(final String fileName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream is = classLoader.getResourceAsStream(fileName)) {
            if (is == null) {
                throw new IOException("Cannot read file: " + fileName);
            }
            final var content = new String(is.readAllBytes());
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(objectMapper.readTree(content));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // endregion
}
