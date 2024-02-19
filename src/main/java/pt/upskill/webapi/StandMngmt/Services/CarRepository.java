package pt.upskill.webapi.StandMngmt.Services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.upskill.webapi.StandMngmt.Enums.Status;
import pt.upskill.webapi.StandMngmt.Models.Car;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    int countByStatus(Status status);

    List<Car> findByStatus(Status status);

}
