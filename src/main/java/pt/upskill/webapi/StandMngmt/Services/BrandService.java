package pt.upskill.webapi.StandMngmt.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.upskill.webapi.StandMngmt.Models.Brand;

@Service
    public class BrandService {
        @Autowired
        private BrandRepository brandRepository;


        public Brand createBrand(Brand brand) {
            return brandRepository.save(brand);
        }
    }
