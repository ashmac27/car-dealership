package com.sg.cardealership.data;

import com.sg.cardealership.TestApplicationConfiguration;
import com.sg.cardealership.model.Special;
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

/**
 * Tests the special dao
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class SpecialDAODatabaseTest {

    @Autowired
    private SpecialDAO specialDAO;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    // Tests get special by id
    @Test
    @Sql(scripts = "file:car_dealership_schema_creation.sql")
    public void getSpecialById() {
        // ARRANGE
        Special special = new Special();
        special.setTitle("Title_1");
        special.setDescription("Description_1");

        // ACT
        Special addedSpecial = specialDAO.addSpecial(special);
        Special getSpecial = specialDAO.getSpecialById(addedSpecial.getSpecialId());

        // ASSERT
        Assert.assertEquals(addedSpecial, getSpecial);
    }

    // Tests get list of all specials
    @Test
    @Sql(scripts = "file:car_dealership_schema_creation_test.sql")
    public void getSpecialList() {
        // ARRANGE
        Special special1 = new Special();
        special1.setTitle("Title_1");
        special1.setDescription("Description_1");
        Special special2 = new Special();
        special2.setTitle("Title_2");
        special2.setDescription("Description_2");
        Special addedSpecial1 = specialDAO.addSpecial(special1);
        Special addedSpecial2 = specialDAO.addSpecial(special2);

        // ACT
        List<Special> specialList = specialDAO.getSpecialList();

        // ASSERT
        Assert.assertEquals(2, specialList.size());
        Assert.assertTrue(specialList.contains(addedSpecial1));
        Assert.assertTrue(specialList.contains(addedSpecial2));
    }

    // Tests adding specials
    @Test
    @Sql(scripts = "file:car_dealership_schema_creation_test.sql")
    public void addSpecial() {
        // ARRANGE
        Special special = new Special();
        special.setTitle("Title_1");
        special.setDescription("Description_1");

        // ACT
        Special addedSpecial = specialDAO.addSpecial(special);

        // ASSERT
        Assert.assertEquals(special, addedSpecial);
    }

    // Tests deleting a special
    @Test
    @Sql(scripts = "file:car_dealership_schema_creation_test.sql")
    public void deleteSpecial() {
        // ARRANGE
        Special special = new Special();
        special.setTitle("Title_1");
        special.setDescription("Description_1");
        Special addedSpecial = specialDAO.addSpecial(special);
        Assert.assertEquals(special, addedSpecial);

        // ACT
        boolean deletedSpecial = specialDAO.deleteSpecial(special.getSpecialId());

        // ASSERT
        Assert.assertTrue(deletedSpecial);
    }
}