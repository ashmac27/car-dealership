package com.sg.cardealership.controller;

import com.sg.cardealership.model.Purchase;
import com.sg.cardealership.model.SearchCriteria;
import com.sg.cardealership.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sg.cardealership.service.CarDealershipService;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sales")
public class SalesController {

    @Autowired
    private CarDealershipService service;

    // Gets all vehicles that are for sale
    @GetMapping("/index")
    public List<Vehicle> getVehiclesForSale (@RequestBody SearchCriteria criteria){
        criteria.setSold(false);
        return service.getListOfVehiclesByCriteria(criteria);
    }

    // Get the info for the car that is going to be purchased
    @GetMapping("/purchase/{VIN}")
    public Vehicle getVehiclePurchase(@PathVariable String VIN){
        return service.getVehicleByVin(VIN);
    }

    // Creates a purchase for the car
    @PostMapping("/purchase/{VIN}")
    public Purchase postPurchase(@PathVariable String VIN, @RequestBody Purchase purchase){
        return service.postAddPurchase(VIN, purchase);
    }
}
