package com.sg.cardealership.data;

import com.sg.cardealership.TestApplicationConfiguration;
import com.sg.cardealership.model.SearchCriteria;
import com.sg.cardealership.model.Vehicle;
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

import static org.junit.Assert.*;

/**
 * Tests vehicle DAO
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class VehicleDAODatabaseTest {

    @Autowired
    private VehicleDAO vehicleDAO;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    // Tests getting a list of all vehicles
    @Test
    @Sql(scripts = {"file:car_dealership_schema_creation.sql", "file:car_dealership_test_data.sql"})
    public void getVehicleList() {
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
        List<Vehicle> vehicleList = vehicleDAO.getVehicleList();

        // ASSERT
        Assert.assertEquals(2, vehicleList.size());
        Assert.assertTrue(vehicleList.contains(vehicle4));
        Assert.assertTrue(vehicleList.contains(vehicle1));
    }

    // Tests getting a list of featured vehicles
    @Test
    @Sql(scripts = {"file:car_dealership_schema_creation.sql", "file:car_dealership_test_data.sql"})
    public void getFeaturedVehicleList() {
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
        List<Vehicle> vehicleList = vehicleDAO.getFeaturedVehicleList();

        // ASSERT
        Assert.assertEquals(1, vehicleList.size());
        Assert.assertTrue(vehicleList.contains(vehicle4));
        Assert.assertFalse(vehicleList.contains(vehicle1));
    }

    // Tests get a list of vehicles given a criteria
    @Test
    @Sql(scripts = {"file:car_dealership_schema_creation.sql", "file:car_dealership_test_data.sql"})
    public void getVehicleListBySearchCriteria() {
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
        List<Vehicle> vehicleList = vehicleDAO.getVehicleListBySearchCriteria(criteria);

        // ASSERT
        Assert.assertEquals(1, vehicleList.size());
        Assert.assertFalse(vehicleList.contains(vehicle4));
        Assert.assertTrue(vehicleList.contains(vehicle1));
    }

    // Gets a vehicle by Id
    @Test
    @Sql(scripts = {"file:car_dealership_schema_creation.sql", "file:car_dealership_test_data.sql"})
    public void getVehicleById() {
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

        // ACT
        Vehicle getVehicle = vehicleDAO.getVehicleById(vehicle1.getVIN());

        // ASSERT
        Assert.assertEquals(vehicle1, getVehicle);
    }

    // Tests adding a vehicle to a database
    @Test
    @Sql(scripts = {"file:car_dealership_schema_creation.sql", "file:car_dealership_test_data.sql"})
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
        Vehicle addedVehicle = vehicleDAO.addVehicle(vehicle1);

        // ASSERT
        Assert.assertEquals(vehicle1, addedVehicle);
    }

    // Tests deleting a vehicle
    @Test
    @Sql(scripts = {"file:car_dealership_schema_creation.sql", "file:car_dealership_test_data.sql"})
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

        Vehicle addedVehicle = vehicleDAO.addVehicle(vehicle1);

        Assert.assertEquals(vehicle1, addedVehicle);

        // ACT
        boolean isDeleted = vehicleDAO.deleteVehicle(vehicle1.getVIN());
        List<Vehicle> vehicleList = vehicleDAO.getVehicleList();

        // ASSERT
        Assert.assertTrue(isDeleted);
        Assert.assertFalse(vehicleList.contains(vehicle1));

    }

    // Tests editing a vehicle
    @Test
    @Sql(scripts = {"file:car_dealership_schema_creation.sql", "file:car_dealership_test_data.sql"})
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

        Vehicle addedVehicle = vehicleDAO.addVehicle(vehicle1);

        Assert.assertEquals(vehicle1, addedVehicle);

        // ACT
        vehicle1.setSalePrice(new BigDecimal("49999.99"));
        boolean isVehicleEdited = vehicleDAO.editVehicle(vehicle1.getVIN(), vehicle1);
        Vehicle getVehicle = vehicleDAO.getVehicleById(vehicle1.getVIN());

        // ASSERT
        Assert.assertTrue(isVehicleEdited);
        Assert.assertEquals(vehicle1, getVehicle);
    }
}