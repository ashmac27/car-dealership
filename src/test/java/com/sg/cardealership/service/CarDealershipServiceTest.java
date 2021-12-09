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
import com.sg.cardealership.data.*;
import com.sg.cardealership.model.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class CarDealershipServiceTest {

    @Autowired
    private CarDealershipService service;

    @Autowired
    private ContactMessageDAO contactMessageDAO;

    @Autowired
    private MakeDAO makeDAO;

    @Autowired
    private ModelDAO modelDAO;
    
    @Autowired
    PurchaseDAO purchaseDAO;

    @Autowired
    private SpecialDAO specialDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private VehicleDAO vehicleDAO;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    @Sql(scripts = {"file:car_dealership_schema_creation_test.sql", "file:car_dealership_test_data.sql"})
    public void getListOfFeaturedVehicles() {
        // ARRANGE
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setVIN("11111111111111111");
        vehicle1.setMakeId(1);
        vehicle1.setModelId(1);
        vehicle1.setType("new");
        vehicle1.setBodyStyle("SUV");
        vehicle1.setYear(2000);
        vehicle1.setTransmission("automatic");
        vehicle1.setColor("blue");
        vehicle1.setInterior("black");
        vehicle1.setMileage(0);
        vehicle1.setSalePrice(new BigDecimal("39999.99"));
        vehicle1.setMsrp(new BigDecimal("59999.99"));
        vehicle1.setDescription("This is car number 1");
        vehicle1.setFeatured(false);
        vehicle1.setSold(true);
        vehicle1.setPicture("picture1.png");

        Vehicle vehicle4 = new Vehicle();
        vehicle4.setVIN("22222222222222222");
        vehicle4.setMakeId(1);
        vehicle4.setModelId(2);
        vehicle4.setType("used");
        vehicle4.setBodyStyle("SUV");
        vehicle4.setYear(2010);
        vehicle4.setTransmission("manual");
        vehicle4.setColor("black");
        vehicle4.setInterior("white");
        vehicle4.setMileage(100);
        vehicle4.setSalePrice(new BigDecimal("49999.99"));
        vehicle4.setMsrp(new BigDecimal("69999.99"));
        vehicle4.setDescription("This is car number 2");
        vehicle4.setFeatured(true);
        vehicle4.setSold(false);
        vehicle4.setPicture("picture2.png");

        // ACT
        List<Vehicle> vehicleList = service.getListOfFeaturedVehicles();

        // ASSERT
        Assert.assertEquals(2, vehicleList.size());
        Assert.assertTrue(vehicleList.contains(vehicle4));
        Assert.assertFalse(vehicleList.contains(vehicle1));
    }

    @Test
    @Sql(scripts = {"file:car_dealership_schema_creation_test.sql", "file:car_dealership_test_data.sql"})
    public void getListOfVehiclesByCriteria() {
        // ARRANGE
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setVIN("11111111111111111");
        vehicle1.setMakeId(1);
        vehicle1.setModelId(1);
        vehicle1.setType("new");
        vehicle1.setBodyStyle("SUV");
        vehicle1.setYear(2000);
        vehicle1.setTransmission("automatic");
        vehicle1.setColor("blue");
        vehicle1.setInterior("black");
        vehicle1.setMileage(0);
        vehicle1.setSalePrice(new BigDecimal("39999.99"));
        vehicle1.setMsrp(new BigDecimal("59999.99"));
        vehicle1.setDescription("This is car number 1");
        vehicle1.setFeatured(false);
        vehicle1.setSold(true);
        vehicle1.setPicture("picture1.png");

        Vehicle vehicle4 = new Vehicle();
        vehicle4.setVIN("22222222222222222");
        vehicle4.setMakeId(1);
        vehicle4.setModelId(2);
        vehicle4.setType("used");
        vehicle4.setBodyStyle("SUV");
        vehicle4.setYear(2010);
        vehicle4.setTransmission("manual");
        vehicle4.setColor("black");
        vehicle4.setInterior("white");
        vehicle4.setMileage(100);
        vehicle4.setSalePrice(new BigDecimal("49999.99"));
        vehicle4.setMsrp(new BigDecimal("69999.99"));
        vehicle4.setDescription("This is car number 2");
        vehicle4.setFeatured(true);
        vehicle4.setSold(false);
        vehicle4.setPicture("picture2.png");

        SearchCriteria criteria = new SearchCriteria();
        criteria.setType("new");

        // ACT
        List<Vehicle> vehicleList = service.getListOfVehiclesByCriteria(criteria);

        // ASSERT
        Assert.assertEquals(2, vehicleList.size());
        Assert.assertFalse(vehicleList.contains(vehicle4));
        Assert.assertTrue(vehicleList.contains(vehicle1));
    }

    @Test
    @Sql(scripts = {"file:car_dealership_schema_creation_test.sql", "file:car_dealership_test_data.sql"})
    public void addVehicle() {
        // ARRANGE
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setVIN("12345678901234567");
        vehicle1.setMakeId(1);
        vehicle1.setModelId(1);
        vehicle1.setType("used");
        vehicle1.setBodyStyle("SUV");
        vehicle1.setYear(2000);
        vehicle1.setTransmission("automatic");
        vehicle1.setColor("blue");
        vehicle1.setInterior("black");
        vehicle1.setMileage(0);
        vehicle1.setSalePrice(new BigDecimal("39999.99"));
        vehicle1.setMsrp(new BigDecimal("59999.99"));
        vehicle1.setDescription("This is car number 1");
        vehicle1.setFeatured(false);
        vehicle1.setSold(true);
        vehicle1.setPicture("picture1.png");

        // ACT
        Vehicle addedVehicle = service.addVehicle(vehicle1);

        // ASSERT
        Assert.assertEquals(vehicle1, addedVehicle);
    }

    @Test
    @Sql(scripts = {"file:car_dealership_schema_creation_test.sql", "file:car_dealership_test_data.sql"})
    public void editVehicle() {
        // ARRANGE
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setVIN("12345678901234567");
        vehicle1.setMakeId(1);
        vehicle1.setModelId(1);
        vehicle1.setType("used");
        vehicle1.setBodyStyle("SUV");
        vehicle1.setYear(2000);
        vehicle1.setTransmission("automatic");
        vehicle1.setColor("blue");
        vehicle1.setInterior("black");
        vehicle1.setMileage(0);
        vehicle1.setSalePrice(new BigDecimal("39999.99"));
        vehicle1.setMsrp(new BigDecimal("59999.99"));
        vehicle1.setDescription("This is car number 1");
        vehicle1.setFeatured(false);
        vehicle1.setSold(true);
        vehicle1.setPicture("picture1.png");

        Vehicle addedVehicle = service.addVehicle(vehicle1);

        Assert.assertEquals(vehicle1, addedVehicle);

        // ACT
        vehicle1.setSalePrice(new BigDecimal("49999.99"));
        boolean isVehicleEdited = service.editVehicle(vehicle1.getVIN(), vehicle1);
        Vehicle getVehicle = service.getVehicleByVin(vehicle1.getVIN());

        // ASSERT
        Assert.assertTrue(isVehicleEdited);
        Assert.assertEquals(vehicle1, getVehicle);
    }

    @Test
    @Sql(scripts = {"file:car_dealership_schema_creation_test.sql", "file:car_dealership_test_data.sql"})
    public void deleteVehicle() {
        // ARRANGE
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setVIN("12345678901234567");
        vehicle1.setMakeId(1);
        vehicle1.setModelId(1);
        vehicle1.setType("used");
        vehicle1.setBodyStyle("SUV");
        vehicle1.setYear(2000);
        vehicle1.setTransmission("automatic");
        vehicle1.setColor("blue");
        vehicle1.setInterior("black");
        vehicle1.setMileage(0);
        vehicle1.setSalePrice(new BigDecimal("39999.99"));
        vehicle1.setMsrp(new BigDecimal("59999.99"));
        vehicle1.setDescription("This is car number 1");
        vehicle1.setFeatured(false);
        vehicle1.setSold(true);
        vehicle1.setPicture("picture1.png");

        Vehicle addedVehicle = service.addVehicle(vehicle1);

        Assert.assertEquals(vehicle1, addedVehicle);

        // ACT
        boolean isDeleted = service.deleteVehicle(vehicle1.getVIN());
        List<Vehicle> vehicleList = vehicleDAO.getVehicleList();

        // ASSERT
        Assert.assertTrue(isDeleted);
        Assert.assertFalse(vehicleList.contains(vehicle1));

    }

    @Test
    @Sql(scripts = {"file:car_dealership_schema_creation_test.sql"})
    public void getListOfUsers() {
        // Arrange
        User user1 = new User();
        user1.setFirstName("Admin_f1");
        user1.setLastName("Admin_l1");
        user1.setEmail("Admin_1@email.com");
        user1.setRole("admin");

        User user2 = new User();
        user2.setFirstName("Sales_f1");
        user2.setLastName("Sales_l1");
        user2.setEmail("Sales_1@email.com");
        user2.setRole("sales");

        User addedUser1 = service.addUser(user1);
        User addedUser2 = service.addUser(user2);

        // Act
        List<User> allUsers = service.getListOfUsers();

        // Assert
        Assert.assertEquals(2, allUsers.size());
        Assert.assertTrue(allUsers.contains(addedUser1));
        Assert.assertTrue(allUsers.contains(addedUser2));
    }

    @Test
    @Sql(scripts = {"file:car_dealership_schema_creation_test.sql"})
    public void addUser() {
        // Arrange
        User user = new User();
        user.setFirstName("Admin_f1");
        user.setLastName("Admin_l1");
        user.setEmail("Admin_1@email.com");
        user.setRole("admin");

        // Act
        User addedUser = service.addUser(user);

        // Assert
        Assert.assertEquals(user,addedUser );
    }

    @Test
    @Sql(scripts = {"file:car_dealership_schema_creation_test.sql"})
    public void editUser() {
        // Arrange
        User user = new User();
        user.setFirstName("Admin_f1");
        user.setLastName("Admin_l1");
        user.setEmail("Admin_1@email.com");
        user.setRole("admin");

        User addedUser = service.addUser(user);

        Assert.assertEquals(user,addedUser);

        // Act
        user.setEmail("Changed@email.com");
        boolean isUserEdited = service.editUser(user);
        User getUserFromDao = userDAO.getUserById(user.getUserId());

        // Assert
        Assert.assertTrue(isUserEdited);
        Assert.assertEquals(user, getUserFromDao);
    }

    @Test
    @Sql(scripts = {"file:car_dealership_schema_creation_test.sql", "file:car_dealership_test_data.sql"})
    public void getMakesList() {
        // Arrange
        Make make1 = new Make();
        make1.setMakeName("Ford");
        make1.setUserId(1);

        Make make2 = new Make();
        make2.setMakeName("Honda");
        make2.setUserId(1);

        Make addedMake1 = service.addMake(make1);
        Make addedMake2 = service.addMake(make2);

        // Act
        List<Make> allMakes = service.getMakesList();

        // Assert
        Assert.assertEquals(6, allMakes.size());
        Assert.assertTrue(allMakes.contains(addedMake1));
        Assert.assertTrue(allMakes.contains(addedMake2));
    }

    @Test
    @Sql(scripts = {"file:car_dealership_schema_creation_test.sql", "file:car_dealership_test_data.sql"})
    public void addMake() {
        // Arrange
        Make make = new Make();
        make.setMakeName("Ford");
        make.setUserId(1);

        // Act
        Make addedMake = service.addMake(make);

        // Assert
        Assert.assertEquals(make.getMakeName(), addedMake.getMakeName());
    }

    @Test
    @Sql(scripts = {"file:car_dealership_schema_creation_test.sql", "file:car_dealership_test_data.sql"})
    public void getModelsList() {
        // Arrange
        Model model1 = new Model();
        model1.setMakeId(1);
        model1.setModelName("Fiesta");
        //model1.setDateAdded(LocalDate.now());
        model1.setUserId(1);

        Model model2 = new Model();
        model2.setMakeId(1);
        model2.setModelName("Focus");
        //model1.setDateAdded(LocalDate.now());
        model2.setUserId(1);

        Model addedModel1 = service.addModel(model1);
        Model addedModel2 = service.addModel(model2);

        // Act
        List<Model> allModels = service.getModelsList();

        // Assert
        Assert.assertEquals(10, allModels.size());
        Assert.assertTrue(allModels.contains(addedModel1));
        Assert.assertTrue(allModels.contains(addedModel2));
    }

    @Test
    @Sql(scripts = {"file:car_dealership_schema_creation_test.sql"})
    public void getSpecialsList() {
        // ARRANGE
        Special special1 = new Special();
        special1.setTitle("Title_1");
        special1.setDescription("Description_1");
        Special special2 = new Special();
        special2.setTitle("Title_2");
        special2.setDescription("Description_2");
        Special addedSpecial1 = service.addSpecial(special1);
        Special addedSpecial2 = service.addSpecial(special2);

        // ACT
        List<Special> specialList = service.getSpecialsList();

        // ASSERT
        Assert.assertEquals(2, specialList.size());
        Assert.assertTrue(specialList.contains(addedSpecial1));
        Assert.assertTrue(specialList.contains(addedSpecial2));
    }

    @Test
    @Sql(scripts = {"file:car_dealership_schema_creation_test.sql"})
    public void addSpecial() {
        // ARRANGE
        Special special = new Special();
        special.setTitle("Title_1");
        special.setDescription("Description_1");

        // ACT
        Special addedSpecial = service.addSpecial(special);

        // ASSERT
        Assert.assertEquals(special, addedSpecial);
    }

    @Test
    @Sql(scripts = {"file:car_dealership_schema_creation_test.sql"})
    public void deleteSpecial() {
        // ARRANGE
        Special special = new Special();
        special.setTitle("Title_1");
        special.setDescription("Description_1");
        Special addedSpecial = service.addSpecial(special);
        Assert.assertEquals(special, addedSpecial);

        // ACT
        boolean deletedSpecial = service.deleteSpecial(special.getSpecialId());

        // ASSERT
        Assert.assertTrue(deletedSpecial);
    }

    @Test
    @Sql(scripts = {"file:car_dealership_schema_creation_test.sql"})
    public void postContactMessage() {
        // ARRANGE
        ContactMessage message = new ContactMessage();
        message.setMessage("Hello World");
        message.setEmail("email@email.com");
        message.setName("name");
        message.setPhone(null);

        // ACT
        ContactMessage newMessage = service.postContactMessage(message);
        message.setContactMessageId(newMessage.getContactMessageId());

        Assert.assertEquals(message, newMessage);
    }
    
    @Test
    @Sql(scripts = {"file:car_dealership_schema_creation_test.sql", "file:car_dealership_test_data.sql"})
    public void getVehicleByVin() {

        // ARRANGE
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setVIN("11111111111111111");
        vehicle1.setMakeId(1);
        vehicle1.setModelId(1);
        vehicle1.setType("new");
        vehicle1.setBodyStyle("SUV");
        vehicle1.setYear(2000);
        vehicle1.setTransmission("automatic");
        vehicle1.setColor("blue");
        vehicle1.setInterior("black");
        vehicle1.setMileage(0);
        vehicle1.setSalePrice(new BigDecimal("39999.99"));
        vehicle1.setMsrp(new BigDecimal("59999.99"));
        vehicle1.setDescription("This is car number 1");
        vehicle1.setFeatured(false);
        vehicle1.setSold(true);
        vehicle1.setPicture("picture1.png");

        Vehicle getVByVin = service.getVehicleByVin(vehicle1.getVIN());

        Assert.assertEquals(vehicle1, getVByVin);
    }

    @Test
    @Sql(scripts = {"file:car_dealership_schema_creation_test.sql", "file:car_dealership_test_data.sql"})
    public void postAddPurchase() {
        // ARRANGE
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setVIN("11111111111111111");
        vehicle1.setMakeId(1);
        vehicle1.setModelId(1);
        vehicle1.setType("new");
        vehicle1.setBodyStyle("SUV");
        vehicle1.setYear(2000);
        vehicle1.setTransmission("automatic");
        vehicle1.setColor("blue");
        vehicle1.setInterior("black");
        vehicle1.setMileage(0);
        vehicle1.setSalePrice(new BigDecimal("39999.99"));
        vehicle1.setMsrp(new BigDecimal("59999.99"));
        vehicle1.setDescription("This is car number 1");
        vehicle1.setFeatured(false);
        vehicle1.setSold(true);
        vehicle1.setPicture("picture1.png");

        Purchase newPurchase = new Purchase();
        newPurchase.setCity("city");
        newPurchase.setState("state");
        newPurchase.setPurchaseType("Cash");
        newPurchase.setEmail("test@test.com");
        newPurchase.setStreet1("street");
        newPurchase.setStreet2("street2");
        newPurchase.setZip("12345");
        newPurchase.setPhone("8144561231");
        newPurchase.setName("Test Mesterson");
        newPurchase.setVIN("11111111111111111");
        newPurchase.setSalespersonId(1);
        newPurchase.setPurchasePrice(BigDecimal.valueOf(2000.00).setScale(2));

        //Act
        Purchase testPurchase = service.postAddPurchase(vehicle1.getVIN(), newPurchase);

        //Assert
        Assert.assertEquals(newPurchase.getVIN(), testPurchase.getVIN());
    }
    
    
    
    
    
    
    @Test
    @Sql(scripts = {"file:car_dealership_schema_creation.sql","file:car_dealership_test_data.sql"})
    public void salesReportTest() {
        Map<String, Long> numberSoldByUser = new HashMap<String, Long>();
        Map<String, BigDecimal> totalSoldByUser = new HashMap<String, BigDecimal>();
        for(User u : service.getListOfUsers()) {
            String name = u.getFirstName()+" "+u.getLastName();
            List<Purchase> inventoryList = purchaseDAO.getPurchasesBySalespersonId(u.getUserId());
            if(inventoryList.size()==0) {
                numberSoldByUser.put(name,(long) 0);
                totalSoldByUser.put(name,BigDecimal.ZERO.setScale(2,RoundingMode.HALF_UP));
            } else {
                numberSoldByUser.put(name,(long) inventoryList.size());
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
            numberSoldByUser.replace(user, numberSoldByUser.get(user) - (long) record.get("Total Vehicles"));
            totalSoldByUser.replace(user, totalSoldByUser.get(user).subtract((BigDecimal) record.get("Total Sales")));
        }
        for(User u : service.getListOfUsers()) {
            String name = u.getFirstName()+" "+u.getLastName();
            assertTrue(numberSoldByUser.get(name).equals(0L));
            assertTrue(totalSoldByUser.get(name).equals(new BigDecimal("0.00")));
        }
    }
    
    @Test
    @Sql(scripts = {"file:car_dealership_schema_creation.sql","file:car_dealership_test_data.sql"})
    public void inventoryReportTest() {
        List<Vehicle> currentInventory = vehicleDAO.getVehicleList().stream().filter(vehicle -> !vehicle.isSold()).collect(Collectors.toList());
        BigDecimal totalInventoryPrice = new BigDecimal("0.00");
        for(Vehicle p : currentInventory) {
            totalInventoryPrice = totalInventoryPrice.add(p.getMsrp());
        }
        long inventorySize = 0;
        BigDecimal reportPrice = new BigDecimal("0.00");
        for(Map<String,Object> inventoryRecord : service.getInventoryReport(true)) {
            inventorySize+=(long) inventoryRecord.get("Count");
            reportPrice = reportPrice.add((BigDecimal) inventoryRecord.get("Stock Value"));
        }
        for(Map<String,Object> inventoryRecord : service.getInventoryReport(false)) {
            inventorySize+=(long) inventoryRecord.get("Count");
            reportPrice = reportPrice.add((BigDecimal) inventoryRecord.get("Stock Value"));
        }
        assertEquals(inventorySize,currentInventory.size());
        assertTrue(totalInventoryPrice.equals(reportPrice));
    }
}
