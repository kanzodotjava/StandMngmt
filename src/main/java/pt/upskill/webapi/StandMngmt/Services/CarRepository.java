package pt.upskill.webapi.StandMngmt.Services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.upskill.webapi.StandMngmt.Models.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

}
