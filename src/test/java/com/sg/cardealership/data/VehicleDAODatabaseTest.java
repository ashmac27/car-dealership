package com.sg.cardealership.data;

import com.sg.cardealership.TestApplicationConfiguration;
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
        Assert.assertEquals(1, vehicleList.size());
        Assert.assertTrue(vehicleList.contains(vehicle4));
        Assert.assertTrue(vehicleList.contains(vehicle1));
    }

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

    @Test
    @Sql(scripts = {"file:car_dealership_schema_creation.sql", "file:car_dealership_test_data.sql"})
    public void getVehicleListBySearchCriteria() {
        // ARRANGE
        // ACT
        // ASSERT
    }

    @Test
    @Sql(scripts = {"file:car_dealership_schema_creation.sql", "file:car_dealership_test_data.sql"})
    public void getVehicleById() {
        // ARRANGE
        // ACT
        // ASSERT
    }

    @Test
    @Sql(scripts = {"file:car_dealership_schema_creation.sql", "file:car_dealership_test_data.sql"})
    public void addVehicle() {
        // ARRANGE
        // ACT
        // ASSERT
    }

    @Test
    @Sql(scripts = {"file:car_dealership_schema_creation.sql", "file:car_dealership_test_data.sql"})
    public void deleteVehicle() {
        // ARRANGE
        // ACT
        // ASSERT
    }

    @Test
    @Sql(scripts = {"file:car_dealership_schema_creation.sql", "file:car_dealership_test_data.sql"})
    public void editVehicle() {
        // ARRANGE
        // ACT
        // ASSERT
    }
}