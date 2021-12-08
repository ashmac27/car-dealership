package com.sg.cardealership.data;

import com.sg.cardealership.TestApplicationConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import com.sg.cardealership.model.Model;
import java.util.List;



@SpringBootTest(classes = TestApplicationConfiguration.class)
public class ModelDAODatabaseTest {

    @Autowired
    private ModelDAO modelDAO;

    @Before
    public void setUp() throws Exception{}

    @After
    public void tearDown() throws Exception{}

    @Test
    @Sql(scripts = "file:car_dealership_schema_creation_test.sql")
    public void getModelById() {
        // Arrange
        Model model = new Model();
        model.setMakeId(1);


        // Act


        // Assert
    }

    @Test
    @Sql(scripts = "file:car_dealership_schema_creation_test.sql")
    public void getModelByNameAndMake() {
        // Arrange


        // Act


        // Assert
    }

    @Test
    @Sql(scripts = "file:car_dealership_schema_creation_test.sql")
    public void getAllModels() {
        // Arrange


        // Act


        // Assert
    }

    @Test
    @Sql(scripts = "file:car_dealership_schema_creation_test.sql")
    public void addModel() {
        // Arrange


        // Act


        // Assert
    }

    @Test
    @Sql(scripts = "file:car_dealership_schema_creation_test.sql")
    public void deleteModelById() {
        // Arrange


        // Act


        // Assert
    }
}