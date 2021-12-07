package service;

import data.*;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<Vehicle> getListOfFeaturedVehicles() {
        return vehicleDAO.getFeaturedVehicleList();
    }

    // Gets a list of vehicles given a search criteria
    @Override
    public List<Vehicle> getListOfVehiclesByCriteria(SearchCriteria criteria) {
        return vehicleDAO.getVehicleListBySearchCriteria(criteria);
    }

    // Adds a vehicle
    @Override
    public Vehicle addVehicle(Vehicle vehicle) {
        return vehicleDAO.addVehicle(vehicle);
    }

    // Edits a vehicle given the VIN
    @Override
    public Boolean editVehicle(String vin, Vehicle vehicle) {
        return vehicleDAO.editVehicle(vin, vehicle);
    }

    @Override
    public Boolean deleteVehicle(String vin) {
        return vehicleDAO.deleteVehicle(vin);
    }

    // Gets a list of all users
    @Override
    public List<User> getListOfUsers() {
        return userDAO.getAllUsers();
    }

    // Adds a user
    @Override
    public User addUser(User user) {
        return userDAO.addUser(user);
    }

    // Edits a user
    @Override
    public Boolean editUser(User user) {
        //TODO: Might need to change up the method
        return userDAO.editUser(user) != null;
    }

    @Override
    public List<Make> getMakesList() {
        return makeDAO.getAllMakes();
    }

    @Override
    public Make addMake(Make make) {
        return makeDAO.addMake(make);
    }

    @Override
    public List<Model> getModelsList() {
        return modelDAO.getAllModels();
    }

    @Override
    public Model addModel(Model model) {
        return modelDAO.addModel(model);
    }

    @Override
    public List<Special> getSpecialsList() {
        return specialDAO.getSpecialList();
    }

    @Override
    public Special addSpecial(Special special) {
        return specialDAO.addSpecial(special);
    }

    @Override
    public Boolean deleteSpecial(int specialId) {
        return specialDAO.deleteSpecial(specialId);
    }

    @Override
    public ContactMessage postContactMessage(ContactMessage contactMessage) {
        return contactDAO.add(contactMessage);
    }

    @Override
    public Vehicle getVehicleByVin(String vin){
        return vehicleDAO.getVehicleById(vin);
    }
}
