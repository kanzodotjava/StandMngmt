package pt.upskill.webapi.StandMngmt.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.upskill.webapi.StandMngmt.Enums.Status;
import pt.upskill.webapi.StandMngmt.Models.Car;

import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }


    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    public Car getCarById(long VIM) {
        return carRepository.findById(VIM).orElse(null);
    }


    public Car createCar(Car car) {
        return carRepository.save(car);
    }

    public Car updateCar(long VIM, Car updatedCar) {
        Car car = carRepository.findById(VIM).orElse(null);
        if (car != null) {
            car.setModel(updatedCar.getModel());
            car.setLicensePlate(updatedCar.getLicensePlate());
            car.setNumberOfSeats(updatedCar.getNumberOfSeats());
            car.setTraction(updatedCar.getTraction());
            car.setFuel(updatedCar.getFuel());
            car.setColor(updatedCar.getColor());
            car.setStatus(updatedCar.getStatus());
            car.setType(updatedCar.getType());
            car.setNumberOfDoors(updatedCar.getNumberOfDoors());
            car.setSeller(updatedCar.getSeller());
            return carRepository.save(car);
        }
        return null;
    }

    public void deleteCar(long id) {
        carRepository.deleteById(id);
    }

    public Car changeCarStatus(long id, Status status) {
        Car car = carRepository.findById(id).orElse(null);
        if (car != null) {
            car.setStatus(status);
            return carRepository.save(car);
        }
        return null;
    }

    //get total of available cars (stock) (using enum Status)
    public int getAvailableCars() {
        return carRepository.countByStatus(Status.AVAILABLE);
    }

    public List<Car> getAllAvailableCars() {
        return carRepository.findByStatus(Status.AVAILABLE);
    }
}