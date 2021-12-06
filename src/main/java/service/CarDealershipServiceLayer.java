package service;

import model.SearchCriteria;
import model.User;
import model.Vehicle;

import java.util.List;

public class CarDealershipServiceLayer implements CarDealershipService {
    @Override
    public List<Vehicle> getListOfVehiclesByCriteria(SearchCriteria criteria) {
        return null;
    }

    @Override
    public Vehicle addVehicle(Vehicle vehicle) {
        return null;
    }

    @Override
    public Boolean editVehicle(String vin, Vehicle vehicle) {
        return null;
    }

    @Override
    public List<User> getListOfUsers() {
        return null;
    }

    @Override
    public User addUser(User user) {
        return null;
    }

    @Override
    public Boolean editUser(User user) {
        return null;
    }
}
