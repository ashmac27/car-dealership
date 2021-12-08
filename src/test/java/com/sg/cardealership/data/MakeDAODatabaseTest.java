package com.sg.cardealership.data;

import com.sg.cardealership.TestApplicationConfiguration;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import com.sg.cardealership.model.Make;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class MakeDAODatabaseTest {

    @Autowired
    private MakeDAO makeDAO;

    @Before
    public void setUp() throws Exception{}

    @After
    public void tearDown() throws Exception{}

    @Test
    @Sql(scripts = "file:car_dealership_schema_creation.sql")
    public void getAllMakes() {
        // Arrange
        Make make1 = new Make();
        make1.setMakeName("Make_Name");
        make1.setUserId(2);

        Make make2 = new Make();
        make2.setMakeName("Make_Name");
        make2.setUserId(2);

        Make addedMake1 = makeDAO.addMake(make1);
        Make addedMake2 = makeDAO.addMake(make2);

        // Act
        List<Make> allMakes = makeDAO.getAllMakes();

        // Assert
        Assert.assertEquals(2, allMakes);
        Assert.assertTrue(allMakes.contains(addedMake1));
        Assert.assertTrue(allMakes.contains(addedMake2));
    }

    @Test
    @Sql(scripts = "file:car_dealership_schema_creation.sql")
    public void addMake() {

        // Arrange
        Make make = new Make();
        make.setMakeName("Make_Name");
        make.setUserId(2);

        // Act
        Make addedMake = makeDAO.addMake(make);

        // Assert
        Assert.assertEquals(make, addedMake);
    }

    @Test
    @Sql(scripts = "file:car_dealership_schema_creation.sql")
    public void getMakeNameById() {
        // Arrange
        Make make = new Make();
        make.setMakeName("Make_Name");
        make.setUserId(2);

        // Act
        Make addedMake = makeDAO.addMake(make);
        String makeName = addedMake.getMakeName();
        String getMakeNameFromDao = makeDAO.getMakeNameById(addedMake.getMakeId());


        // Assert
        Assert.assertEquals(makeName, getMakeNameFromDao);
    }

    @Test
    @Sql(scripts = "file:car_dealership_schema_creation.sql")
    public void getMakeByName() {
        // Arrange
        Make make = new Make();
        make.setMakeName("Make_Name");
        make.setUserId(2);

        // Act
        Make addedMake = makeDAO.addMake(make);
        Make getMakeFromDao = makeDAO.getMakeByName(addedMake.getMakeName());

        // Assert
        Assert.assertEquals(addedMake,getMakeFromDao );


    }

    @Test
    @Sql(scripts = "file:car_dealership_schema_creation.sql")
    public void getMakeById() {
        // Arrange
        Make make = new Make();
        make.setMakeName("Make_Name");
        make.setUserId(2);

        // Act
        Make addedMake = makeDAO.addMake(make);
        Make getMakeFromDao = makeDAO.getMakeById(addedMake.getMakeId());

        // Assert
        Assert.assertEquals(addedMake,getMakeFromDao );
    }

    @Test
    @Sql(scripts = "file:car_dealership_schema_creation.sql")
    public void deleteMakeById() {
        // Arrange
        Make make = new Make();
        make.setMakeName("Make_Name");
        make.setUserId(2);
        Make addedMake = makeDAO.addMake(make);
        Assert.assertEquals(make,addedMake );

        // Act
        makeDAO.deleteMakeById(make.getMakeId());

        // Assert
        Assert.assertNull(addedMake);

    }
}