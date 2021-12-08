package com.sg.cardealership.data;

import java.util.List;
import com.sg.cardealership.model.User;

public interface UserDAO {

    List<User> getAllUsers();
    Boolean editUser(User user);
    User addUser(User user);
    User getUserById(int id);
    User getUserByFNLN(String firstName, String lastName);
}
