package pt.upskill.webapi.StandMngmt.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.upskill.webapi.StandMngmt.Models.Brand;
import pt.upskill.webapi.StandMngmt.Models.Car;

import java.util.List;

@Service
    public class BrandService {
        @Autowired
        private BrandRepository brandRepository;


        public Brand createBrand(Brand brand) {
            return brandRepository.save(brand);
        }

    public Brand updateBrand(Long id, Brand updatedBrand){
        if (brandRepository.existsById(id)) {
            updatedBrand.setId(id);
            return brandRepository.save(updatedBrand);
        } else {
            return null;
        }
    }

    public void deleteBrand(long id) {
        brandRepository.deleteById(id);
    }

    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    public Brand getBrandById(long id) {
        return brandRepository.findById(id).orElse(null);
    }
}
