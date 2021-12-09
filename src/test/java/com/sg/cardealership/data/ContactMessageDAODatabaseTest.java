package com.sg.cardealership.data;

import com.sg.cardealership.TestApplicationConfiguration;
import com.sg.cardealership.model.ContactMessage;
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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class ContactMessageDAODatabaseTest {

    @Autowired
    private ContactMessageDAO contactDao;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    @Sql(scripts = "file:car_dealership_schema_creation_test.sql")
    public void add() {
        // ARRANGE
        ContactMessage message = new ContactMessage();
        message.setMessage("Hello World");
        message.setEmail("email@email.com");
        message.setName("name");
        message.setPhone(null);

        // ACT
        ContactMessage newMessage = contactDao.add(message);
        message.setContactMessageId(newMessage.getContactMessageId());

        Assert.assertEquals(message, newMessage);

    }

    @Test
    @Sql(scripts = "file:car_dealership_schema_creation_test.sql")
    public void getAll() {
        // ARRANGE
        ContactMessage message1 = new ContactMessage();
        message1.setMessage("Hello World1");
        message1.setEmail("email1@email.com");
        message1.setName("name1");
        message1.setPhone(null);

        ContactMessage message2 = new ContactMessage();
        message2.setMessage("Hello World2");
        message2.setEmail(null);
        message2.setName("name2");
        message2.setPhone("111-111-1111");

        ContactMessage newMessage1 = contactDao.add(message1);
        message1.setContactMessageId(newMessage1.getContactMessageId());
        ContactMessage newMessage2 = contactDao.add(message2);
        message2.setContactMessageId(newMessage2.getContactMessageId());

        // ACT
        List<ContactMessage> contactMessageList = contactDao.getAll();

        // ASSERT
        Assert.assertTrue(contactMessageList.contains(newMessage1));
        Assert.assertTrue(contactMessageList.contains(newMessage2));
    }

    @Test
    @Sql(scripts = "file:car_dealership_schema_creation_test.sql")
    public void getContactMessageById() {
        // ARRANGE
        ContactMessage message = new ContactMessage();
        message.setMessage("Hello World");
        message.setEmail("email@email.com");
        message.setName("name");
        message.setPhone(null);
        ContactMessage newMessage = contactDao.add(message);
        message.setContactMessageId(newMessage.getContactMessageId());

        // ACT
        ContactMessage getMessage = contactDao.getContactMessageById(newMessage.getContactMessageId());

        // ASSERT
        Assert.assertEquals(newMessage, getMessage);
    }

    @Test
    @Sql(scripts = "file:car_dealership_schema_creation_test.sql")
    public void update() {
        // ARRANGE
        ContactMessage message = new ContactMessage();
        message.setMessage("Hello World");
        message.setEmail("email@email.com");
        message.setName("name");
        message.setPhone(null);
        ContactMessage newMessage = contactDao.add(message);
        ContactMessage getMessage = contactDao.getContactMessageById(newMessage.getContactMessageId());
        Assert.assertEquals(newMessage, getMessage);

        newMessage.setMessage("Hey Guys");

        // ACT
        boolean isEditMessage = contactDao.update(newMessage);
        ContactMessage editedMessage = contactDao.getContactMessageById(newMessage.getContactMessageId());

        Assert.assertTrue(isEditMessage);
        Assert.assertEquals(newMessage, editedMessage);
    }

    @Test
    @Sql(scripts = "file:car_dealership_schema_creation_test.sql")
    public void deleteByContactMessageId() {
        // ARRANGE
        ContactMessage message = new ContactMessage();
        message.setMessage("Hello World");
        message.setEmail("email@email.com");
        message.setName("name");
        message.setPhone(null);
        ContactMessage newMessage = contactDao.add(message);
        ContactMessage getMessage = contactDao.getContactMessageById(newMessage.getContactMessageId());
        Assert.assertEquals(newMessage, getMessage);

        // ACT
        boolean isDeleted = contactDao.deleteByContactMessageId(newMessage.getContactMessageId());
        List<ContactMessage> listOfMessage = contactDao.getAll();

        // ASSERT
        Assert.assertTrue(isDeleted);
        Assert.assertFalse(listOfMessage.contains(newMessage));
    }
}