package service;

import data.*;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class represents the implementation of the business logic
 */
@Service
public class CarDealershipServiceLayer implements CarDealershipService {

    @Autowired
    private VehicleDAO vehicleDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private MakeDAO makeDAO;

    @Autowired
    private ModelDAO modelDAO;

    @Autowired
    private SpecialDAO specialDAO;

    @Autowired
    private ContactMessageDAO contactDAO;

    // Gets a list of all featured vehicles
    @Override
    public List<Vehicle> getListOfFeaturedVehicles() {
        return vehicleDAO.getFeaturedVehicleList();
    }

    // Gets a list of vehicles given a search criteria
    @Override
    public List<Vehicle> getListOfVehiclesByCriteria(SearchCriteria criteria) {
        return vehicleDAO.getVehicleListBySearchCriteria(criteria);
    }

    // Adds a vehicle to the system
    @Override
    public Vehicle addVehicle(Vehicle vehicle) {
        return vehicleDAO.addVehicle(vehicle);
    }

    // Edits a vehicle given the VIN from the system
    @Override
    public Boolean editVehicle(String vin, Vehicle vehicle) {
        return vehicleDAO.editVehicle(vin, vehicle);
    }

    // Deletes a vehicles from the system
    @Override
    public Boolean deleteVehicle(String vin) {
        return vehicleDAO.deleteVehicle(vin);
    }

    // Gets a list of all users
    @Override
    public List<User> getListOfUsers() {
        return userDAO.getAllUsers();
    }

    // Adds a user to the system
    @Override
    public User addUser(User user) {
        return userDAO.addUser(user);
    }

    // Edits a user from the system
    @Override
    public Boolean editUser(User user) {
        //TODO: Might need to change up the method
        return userDAO.editUser(user) != null;
    }

    // Gets a list of all makes
    @Override
    public List<Make> getMakesList() {
        return makeDAO.getAllMakes();
    }

    // Adds a make into the system
    @Override
    public Make addMake(Make make) {
        return makeDAO.addMake(make);
    }

    // Gets a list of all models
    @Override
    public List<Model> getModelsList() {
        return modelDAO.getAllModels();
    }

    // Adds a model to the system
    @Override
    public Model addModel(Model model) {
        return modelDAO.addModel(model);
    }

    // Gets a list of all specials
    @Override
    public List<Special> getSpecialsList() {
        return specialDAO.getSpecialList();
    }

    // Adds a special to the system
    @Override
    public Special addSpecial(Special special) {
        return specialDAO.addSpecial(special);
    }

    // Deletes a special from the system
    @Override
    public Boolean deleteSpecial(int specialId) {
        return specialDAO.deleteSpecial(specialId);
    }

    // Post a contact message into the system
    @Override
    public ContactMessage postContactMessage(ContactMessage contactMessage) {
        return contactDAO.add(contactMessage);
    }
}
