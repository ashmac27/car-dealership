package com.sg.cardealership.service;

import java.time.LocalDate;
import com.sg.cardealership.model.*;

import java.util.List;
import java.util.Map;

/**
 * This class represents the functionality of the business logic
 */
public interface CarDealershipService {

    // Gets the list of featured vehicles
    List<Vehicle> getListOfFeaturedVehicles();

    // Gets the list of vehicles given their search criteria
    List<Vehicle> getListOfVehiclesByCriteria(SearchCriteria criteria);

    // Adds a vehicle to the system
    Vehicle addVehicle(Vehicle vehicle);

    // Edits a vehicle from the system
    Boolean editVehicle(String vin, Vehicle vehicle);

    // Delete a vehicle from the system
    Boolean deleteVehicle(String vin);

    // Gets a list of users
    List<User> getListOfUsers();

    // Adds a user to the system
    User addUser(User user);

    // Edits a user from the system
    Boolean editUser(User user);

    // Gets a list of all makes
    List<Make> getMakesList();

    // Adds a make to the system
    Make addMake(Make make);

    // Gets a list of all models
    List<Model> getModelsList();

    // Adds a list to the system
    Model addModel(Model model);

    // Gets a list of all specials
    List<Special> getSpecialsList();

    // Adds a special to the system
    Special addSpecial(Special special);

    // Deletes a special from the system
    Boolean deleteSpecial(int specialId);

    // Adds/post a contact message to the system
    ContactMessage postContactMessage(ContactMessage contactMessage);
    
    /**
     * Gets sales reports, optional parameters provided
     * @param salespersonId ID of salesperson. Can be null
     * @param fromDate Beginning range for date. Can be null
     * @param toDate Ending range for date. Can be null
     * @return List of salespeople and their 
     */
    public List<Map<String,Object>> getSalesReport(Integer salespersonId, LocalDate fromDate, LocalDate toDate);
    
    /**
     * Gets inventory report. This returns the sum of cars by model and year that have been sold, and for how much
     * @return inventory report
     */
    public List<Map<String,Object>> getInventoryReport(boolean used);

    // Gets a single vehicle by its VIN
    Vehicle getVehicleByVin(String VIN);

    // Adds/post a new purchase
    Purchase postAddPurchase(String VIN, Purchase purchase);
}
