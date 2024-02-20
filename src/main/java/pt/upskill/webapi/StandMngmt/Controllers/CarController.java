package pt.upskill.webapi.StandMngmt.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import pt.upskill.webapi.StandMngmt.Enums.Status;
import pt.upskill.webapi.StandMngmt.Models.Car;
import pt.upskill.webapi.StandMngmt.Services.CarService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Car>>> getAllCars() {
        List<EntityModel<Car>> cars = carService.getAllCars().stream()
                .map(car -> EntityModel.of(car,
                        Link.of("/car/" + car.getVIM()).withSelfRel()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(CollectionModel.of(cars,
                Link.of("/car").withSelfRel(),
                Link.of("/car").withRel("create-car")), HttpStatus.OK);
    }



    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Car>> getCarById(@PathVariable long id) {
        Car car = carService.getCarById(id);
        if (car == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        EntityModel<Car> resource = EntityModel.of(car,
                Link.of("/car/" + id).withSelfRel(),
                Link.of("/car/" + id).withRel("update-car"),
                Link.of("/car/" + id).withRel("delete-car"));
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }



    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        Car createdCar = carService.createCar(car);
        return new ResponseEntity<>(createdCar, HttpStatus.CREATED);
    }



    @PutMapping("{id}")
    public ResponseEntity<Car> updateCar(@PathVariable long id, @RequestBody Car car) {
        Car updatedCar = carService.updateCar(id, car);
        return new ResponseEntity<>(updatedCar, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable long id) {
        carService.deleteCar(id);
        return new ResponseEntity<>("Car deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Car> changeCarStatus(@PathVariable long id, @RequestBody Status status) {
        Car updatedCar = carService.changeCarStatus(id, status);
        return new ResponseEntity<>(updatedCar, HttpStatus.OK);
    }

    @GetMapping("/count/available")
    public ResponseEntity<Map<String, Object>> getAvailableCars() {
        int availableCars = carService.getAvailableCars();
        String message = "Total of available cars: " + availableCars;
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/available")
    public ResponseEntity<List<Car>> getAllAvailableCars() {
        List<Car> availableCars = carService.getAllAvailableCars();
        return new ResponseEntity<>(availableCars, HttpStatus.OK);
    }

}
