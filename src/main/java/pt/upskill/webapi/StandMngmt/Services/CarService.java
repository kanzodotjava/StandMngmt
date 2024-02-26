package pt.upskill.webapi.StandMngmt.Services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pt.upskill.webapi.StandMngmt.DTOs.CarDTO;
import pt.upskill.webapi.StandMngmt.Enums.Status;
import pt.upskill.webapi.StandMngmt.Models.Brand;
import pt.upskill.webapi.StandMngmt.Models.Car;
import pt.upskill.webapi.StandMngmt.Models.Model;

import java.util.Date;
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

    public Page<CarDTO> getCars(int page, int size, String sort) {
        return  this.carRepository.findAll(PageRequest.of(page,size, Sort.by(sort))).map(CarDTO::toCarDTO) ;
    }

    public CarDTO getCar(String id) {
        return this.carRepository.findById(id).map(CarDTO::toCarDTO).orElse(null);
    }

    public Car getCarById(String VIM) {
        return carRepository.findById(VIM).orElse(null);
    }


    //createCar but if it has same VIM throw error
    public Car createCar(Car car) {
        if (carRepository.existsById(car.getVIM())) {
            throw new IllegalArgumentException("Car with the same VIM already exists");
        }
        Optional<Model> modelOptional = modelRepository.findById(car.getModel().getId());
        car.setModel(modelOptional.get());
        return carRepository.save(car);
    }


    public Car updateCar(String VIM, Car updatedCar) {
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
            if (updatedCar.getDateOfPurchase() != null) {
                car.setDateOfPurchase(updatedCar.getDateOfPurchase());
            }
            if (updatedCar.getBuyingPrice() != 0) {
                car.setBuyingPrice(updatedCar.getBuyingPrice());
            }
            if (updatedCar.getSellingPrice() != 0) {
                car.setSellingPrice(updatedCar.getSellingPrice());
            }
            if (updatedCar.getVIM() != null) {
                car.setVIM(updatedCar.getVIM());
            }
            if (updatedCar.getTransactionID() != 0) {
                car.setTransactionID(updatedCar.getTransactionID());
            }
            if (updatedCar.getBuyerID() != 0) {
                car.setBuyerID(updatedCar.getBuyerID());
            }
            if (updatedCar.getKilometers() != 0) {
                car.setKilometers(updatedCar.getKilometers());
            }
            return carRepository.save(car);
        }
        return null;
    }

    public void deleteCar(String id) {
        carRepository.deleteById(id);
    }

    public Car changeCarStatus(String id, Status status) {
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

    public Car changeCarStatusToSold(String id, double sellingPrice) {
        Car car = carRepository.findById(id).orElse(null);
        if (car != null) {
            car.setStatus(Status.SOLD);
            car.setSellingPrice(sellingPrice);
            return carRepository.save(car);
        }
        return null;
    }

    public Car updateDateOfPurchase(String id, Date date) {
        Car car = carRepository.findById(id).orElse(null);
        if (car != null) {
            car.setDateOfPurchase(date);
            return carRepository.save(car);
        }
        return null;
    }

    public Car updateTransactionId(String id, int transactionId) {
        Car car = carRepository.findById(id).orElse(null);
        if (car != null) {
            car.setTransactionID((transactionId));
            return carRepository.save(car);
        }
        return null;
    }

    public Car updateBuyerId(String id, int buyerid) {
        Car car = carRepository.findById(id).orElse(null);
        if (car != null) {
            car.setBuyerID(buyerid);
            return carRepository.save(car);
        }
        return null;
    }
}