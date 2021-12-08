package com.sg.cardealership.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sg.cardealership.service.CarDealershipServiceLayer;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private CarDealershipServiceLayer service;

    //TODO: Fix this!
    @GetMapping("/sales")
    public List<Map<String,Object>> salesReport(@RequestParam(value="id",required=false) Integer salespersonId,
            @RequestParam(value="from",required=false) @DateTimeFormat(pattern="MMddyyyy") LocalDate fromDate,
            @RequestParam(value="to",required=false) @DateTimeFormat(pattern="MMddyyyy") LocalDate toDate) {
        return service.getSalesReport(salespersonId, fromDate, toDate);
    }
    
    @GetMapping("/inventory")
    public Map<String,List<Map<String,Object>>> inventoryReport() {
        Map<String,List<Map<String,Object>>> report = new HashMap<>();
        report.put("New", service.getInventoryReport(false));
        report.put("Used", service.getInventoryReport(true));
        return report;
    }
}
