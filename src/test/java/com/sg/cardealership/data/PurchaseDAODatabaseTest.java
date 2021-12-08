package com.sg.cardealership.data;

import com.sg.cardealership.TestApplicationConfiguration;
import com.sg.cardealership.model.Purchase;
import com.sg.cardealership.model.SearchCriteria;
import com.sg.cardealership.model.Special;
import com.sg.cardealership.model.User;
import com.sg.cardealership.model.Vehicle;
import java.math.BigDecimal;
import java.util.stream.Collectors;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class PurchaseDAODatabaseTest {

    @Autowired
    private PurchaseDAO purchaseDAO;
    
    @Autowired
    private UserDAO userDAO;
    
    @Autowired
    private VehicleDAO vehicleDAO;

    @Test
    @Sql(scripts = {"file:car_dealership_schema_creation.sql","file:car_dealership_test_data.sql"})
    public void getPurchaseById() {
        SearchCriteria sc = new SearchCriteria();
        sc.setSold(false);
        Vehicle v = vehicleDAO.getVehicleListBySearchCriteria(sc).get(0);
        User u = userDAO.getAllUsers().stream().filter(user -> user.getRole().toLowerCase().equals("sales")).collect(Collectors.toList()).get(0);
        
        Purchase old = new Purchase();
        
        old.setCity("city");
        old.setState("state");
        old.setPurchaseType("Cash");
        old.setEmail("test@test.com");
        old.setStreet1("street");
        old.setZip("12345");
        old.setPhone("8144561231");
        old.setName("Test Mesterson");
        old.setVIN(v.getVIN());
        old.setSalespersonId(u.getUserId());
        old.setPurchasePrice(BigDecimal.valueOf(2000.00).setScale(2));
        
        old = purchaseDAO.add(old);
        
        assertEquals(old,purchaseDAO.getPurchaseByPurchaseId(old.getPurchaseId()));
    }
}
