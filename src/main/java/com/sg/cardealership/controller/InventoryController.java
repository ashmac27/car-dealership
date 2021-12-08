package com.sg.cardealership.controller;

import com.sg.cardealership.model.SearchCriteria;
import com.sg.cardealership.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sg.cardealership.service.CarDealershipService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private CarDealershipService service;

    // Gets all new cars with a given criteria
    @PostMapping("/new")
    public List<Vehicle> newVehicles (@RequestBody SearchCriteria criteria){
        criteria.setType("new");
        return service.getListOfVehiclesByCriteria(criteria);
    }

    // Gets all used cars with a given criteria
    @PostMapping("/used")
    public List<Vehicle> usedVehicles(@RequestBody SearchCriteria criteria){
        criteria.setType("used");
        return service.getListOfVehiclesByCriteria(criteria).stream().filter(x-> x.getMileage() > 0).collect(Collectors.toList());
    }

    // Gets a vehicle given its VIN
    @GetMapping("/details/{VIN}")
    public Vehicle getVehicleDetails(@PathVariable String VIN){
        return service.getVehicleByVin(VIN);
    }
}
