package pt.upskill.webapi.StandMngmt.Models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import pt.upskill.webapi.StandMngmt.Enums.Status;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long VIM;

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//
//    private int IdClient;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//
//    private long Id2;
    @ManyToOne
    private Model model;
    @Nullable
    private String licensePlate;

    private int numberOfSeats;

    private String traction;
    private String fuel;

    private String color;

    private String Type;

    @ManyToOne
    private Seller seller;

    private int numberOfDoors;

    @Enumerated(EnumType.STRING)
    private Status status = Status.UNKNOWN;

    public Car() {
    }

    public long getVIM() {
        return VIM;
    }


    public Model getModel() {
        return model;
    }

    @Nullable
    public String getLicensePlate() {
        return licensePlate;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public String getTraction() {
        return traction;
    }

    public String getFuel() {
        return fuel;
    }

    public String getColor() {
        return color;
    }

    public Status getStatus() {
        return status;
    }

    public String getType() {
        return Type;
    }


    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setVIM(long VIM) {
        this.VIM = VIM;
    }



    public void setModel(Model model) {
        this.model = model;
    }

    public void setLicensePlate(@Nullable String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public void setTraction(String traction) {
        this.traction = traction;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setType(String type) {
        Type = type;
    }



    public void setNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}

