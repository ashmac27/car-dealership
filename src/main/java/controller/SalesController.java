package controller;

import model.Purchase;
import model.SearchCriteria;
import model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.CarDealershipService;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sales")
public class SalesController {

    @Autowired
    private CarDealershipService service;

    @GetMapping("/index")
    public List<Vehicle> getVehiclesForSale (@RequestBody SearchCriteria criteria){
        return service.getListOfVehiclesByCriteria(criteria).stream().filter(vehicle-> !vehicle.isSold()).collect(Collectors.toList());
    }

    @GetMapping("/purchase/{VIN}")
    public Vehicle getVehiclePurchase(@PathVariable String VIN){
        return service.getVehicleByVin(VIN);
    }

    @PostMapping("/purchase/{VIN}")
    public Purchase postPurchase(@PathVariable String VIN, @RequestBody Purchase purchase){
        purchase.setVIN(VIN);
        return service.postAddPurchase(purchase);
    }
}
