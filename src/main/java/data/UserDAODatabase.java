package data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import model.User;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;


public class UserDAODatabase implements UserDAO{
    /**
     *    List<User> getAllUsers();
     *     User editUser(User user);
     *     User addUser(User user);
     *     User getUserById();
     *     User getUserByFNLN(String firstName, String lastName);
     */

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<User> getAllUsers() {
        final String SELECT_ALL_USER = "SELECT * FROM user;";
        List<User> user = jdbc.query(SELECT_ALL_USER, new UserMapper());
        return user;
    }

    @Override
    @Transactional
    public User editUser(User user) {
        final String EDIT_USER = "UPDATE user SET FirstName = ?, LastName = ?, Email = ?, Role = ? WHERE UserId = ?;";

        jdbc.update(EDIT_USER,
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole(),
                user.getUserId());

        return user;
    }

    @Override
    @Transactional
    public User addUser(User user) {

        final String INSERT_USER = "INSERT INTO user(FirstName, LastName, Email, Role) "
                + "VALUES(?,?,?,?)";
        jdbc.update(INSERT_USER,
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        user.setUserId(newId);
        return user;
    }

    public User getUserById(int userId){

        try {
            final String SELECT_USER_BY_ID = "SELECT * FROM user WHERE user.UserId = ?;";
            User user = jdbc.queryForObject(SELECT_USER_BY_ID, new UserMapper(), userId);

            return user;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    public User getUserByFNLN( String fname, String lname){

        final String SELECT_USER_BY_FN_LN = "SELECT * FROM user WHERE FirstName = ? AND LastName = ?;";
        User user = jdbc.queryForObject(SELECT_USER_BY_FN_LN, new UserMapper(), fname, lname );

        return user;
    }

    public static final class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int index) throws SQLException {
            User user = new User(
                    rs.getInt("UserId"),
                    rs.getString("FirstName"),
                    rs.getString("LastName"),
                    rs.getString("Email"),
                    rs.getString("Role"));

            return user;
        }

    }


}
