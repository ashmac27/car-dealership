package controller;

import model.ContactMessage;
import model.Special;
import model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.CarDealershipService;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    CarDealershipService service;

    @GetMapping("/index")
    public List<Vehicle> getListOfFeaturedVehicles() {
        return service.getListOfFeaturedVehicles();
    }

    @GetMapping("/specials")
    public List<Special> getSpecialsList() {
        return service.getSpecialsList();
    }

    @PostMapping("/contact")
    public ContactMessage postContact(@RequestBody ContactMessage message) {
        return service.postContactMessage(message);
    }

}
