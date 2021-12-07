package com.sg.cardealership.controller;

import com.sg.cardealership.model.SearchCriteria;
import com.sg.cardealership.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sg.cardealership.service.CarDealershipService;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private CarDealershipService service;

    // TODO: Improve method
    @PostMapping("/new")
    public List<Vehicle> newVehicles (@RequestBody SearchCriteria criteria){
        return service.getListOfVehiclesByCriteria(criteria).stream().filter(x-> x.getMileage() == 0).collect(Collectors.toList());
    }

    // TODO: Improve method
    @PostMapping("/used")
    public List<Vehicle> usedVehicles(@RequestBody SearchCriteria criteria){
        return service.getListOfVehiclesByCriteria(criteria).stream().filter(x-> x.getMileage() > 0).collect(Collectors.toList());
    }

    @GetMapping("/details/{VIN}")
    public Vehicle getVehicleDetails(@PathVariable String VIN){
        return service.getVehicleByVin(VIN);
    }
}
