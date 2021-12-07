package data;

import model.SearchCriteria;
import model.Vehicle;

import java.util.List;

public interface VehicleDAO {
    // Gets the list of all vehicles
    List<Vehicle> getVehicleList();

    // Gets the list of all featured vehicles
    List<Vehicle> getFeaturedVehicleList();

    // Gets the list of vehicles by the search criteria
    List<Vehicle> getVehicleListBySearchCriteria(SearchCriteria criteria);

    // Gets a vehicles by ID
    Vehicle getVehicleById(String VIN);

    // Adds Vehicle and return back that vehicle if the query is successful
    Vehicle addVehicle(Vehicle vehicle);

    // Delete Vehicle and return back that vehicle if the query is successful
    boolean deleteVehicle(String VIN);

    // Edit Vehicle and return back that vehicle if the query is successful
    boolean editVehicle(String vin, Vehicle vehicle);
}
