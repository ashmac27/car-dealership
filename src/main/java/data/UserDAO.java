package data;

import java.util.List;
import model.User;

public interface UserDAO {

    List<User> getAllUsers();
    User editUser(User user);
    User addUser(User user);
    User getUserById(int id);
    User getUserByFNLN(String firstName, String lastName);
}
