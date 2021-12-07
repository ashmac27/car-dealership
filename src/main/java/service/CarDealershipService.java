package service;

import java.time.LocalDate;
import model.*;

import java.util.List;
import java.util.Map;

public interface CarDealershipService {
    List<Vehicle> getListOfVehiclesByCriteria(SearchCriteria criteria);

    Vehicle addVehicle(Vehicle vehicle);

    Boolean editVehicle(String vin, Vehicle vehicle);

    Boolean deleteVehicle(String vin);

    List<User> getListOfUsers();

    User addUser(User user);

    Boolean editUser(User user);

    List<Make> getMakesList();

    Make addMake(Make make);

    List<Model> getModelsList();

    Model addModel(Model model);

    List<Special> getSpecialsList();

    Special addSpecial(Special special);

    Boolean deleteSpecial(int specialId);
    
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
    public List<Map<String,Object>> getInventoryReport();
}
