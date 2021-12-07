package controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.CarDealershipServiceLayer;

@RestController
@RequestMapping("/report")
public class ReportController {
    
    CarDealershipServiceLayer service;
    
    @GetMapping("/sales")
    public List<Map<String,Object>> salesReport(@RequestParam(value="id",required=false) Integer salespersonId,
            @RequestParam(value="from",required=false) @DateTimeFormat(pattern="MMddyyyy") LocalDate fromDate,
            @RequestParam(value="to",required=false) @DateTimeFormat(pattern="MMddyyyy") LocalDate toDate) {
        // TODO: Import users and sales
        return service.getSalesReport(salespersonId, fromDate, toDate);
    }
    
    @GetMapping("/inventory")
    public List<Map<String,Object>> inventoryReport() {
        return service.getInventoryReport();
    }
}
