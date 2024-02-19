package pt.upskill.webapi.StandMngmt.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.upskill.webapi.StandMngmt.Enums.Status;
import pt.upskill.webapi.StandMngmt.Models.Brand;
import pt.upskill.webapi.StandMngmt.Models.Car;
import pt.upskill.webapi.StandMngmt.Models.Model;
import pt.upskill.webapi.StandMngmt.Models.Seller;
import pt.upskill.webapi.StandMngmt.Services.BrandService;
import pt.upskill.webapi.StandMngmt.Services.CarService;
import pt.upskill.webapi.StandMngmt.Services.ModelService;
import pt.upskill.webapi.StandMngmt.Services.SellerService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {
    @Autowired
    private CarService carService;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private ModelService modelService;

    @Autowired
    private BrandService brandService;



    @GetMapping("/cars")
    public ResponseEntity<CollectionModel<EntityModel<Car>>> getAllCars() {
        List<EntityModel<Car>> cars = carService.getAllCars().stream()
                .map(car -> EntityModel.of(car,
                        Link.of("/api/car/" + car.getVIM()).withSelfRel()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(CollectionModel.of(cars,
                Link.of("/api/cars").withSelfRel(),
                Link.of("/api/car").withRel("create-car")), HttpStatus.OK);
    }

    @GetMapping("/sellers")
    public ResponseEntity<CollectionModel<EntityModel<Seller>>> getAllSellers() {
        List<EntityModel<Seller>> sellers = sellerService.getAllSellers().stream()
                .map(seller -> EntityModel.of(seller,
                        Link.of("/api/seller/" + seller.getId()).withSelfRel()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(CollectionModel.of(sellers,
                Link.of("/api/sellers").withSelfRel(),
                Link.of("/api/seller").withRel("create-seller")), HttpStatus.OK);
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<EntityModel<Car>> getCarById(@PathVariable long id) {
        Car car = carService.getCarById(id);
        if (car == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        EntityModel<Car> resource = EntityModel.of(car,
                Link.of("/api/car/" + id).withSelfRel(),
                Link.of("/api/car/" + id).withRel("update-car"),
                Link.of("/api/car/" + id).withRel("delete-car"));
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @GetMapping("/seller/{id}")
    public ResponseEntity<EntityModel<Seller>> getSellerById(@PathVariable long id) {
        Seller seller = sellerService.getSellerById(id);
        if (seller == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        EntityModel<Seller> resource = EntityModel.of(seller,
                Link.of("/api/seller/" + id).withSelfRel(),
                Link.of("/api/seller/" + id).withRel("update-seller"),
                Link.of("/api/seller/" + id).withRel("delete-seller"));
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @PostMapping("/car")
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        Car createdCar = carService.createCar(car);
        return new ResponseEntity<>(createdCar, HttpStatus.CREATED);
    }

    @PostMapping("/seller")
    public ResponseEntity<Seller> createSeller(@RequestBody Seller seller) {
        Seller createdSeller = sellerService.createSeller(seller);
        return new ResponseEntity<>(createdSeller, HttpStatus.CREATED);
    }

    @PutMapping("/seller/{id}")
    public ResponseEntity<Seller> updateSeller(@PathVariable long id, @RequestBody Seller seller) {
        Seller updatedSeller = sellerService.updateSeller(id, seller);
        return new ResponseEntity<>(updatedSeller, HttpStatus.OK);
    }

    @PutMapping("/car/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable long id, @RequestBody Car car) {
        Car updatedCar = carService.updateCar(id, car);
        return new ResponseEntity<>(updatedCar, HttpStatus.OK);
    }

    @DeleteMapping("/car/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable long id) {
        carService.deleteCar(id);
        return new ResponseEntity<>("Car deleted successfully", HttpStatus.OK);
    }

    @DeleteMapping("/seller/{id}")
    public ResponseEntity<String> deleteSeller(@PathVariable long id) {
        sellerService.deleteSeller(id);
        return new ResponseEntity<>("Seller deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/car/{id}/status")
    public ResponseEntity<Car> changeCarStatus(@PathVariable long id, @RequestBody Status status) {
        Car updatedCar = carService.changeCarStatus(id, status);
        return new ResponseEntity<>(updatedCar, HttpStatus.OK);
    }

    @GetMapping("/cars/count/available")
    public ResponseEntity<Map<String, Object>> getAvailableCars() {
        int availableCars = carService.getAvailableCars();
        String message = "Total of available cars: " + availableCars;
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/cars/available")
    public ResponseEntity<List<Car>> getAllAvailableCars() {
        List<Car> availableCars = carService.getAllAvailableCars();
        return new ResponseEntity<>(availableCars, HttpStatus.OK);
    }

    @PostMapping("/model")
    public ResponseEntity<Model> createModel(@RequestBody Model model) {
        Model createdModel = modelService.createModel(model);
        return new ResponseEntity<>(createdModel, HttpStatus.CREATED);
    }

    //create brand
    @PostMapping("/brand")
    public ResponseEntity<Brand> createBrand(@RequestBody Brand brand) {
        Brand createdBrand = brandService.createBrand(brand);
        return new ResponseEntity<>(createdBrand, HttpStatus.CREATED);
    }

}

