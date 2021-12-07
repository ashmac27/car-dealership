package controller;

import model.SearchCriteria;
import model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.CarDealershipService;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private CarDealershipService service;

    @PostMapping("/new")
    public List<Vehicle> getNewVehicles (@RequestBody SearchCriteria criteria){
        return service.getListOfVehiclesByCriteria(criteria).stream().filter(x-> x.getMileage() == 0).collect(Collectors.toList());
    }

    @PostMapping("/used")
    public List<Vehicle> getUsedVehicles(@RequestBody SearchCriteria criteria){
        return service.getListOfVehiclesByCriteria(criteria).stream().filter(x-> x.getMileage() > 0).collect(Collectors.toList());
    }

    @PostMapping("/details/{vin}")
    public Vehicle getVehicleDetails(@PathVariable String vin){
        return service.getVehicleByVin(vin);
    }
}
