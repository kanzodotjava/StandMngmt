package pt.upskill.webapi.StandMngmt.Services;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.upskill.webapi.StandMngmt.Models.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
