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
import com.sg.cardealership.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class UserDAODatabaseTest {

    @Autowired
    private UserDAO userDAO;

    @Before
    public void setUp() throws Exception{}

    @After
    public void tearDown() throws Exception{}

    @Test
    @Sql(scripts = "file:car_dealership_schema_creation.sql")
    public void getAllUsers() {
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

        User addedUser1 = userDAO.addUser(user1);
        User addedUser2 = userDAO.addUser(user2);

        // Act
        List<User> allUsers = userDAO.getAllUsers();

        // Assert
        Assert.assertEquals(2, allUsers);
        Assert.assertTrue(allUsers.contains(addedUser1));
        Assert.assertTrue(allUsers.contains(addedUser2));

    }

    @Test
    @Sql(scripts = "file:car_dealership_schema_creation.sql")
    public void editUser() {
        // Arrange
        User user = new User();
        user.setFirstName("Admin_f1");
        user.setLastName("Admin_l1");
        user.setEmail("Admin_1@email.com");
        user.setRole("admin");

        User addedUser = userDAO.addUser(user);

        Assert.assertEquals(user,addedUser);

        // Act
        user.setEmail("Changed@email.com");
        boolean isUserEdited = userDAO.editUser(user);
        User getUserFromDao = userDAO.getUserById(user.getUserId());

        // Assert
        Assert.assertTrue(isUserEdited);
        Assert.assertEquals(user, getUserFromDao);
    }

    @Test
    @Sql(scripts = "file:car_dealership_schema_creation.sql")
    public void addUser() {
        // Arrange
        User user = new User();
        user.setFirstName("Admin_f1");
        user.setLastName("Admin_l1");
        user.setEmail("Admin_1@email.com");
        user.setRole("admin");

        // Act
        User addedUser = userDAO.addUser(user);

        // Assert
        Assert.assertEquals(user,addedUser );
    }

    @Test
    @Sql(scripts = "file:car_dealership_schema_creation.sql")
    public void getUserById() {
        // Arrange
        User user = new User();
        user.setFirstName("Admin_f1");
        user.setLastName("Admin_l1");
        user.setEmail("Admin_1@email.com");
        user.setRole("admin");

        // Act
        User addedUser = userDAO.addUser(user);
        User getUserFromDao = userDAO.getUserById(addedUser.getUserId());

        // Assert
        Assert.assertEquals(addedUser,getUserFromDao);
    }
}