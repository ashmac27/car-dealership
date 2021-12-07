package service;

import model.*;

import java.util.List;

public interface CarDealershipService {

    List<Vehicle> getListOfFeaturedVehicles();

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

    ContactMessage postContactMessage(ContactMessage contactMessage);

    Vehicle getVehicleByVin(String VIN);

    Purchase postAddPurchase(Purchase purchase);

}
