package service;

import model.SearchCriteria;
import model.User;
import model.Vehicle;

import java.util.List;

public interface CarDealershipService {
    List<Vehicle> getListOfVehiclesByCriteria(SearchCriteria criteria);
    Vehicle addVehicle(Vehicle vehicle);
    Boolean editVehicle(String vin, Vehicle vehicle);
    List<User> getListOfUsers();
    User addUser(User user);
    Boolean editUser(User user);
}
