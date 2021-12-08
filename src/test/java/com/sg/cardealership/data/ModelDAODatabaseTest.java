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
import com.sg.cardealership.model.Model;
import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class ModelDAODatabaseTest {

    @Autowired
    private ModelDAO modelDAO;

    @Before
    public void setUp() throws Exception{}

    @After
    public void tearDown() throws Exception{}

    @Test
    @Sql(scripts = {"file:car_dealership_schema_creation.sql","file:car_dealership_test_data.sql"})
    public void getModelById() {
        // Arrange
        Model model = new Model();
        model.setMakeId(1);
        model.setModelName("Fiesta");
        model.setUserId(1);

        // Act
        Model addedModel = modelDAO.addModel(model);
        Model getModelFromDao = modelDAO.getModelById(addedModel.getModelId());

        // Assert
        Assert.assertEquals(addedModel,getModelFromDao);
    }

    @Test
    @Sql(scripts = {"file:car_dealership_schema_creation.sql","file:car_dealership_test_data.sql"})
    public void getAllModels() {
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

        Model addedModel1 = modelDAO.addModel(model1);
        Model addedModel2 = modelDAO.addModel(model2);

        // Act
        List<Model> allModels = modelDAO.getAllModels();

        // Assert
        Assert.assertEquals(10, allModels.size());
        Assert.assertTrue(allModels.contains(addedModel1));
        Assert.assertTrue(allModels.contains(addedModel2));
    }

    @Test
    @Sql(scripts = {"file:car_dealership_schema_creation.sql","file:car_dealership_test_data.sql"})
    public void addModel() {
        // Arrange
        Model model = new Model();
        model.setMakeId(1);
        model.setModelName("Fiesta");
        model.setUserId(1);

        // Act
        Model addedModel = modelDAO.addModel(model);

        // Assert
        Assert.assertEquals(model.getModelName(),addedModel.getModelName());
    }

    @Test
    @Sql(scripts = {"file:car_dealership_schema_creation.sql","file:car_dealership_test_data.sql"})
    public void deleteModelById() {
        // Arrange
        Model model = new Model();
        model.setMakeId(1);
        model.setModelName("Fiesta");
        model.setUserId(1);
        Model addedModel = modelDAO.addModel(model);
        Assert.assertEquals(model.getModelName(),addedModel.getModelName() );

        // Act
        modelDAO.deleteModelById(model.getModelId());
        Model deletedModel = modelDAO.getModelById(model.getModelId());

        // Assert
        Assert.assertNull(deletedModel);
    }
}