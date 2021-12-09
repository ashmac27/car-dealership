package com.sg.cardealership.data;

import com.sg.cardealership.TestApplicationConfiguration;
import com.sg.cardealership.model.Purchase;
import com.sg.cardealership.model.SearchCriteria;
import com.sg.cardealership.model.Special;
import com.sg.cardealership.model.User;
import com.sg.cardealership.model.Vehicle;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
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
    public void addPurchase() {
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
        old.setStreet2("street2");
        old.setZip("12345");
        old.setPhone("8144561231");
        old.setName("Test Mesterson");
        old.setVIN(v.getVIN());
        old.setSalespersonId(u.getUserId());
        old.setPurchasePrice(BigDecimal.valueOf(2000.00).setScale(2));
        
        old = purchaseDAO.add(old);
        // Test normal add
        assertEquals(old,purchaseDAO.getPurchaseByPurchaseId(old.getPurchaseId()));
        purchaseDAO.deleteByPurchaseId(old.getPurchaseId());
        // Test null phone add
        old.setPhone(null);
        old = purchaseDAO.add(old);
        assertEquals(old,purchaseDAO.getPurchaseByPurchaseId(old.getPurchaseId()));
        // Test null email add
        purchaseDAO.deleteByPurchaseId(old.getPurchaseId());
        old.setPhone("8144561231");
        old.setEmail(null);
        old = purchaseDAO.add(old);
        assertEquals(old,purchaseDAO.getPurchaseByPurchaseId(old.getPurchaseId()));
        // Test street2 email add
        purchaseDAO.deleteByPurchaseId(old.getPurchaseId());
        old.setEmail("test@test.com");
        old.setStreet2(null);
        old = purchaseDAO.add(old);
        assertEquals(old,purchaseDAO.getPurchaseByPurchaseId(old.getPurchaseId()));
        
        // test incorrect add (no ZIP), unsure which exception throws
        old.setZip(null);
        final Purchase p = old;
        assertThrows(Exception.class, () -> {
            purchaseDAO.add(p);
        });
    }
    
    @Test
    @Sql(scripts = {"file:car_dealership_schema_creation.sql","file:car_dealership_test_data.sql"})
    public void getPurchaseByPurchaseId() {
        purchaseDAO.getAllPurchases().forEach((purchase) -> {
            assertEquals(purchase, purchaseDAO.getPurchaseByPurchaseId(purchase.getPurchaseId()));
        });
        // Tests if exception is thrown on empty query. Unsure which exception is thrown
        assertThrows(Exception.class, () -> {
            purchaseDAO.getPurchaseByPurchaseId(-1);
        });
    }
    
    @Test
    @Sql(scripts = {"file:car_dealership_schema_creation.sql","file:car_dealership_test_data.sql"})
    public void getAllPurchases() {
        // Deletes all old records
        List<Purchase> oldList = purchaseDAO.getAllPurchases();
        oldList.forEach(purchase -> purchaseDAO.deleteByPurchaseId(purchase.getPurchaseId()));
        // Reinserts all old records (with new IDs)
        oldList.replaceAll(purchase -> {
            return purchaseDAO.add(purchase);
        });
        // Assert that each item is in the new list
        List<Purchase> newList = purchaseDAO.getAllPurchases();
        for(Purchase p : newList) {
            assertTrue(oldList.contains(p));
        }
        
        // Adds a new value
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
        
        assertTrue(purchaseDAO.getAllPurchases().contains(old));
    }
    
    @Test
    @Sql(scripts = {"file:car_dealership_schema_creation.sql","file:car_dealership_test_data.sql"})
    public void deletePurchase() {
        List<Purchase> oldList = purchaseDAO.getAllPurchases();
        oldList.forEach(purchase -> {
            // Deletes value
            assertTrue(purchaseDAO.deleteByPurchaseId(purchase.getPurchaseId()));
            // Makes sure it doesn't delete another record
            assertFalse(purchaseDAO.deleteByPurchaseId(purchase.getPurchaseId()));
        });
    }
    
    @Test
    @Sql(scripts = {"file:car_dealership_schema_creation.sql","file:car_dealership_test_data.sql"})
    public void updatePurchase() {
        List<Purchase> oldList = purchaseDAO.getAllPurchases();
        oldList.replaceAll(purchase -> {
            purchase.setPurchasePrice(BigDecimal.valueOf((new Random()).nextDouble() * 1000).setScale(2, RoundingMode.HALF_UP));
            return purchase;
        });
        oldList.forEach(purchase -> {
            assertTrue(purchaseDAO.update(purchase));
            purchase.setPurchaseId(0);
            assertFalse(purchaseDAO.update(purchase));
        });
        // City can't be null, unsure of which exception will throw
        Purchase p = oldList.get(0);
        p.setCity(null);
        assertFalse(purchaseDAO.update(p));
    }
    
    @Test
    @Sql(scripts = {"file:car_dealership_schema_creation.sql","file:car_dealership_test_data.sql"})
    public void getByVehicleId() {
        vehicleDAO.getVehicleList().forEach(vehicle -> {
            if(vehicle.isSold()) {
                Purchase p = purchaseDAO.getPurchaseByVehicleId(vehicle.getVIN());
                assertEquals(vehicle.getVIN(),p.getVIN());
            } else {
                // Should throw exception, unsure which
                assertThrows(Exception.class, () -> {
                    purchaseDAO.getPurchaseByVehicleId(vehicle.getVIN());
                });
            }
        });
    }
    
    @Test
    @Sql(scripts = {"file:car_dealership_schema_creation.sql","file:car_dealership_test_data.sql"})
    public void getPurchasesBySalespersonId() {
        Map<Integer, Integer> idToNumberOfSales = new HashMap<Integer,Integer>();
        Map<Integer, List<Purchase>> idToListOfSales = new HashMap<Integer,List<Purchase>>();
        // Populates 2 maps, 1 with a mutable count of sales per user, the other with a list of sales to each user
        for(User u : userDAO.getAllUsers()) {
            List<Purchase> list = purchaseDAO.getPurchasesBySalespersonId(u.getUserId());
            idToNumberOfSales.put(u.getUserId(),list.size());
            idToListOfSales.put(u.getUserId(), list);
        }
        // Verifies each purchase is in the right list
        for(Purchase p : purchaseDAO.getAllPurchases()) {
            // Decrements map value. This will be used in verifying missing or overallocated purchases
            idToNumberOfSales.replace(p.getSalespersonId(), idToNumberOfSales.get(p.getSalespersonId())-1);
            assertTrue(idToListOfSales.get(p.getSalespersonId()).contains(p));
        }
        idToNumberOfSales.entrySet().stream().forEach(entry -> {
            // Values should be 0 if size is correct
            assertEquals(Integer.valueOf(0),entry.getValue());
        });
    }
}