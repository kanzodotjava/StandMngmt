package pt.upskill.webapi.StandMngmt.Services;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.upskill.webapi.StandMngmt.Models.Model;

public interface ModelRepository extends JpaRepository<Model, Long> {
}
