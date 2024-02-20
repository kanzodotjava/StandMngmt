//package pt.upskill.webapi.StandMngmt.DTOs;
//
//import jakarta.annotation.Nullable;
//import jakarta.persistence.*;
//import lombok.Builder;
//import pt.upskill.webapi.StandMngmt.Enums.Status;
//import pt.upskill.webapi.StandMngmt.Models.Model;
//import pt.upskill.webapi.StandMngmt.Models.Seller;
//
//@Builder
//public class CarDTO {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//
//    private long VIM;
//    @ManyToOne
//
//    private Model model;
//    @Nullable
//
//    private String licensePlate;
//
//    private int numberOfSeats;
//
//    private String traction;
//    private String fuel;
//
//    private String color;
//
//    private String Type;
//
//    @ManyToOne
//    private Seller seller;
//
//    private int numberOfDoors;
//
//    @Enumerated(EnumType.STRING)
//    private Status status = Status.UNKNOWN;
//}
