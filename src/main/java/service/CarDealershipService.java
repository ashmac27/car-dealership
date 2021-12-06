package service;

import model.*;

import java.util.List;

public interface CarDealershipService {
    List<Vehicle> getListOfVehiclesByCriteria(SearchCriteria criteria);

    Vehicle addVehicle(Vehicle vehicle);

    Boolean editVehicle(String vin, Vehicle vehicle);

    List<User> getListOfUsers();

    User addUser(User user);

    Boolean editUser(User user);

    List<Make> getMakesList();

    List<Model> getModelsList();

    List<Special> getSpecialsList();
}
