package controller;

import model.Special;
import model.Vehicle;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    public List<Vehicle> getListOfFeaturedVehicles() {
        return null;
    }
}
