package pt.upskill.webapi.StandMngmt.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import pt.upskill.webapi.StandMngmt.DTOs.CarDTO;
import pt.upskill.webapi.StandMngmt.Enums.Status;
import pt.upskill.webapi.StandMngmt.Models.Car;
import pt.upskill.webapi.StandMngmt.Services.CarService;

import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public CollectionModel<CarDTO> getAllCars(@RequestParam(name = "page") Optional<Integer> page,
                                              @RequestParam(name = "size") Optional<Integer> size,
                                              @RequestParam(name = "sort") Optional<String> sort) {
        int _page = page.orElse(0);
        int _size = size.orElse(10);
        String _sort = sort.orElse("VIM");
        Page<CarDTO> cars = this.carService.getCars(_page, _size, _sort);
        ;
        cars = cars.map((CarDTO d) -> d.add(linkTo(methodOn(CarController.class).getCarById(d.getVIM())).withSelfRel()));
        Link link = linkTo(methodOn(CarController.class).getAllCars(Optional.of(1), Optional.of(10), Optional.of(_sort))).withSelfRel();
        List<Link> links = new ArrayList<>();
        links.add(link);
        if (!cars.isLast()) {
            Link _link = linkTo(methodOn(CarController.class).getAllCars(Optional.of(_page + 1), Optional.of(_size), Optional.of(_sort))).withRel("next");
            links.add(_link);
        }
        if (!cars.isFirst()) {
            Link _link = linkTo(methodOn(CarController.class).getAllCars(Optional.of(_page - 1), Optional.of(_size), Optional.of(_sort))).withRel("previous");
            links.add(_link);
        }
        return CollectionModel.of(cars.toList(), links);
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
