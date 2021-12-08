package com.sg.cardealership.model;

import java.math.BigDecimal;

/**
 * This class represents a vehicle
 */
public class Vehicle {

    private String VIN;
    private int makeId;
    private int modelId;
    private String type;
    private String bodyStyle;
    private int year;
    private String transmission;
    private String color;
    private String interior;
    private int mileage;
    private BigDecimal salePrice;
    private BigDecimal msrp;
    private String description;
    private boolean isFeatured;
    private boolean isSold;
    private String picture;

    // Gets the vin of the vehicle
    public String getVIN() {
        return VIN;
    }

    // Sets the vin of the vehicle
    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    // Gets the make id of the vehicle
    public int getMakeId() {
        return makeId;
    }

    // Sets the make id of the vehicle
    public void setMakeId(int makeId) {
        this.makeId = makeId;
    }

    // Gets the model id of the vehicle
    public int getModelId() {
        return modelId;
    }

    // Sets the model id of the vehicle
    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    // Gets the type of the vehicle
    public String getType() {
        return type;
    }

    // Sets the type of the vehicle
    public void setType(String type) {
        this.type = type;
    }

    // Gets the body style of the vehicle
    public String getBodyStyle() {
        return bodyStyle;
    }

    // Sets the body style of the vehicle
    public void setBodyStyle(String bodyStyle) {
        this.bodyStyle = bodyStyle;
    }

    // Gets the year of the vehicle
    public int getYear() {
        return year;
    }

    // Sets the year of the vehicle
    public void setYear(int year) {
        this.year = year;
    }

    // Gets the transmission of the vehicle
    public String getTransmission() {
        return transmission;
    }

    // Sets the transmission of the vehicle
    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    // Gets the color of the vehicle
    public String getColor() {
        return color;
    }

    // Sets the color of the vehicle
    public void setColor(String color) {
        this.color = color;
    }

    // Gets the interior of the vehicle
    public String getInterior() {
        return interior;
    }

    // Sets the interior of the vehicle
    public void setInterior(String interior) {
        this.interior = interior;
    }

    // Gets the mileage of the vehicle
    public int getMileage() {
        return mileage;
    }

    // Sets the mileage of the vehicle
    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    // Gets the sale price of the vehicle
    public BigDecimal getSalePrice() {
        return salePrice;
    }

    // Sets the sale price of the vehicle
    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    // Gets the MSRP of the vehicle
    public BigDecimal getMsrp() {
        return msrp;
    }

    // Sets the MSRP of the vehicle
    public void setMsrp(BigDecimal msrp) {
        this.msrp = msrp;
    }

    // Gets the description of the vehicle
    public String getDescription() {
        return description;
    }

    // Sets the description of the vehicle
    public void setDescription(String description) {
        this.description = description;
    }

    // Gets if the vehicle is featured
    public boolean isFeatured() {
        return isFeatured;
    }

    // Sets if the vehicle is featured
    public void setFeatured(boolean featured) {
        isFeatured = featured;
    }

    // Gets if the vehicle is sold
    public boolean isSold() {
        return isSold;
    }

    // Sets if the vehicle is sold
    public void setSold(boolean sold) {
        isSold = sold;
    }

    // Gets the picture of the vehicle
    public String getPicture() {
        return picture;
    }

    // Sets the picture of the vehicle
    public void setPicture(String picture) {
        this.picture = picture;
    }
}
