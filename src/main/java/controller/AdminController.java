package controller;

import model.SearchCriteria;
import model.User;
import model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.CarDealershipService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CarDealershipService service;

    // Gets a list of vehicles given a search criteria
    @PostMapping("/vehicles")
    public List<Vehicle> vehicles(@RequestBody SearchCriteria criteria) {
        return service.getListOfVehiclesByCriteria(criteria);
    }

    // Adds vehicles to the system, and return that vehicle if successful
    @PostMapping("/addvehicle")
    public Vehicle addVehicle(@RequestBody Vehicle vehicle) {
        return service.addVehicle(vehicle);
    }

    // Edits a vehicle given its vehicle id
    @PostMapping("/editvehicle/{vin}")
    public Boolean editVehicle(@PathVariable String vin, @RequestBody Vehicle vehicle) {
        return service.editVehicle(vin, vehicle);
    }

    // Gets all users from the system
    @PostMapping("/users")
    public List<User> users() {
        return service.getListOfUsers();
    }

    // Adds a user to the system
    @PostMapping("/adduser")
    public User addUser(@RequestBody User user) {
        return service.addUser(user);
    }

    // Edits a user from the system
    @PostMapping("/edituser")
    public Boolean editUser(@RequestBody User user) {
        return service.editUser(user);
    }

    // Changes the password for a user
    @PostMapping("/changepassword")
    public void changePassword() {
        //TODO: There is no password in our database. So, this method might be omitted.
    }
}
