package com.sg.cardealership.service;

import com.sg.cardealership.TestApplicationConfiguration;
import com.sg.cardealership.data.PurchaseDAO;
import com.sg.cardealership.model.Purchase;
import com.sg.cardealership.model.SearchCriteria;
import com.sg.cardealership.model.User;
import com.sg.cardealership.model.Vehicle;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class CarDealershipServiceTest {
    
    @Autowired
    CarDealershipService service;
    
    @Autowired
    PurchaseDAO purchaseDAO;
    
    @Test
    @Sql(scripts = {"file:car_dealership_schema_creation.sql","file:car_dealership_test_data.sql"})
    public void salesReportTest() {
        Map<String, Integer> numberSoldByUser = new HashMap<String, Integer>();
        Map<String, BigDecimal> totalSoldByUser = new HashMap<String, BigDecimal>();
        for(User u : service.getListOfUsers()) {
            String name = u.getFirstName()+" "+u.getLastName();
            List<Purchase> inventoryList = purchaseDAO.getPurchasesBySalespersonId(u.getUserId());
            if(inventoryList.size()==0) {
                numberSoldByUser.put(name,0);
                totalSoldByUser.put(name,BigDecimal.ZERO.setScale(2,RoundingMode.HALF_UP));
            } else {
                numberSoldByUser.put(name,inventoryList.size());
                BigDecimal total = BigDecimal.ZERO.setScale(2,RoundingMode.HALF_UP);
                for(Purchase p : inventoryList) {
                    total = total.add(p.getPurchasePrice());
                }
                totalSoldByUser.put(name, total);
            }
        }
        
        List<Map<String,Object>> report = service.getSalesReport(null,null,null);
        for(Map<String,Object> record : service.getSalesReport(null,null,null)) {
            String user = (String) record.get("user");
            numberSoldByUser.replace(user, numberSoldByUser.get(user) - (Integer) record.get("Total Vehicles"));
            totalSoldByUser.replace(user, totalSoldByUser.get(user).subtract(BigDecimal.valueOf((Double) record.get("Total Sales")).setScale(2,RoundingMode.HALF_UP)));
        }
        for(User u : service.getListOfUsers()) {
            String name = u.getFirstName()+" "+u.getLastName();
            assertTrue(numberSoldByUser.get(name).equals(0));
            assertTrue(totalSoldByUser.get(name).equals(BigDecimal.ZERO));
        }
    }
    
    @Test
    @Sql(scripts = {"file:car_dealership_schema_creation.sql","file:car_dealership_test_data.sql"})
    public void inventoryReportTest() {
        
    }
}
