package pt.upskill.webapi.StandMngmt.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import pt.upskill.webapi.StandMngmt.Models.Seller;
import pt.upskill.webapi.StandMngmt.Services.ModelService;
import pt.upskill.webapi.StandMngmt.Services.SellerService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Seller>>> getAllSellers() {
        List<EntityModel<Seller>> sellers = sellerService.getAllSellers().stream()
                .map(seller -> EntityModel.of(seller,
                        Link.of("/seller/" + seller.getId()).withSelfRel()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(CollectionModel.of(sellers,
                Link.of("/seller").withSelfRel(),
                Link.of("/seller").withRel("create-seller")), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Seller>> getSellerById(@PathVariable long id) {
        Seller seller = sellerService.getSellerById(id);
        if (seller == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        EntityModel<Seller> resource = EntityModel.of(seller,
                Link.of("/seller/" + id).withSelfRel(),
                Link.of("/seller/" + id).withRel("update-seller"),
                Link.of("/seller/" + id).withRel("delete-seller"));
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Seller> createSeller(@RequestBody Seller seller) {
        Seller createdSeller = sellerService.createSeller(seller);
        return new ResponseEntity<>(createdSeller, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Seller> updateSeller(@PathVariable long id, @RequestBody Seller seller) {
        Seller updatedSeller = sellerService.updateSeller(id, seller);
        return new ResponseEntity<>(updatedSeller, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSeller(@PathVariable long id) {
        sellerService.deleteSeller(id);
        return new ResponseEntity<>("Seller deleted successfully", HttpStatus.OK);
    }

}
