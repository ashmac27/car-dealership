package com.sg.cardealership.controller;

import com.sg.cardealership.model.ContactMessage;
import com.sg.cardealership.model.Special;
import com.sg.cardealership.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sg.cardealership.service.CarDealershipService;

import java.util.List;

/**
 * Representing the endpoints for home-related pages
 */
@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    CarDealershipService service;

    // Gets the list of all featured vehicles for the index page
    @GetMapping("/index")
    public List<Vehicle> getListOfFeaturedVehicles() {
        return service.getListOfFeaturedVehicles();
    }

    // Gets the list of all specials
    @GetMapping("/specials")
    public List<Special> getSpecialsList() {
        return service.getSpecialsList();
    }

    // Gets the list of all contacts
    @PostMapping("/contact")
    public ContactMessage postContact(@RequestBody ContactMessage message) {
        return service.postContactMessage(message);
    }

}
