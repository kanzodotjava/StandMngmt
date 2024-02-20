package pt.upskill.webapi.StandMngmt.Services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.upskill.webapi.StandMngmt.Enums.Status;
import pt.upskill.webapi.StandMngmt.Models.Brand;
import pt.upskill.webapi.StandMngmt.Models.Car;
import pt.upskill.webapi.StandMngmt.Models.Model;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private ModelRepository modelRepository;


    public List<Car> getAllCars() {
        return carRepository.findAll();
    }



    public Car getCarById(long VIM) {
        return carRepository.findById(VIM).orElse(null);
    }


    public Car createCar(Car car) {
        Optional<Model> modelOptional = modelRepository.findById(car.getModel().getId());
        car.setModel(modelOptional.get());
        return carRepository.save(car);
    }

    public Car updateCar(long VIM, Car updatedCar) {
        Car car = carRepository.findById(VIM).orElse(null);
        if (car != null) {
            if (updatedCar.getModel() != null) {
                car.setModel(updatedCar.getModel());
            }
            if (updatedCar.getLicensePlate() != null) {
                car.setLicensePlate(updatedCar.getLicensePlate());
            }
            if (updatedCar.getNumberOfSeats() != 0) {
                car.setNumberOfSeats(updatedCar.getNumberOfSeats());
            }
            if (updatedCar.getTraction() != null) {
                car.setTraction(updatedCar.getTraction());
            }
            if (updatedCar.getFuel() != null) {
                car.setFuel(updatedCar.getFuel());
            }
            if (updatedCar.getColor() != null) {
                car.setColor(updatedCar.getColor());
            }
            if (updatedCar.getStatus() != null) {
                car.setStatus(updatedCar.getStatus());
            }
            if (updatedCar.getType() != null) {
                car.setType(updatedCar.getType());
            }
            if (updatedCar.getNumberOfDoors() != 0) {
                car.setNumberOfDoors(updatedCar.getNumberOfDoors());
            }
            if (updatedCar.getSeller() != null) {
                car.setSeller(updatedCar.getSeller());
            }
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

    public int getAvailableCars() {
        return carRepository.countByStatus(Status.AVAILABLE);
    }

    public List<Car> getAllAvailableCars() {
        return carRepository.findByStatus(Status.AVAILABLE);
    }
}