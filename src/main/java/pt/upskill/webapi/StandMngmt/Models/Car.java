package pt.upskill.webapi.StandMngmt.Models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import pt.upskill.webapi.StandMngmt.Enums.Status;
import pt.upskill.webapi.StandMngmt.Enums.Type;

import java.util.Date;

@Entity
public class Car {
    @Id
    private String VIM;

    private int TransactionID;

    private int BuyerID;

    private double buyingPrice;
    private double sellingPrice;
    private Date dateOfPurchase;
    @ManyToOne
    private Model model;
    @Nullable
    private String licensePlate;

    private int numberOfSeats;

    private String traction;
    private String fuel;

    private String color;
    @Enumerated(EnumType.STRING)
    private Type type = Type.UNKNOWN;

    @ManyToOne
    private Seller seller;

    private int numberOfDoors;

    @Enumerated(EnumType.STRING)
    private Status status = Status.UNKNOWN;

    private double kilometers;

    public Car() {
    }

    public String getVIM() {
        return VIM;
    }

    public double getKilometers() {
        return kilometers;
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

    public Type getType() {
        return type;
    }

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public Seller getSeller() {
        return seller;
    }

    public double getBuyingPrice() {
        return buyingPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public int getTransactionID() {
        return TransactionID;
    }

    public int getBuyerID() {
        return BuyerID;
    }

    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setVIM(String VIM) {
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

    public void setType(Type type) {
        this.type = type;
    }

    public void setNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public void setBuyingPrice(double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public void setTransactionID(int transactionID) {
        TransactionID = transactionID;
    }

    public void setBuyerID(int buyerID) {
        BuyerID = buyerID;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public void setKilometers(double kilometers) {
        this.kilometers = kilometers;
    }
}

