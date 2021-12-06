package service;

import data.UserDAO;
import data.VehicleDAO;
import model.SearchCriteria;
import model.User;
import model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarDealershipServiceLayer implements CarDealershipService {

    @Autowired
    private VehicleDAO vehicleDAO;

    @Autowired
    private UserDAO userDAO;

    @Override
    public List<Vehicle> getListOfVehiclesByCriteria(SearchCriteria criteria) {
        return vehicleDAO.getVehicleListBySearchCriteria(criteria);
    }

    @Override
    public Vehicle addVehicle(Vehicle vehicle) {
        return vehicleDAO.addVehicle(vehicle);
    }

    @Override
    public Boolean editVehicle(String vin, Vehicle vehicle) {
        return vehicleDAO.editVehicle(vin, vehicle);
    }

    @Override
    public List<User> getListOfUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public User addUser(User user) {
        return userDAO.addUser(user);
    }

    @Override
    public Boolean editUser(User user) {
        //TODO: Might need to change up the method
        return userDAO.editUser(user) != null;
    }
}
