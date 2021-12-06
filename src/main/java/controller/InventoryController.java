package controller;

import model.SearchCriteria;
import model.Vehicle;
import data.VehicleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.CarDealershipService;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private CarDealershipService service;
    private VehicleDAO vDao;

    @PostMapping("/new")
    public List<Vehicle> newVehicles (@RequestBody SearchCriteria criteria){
        return service.getListOfVehiclesByCriteria(criteria).stream().filter(x-> x.getMileage() == 0).collect(Collectors.toList());
    }

    @PostMapping("/used")
    public List<Vehicle> usedVehicles(@RequestBody SearchCriteria criteria){
        return service.getListOfVehiclesByCriteria(criteria).stream().filter(x-> x.getMileage() > 0).collect(Collectors.toList());
    }

    @PostMapping("/details/{vin}")

}
