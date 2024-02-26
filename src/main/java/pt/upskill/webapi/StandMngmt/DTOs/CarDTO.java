package pt.upskill.webapi.StandMngmt.DTOs;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import pt.upskill.webapi.StandMngmt.Enums.Status;
import pt.upskill.webapi.StandMngmt.Enums.Type;
import pt.upskill.webapi.StandMngmt.Models.Car;
import pt.upskill.webapi.StandMngmt.Models.Model;
import pt.upskill.webapi.StandMngmt.Models.Seller;

import java.sql.Driver;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO extends RepresentationModel<CarDTO> {

    @Id
    private String VIM;

    @ManyToOne
    private Model model;

    int numberOfSeats;

    String traction;

    String fuel;

    String color;

    @Enumerated(EnumType.STRING)
    Type type = Type.UNKNOWN;

    int numberOfDoors;

    @Enumerated(EnumType.STRING)
    private Status status = Status.UNKNOWN;

    private double sellingPrice;

    private Date dateOfPurchase;



    public static CarDTO toCarDTO(Car c){
        return new CarDTO(c.getVIM(),c.getModel(),c.getNumberOfSeats(),c.getTraction(),c.getFuel(),c.getColor(),
                c.getType(),c.getNumberOfDoors(),c.getStatus(),c.getSellingPrice(),c.getDateOfPurchase());
    }
}
