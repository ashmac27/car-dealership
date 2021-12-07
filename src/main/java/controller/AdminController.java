package controller;

import model.*;
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

    // Deletes Vehicle given its id
    @DeleteMapping("/editvehicle/{vin}")
    public Boolean deleteVehicle(@PathVariable String vin) {
        return service.deleteVehicle(vin);
    }

    // Gets all users from the system
    @GetMapping("/users")
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

    // Gets all makes from the system
    @GetMapping("/makes")
    public List<Make> getMakesList() {
        return service.getMakesList();
    }

    // Gets all makes from the system
    @PostMapping("/makes/add")
    public Make addMake(@RequestBody Make make) {
        return service.addMake(make);
    }

    // Gets all models from the system
    @GetMapping("/models")
    public List<Model> getModelsList() {
        return service.getModelsList();
    }

    // Gets all models from the system
    @PostMapping("/models/add")
    public Model addModel(@RequestBody Model model) {
        return service.addModel(model);
    }

    // Gets all specials from the system
    @GetMapping("/specials")
    public List<Special> getSpecialsList() {
        return service.getSpecialsList();
    }

    // Gets all specials from the system
    @PostMapping("/specials/add")
    public Special addSpecial(@RequestBody Special special) {
        return service.addSpecial(special);
    }

    // Deletes a special given its id
    @DeleteMapping("/specials/{specialId}")
    public Boolean deleteSpecial(@PathVariable int specialId) {
        return service.deleteSpecial(specialId);
    }

}
