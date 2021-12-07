package model;

import java.math.BigDecimal;

/**
 * This class represents the search criteria for searching specific kinds of vehicles
 */
public class SearchCriteria {
    private String type;
    private Boolean isSold;
    private String model;
    private String make;
    private Integer year;
    private Integer minYear;
    private Integer maxYear;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;

    // Gets the type of the vehicle search criteria
    public String getType() {
        return type;
    }

    // Sets the type of the vehicle search criteria
    public void setType(String type) {
        this.type = type;
    }

    // Gets the model of the vehicle search criteria
    public String getModel() {
        return model;
    }

    // Sets the model of the vehicle search criteria
    public void setModel(String model) {
        this.model = model;
    }

    // Gets the make of the vehicle search criteria
    public String getMake() {
        return make;
    }

    // Sets the make of the vehicle search criteria
    public void setMake(String make) {
        this.make = make;
    }

    // Gets the max year of the vehicle search criteria
    public Integer getMaxYear() {
        return maxYear;
    }

    // Sets the max year of the vehicle search criteria
    public void setMaxYear(Integer maxYear) {
        this.maxYear = maxYear;
    }

    // Gets the min year of the vehicle search criteria
    public Integer getMinYear() {
        return minYear;
    }

    // Sets the min year of the vehicle search criteria
    public void setMinYear(Integer minYear) {
        this.minYear = minYear;
    }

    // Gets the specific year of the vehicle search criteria
    public Integer getYear() {
        return year;
    }

    // Sets the specific year of the vehicle search criteria
    public void setYear(Integer year) {
        this.year = year;
    }

    // Gets the min price of the vehicle search criteria
    public BigDecimal getMinPrice() {
        return minPrice;
    }

    // Sets the min price of the vehicle search criteria
    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    // Gets the max price of the vehicle search criteria
    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    // Sets the max price of the vehicle search criteria
    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    // Gets the boolean isSold of the vehicle search criteria
    public Boolean getSold() {
        return isSold;
    }

    // Sets the boolean isSold of the vehicle search criteria
    public void setSold(Boolean sold) {
        isSold = sold;
    }
}
