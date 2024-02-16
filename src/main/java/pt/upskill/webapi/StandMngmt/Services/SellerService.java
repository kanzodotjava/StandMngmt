package pt.upskill.webapi.StandMngmt.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.upskill.webapi.StandMngmt.Models.Car;
import pt.upskill.webapi.StandMngmt.Models.Seller;

import java.util.List;

@Service
public class SellerService {
    @Autowired
    private SellerRepository sellerRepository;

    public List<Seller> getAllSellers(){
        return sellerRepository.findAll();
    }

    public Seller getSellerById(long id){
        return sellerRepository.findById(id).orElse(null);
    }

    public Seller createSeller(Seller seller) {
        return sellerRepository.save(seller);
    }

    public Seller updateSeller(long id, Seller updatedSeller) {
        Seller seller = sellerRepository.findById(id).orElse(null);
        if (seller != null) {
            seller.setName(updatedSeller.getName());
            seller.setPhoneNumber(updatedSeller.getPhoneNumber());
            seller.setEmailAddress(updatedSeller.getEmailAddress());
            return sellerRepository.save(seller);
        }
        return null;
    }
}
