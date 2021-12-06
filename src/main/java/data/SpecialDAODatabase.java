package data;

import model.Special;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SpecialDAODatabase implements SpecialDAO {

    @Autowired
    private JdbcTemplate jdbcTemp;

    @Override
    public Special getSpecialById(int specialId) {
        final String SELECT_SPECIAL = "SELECT * " +
                "FROM specials " +
                "WHERE SpecialId = ?";
        Special special = jdbcTemp.queryForObject(SELECT_SPECIAL, new SpecialMapper(), specialId);
        return special;
    }

    @Override
    public List<Special> getSpecialList() {
        final String SELECT_LIST_OF_SPECIAL = "SELECT * " +
                "FROM specials";
        List<Special> specialList = jdbcTemp.query(SELECT_LIST_OF_SPECIAL, new SpecialMapper());
        return specialList;
    }

    @Override
    @Transactional
    public Special addSpecial(Special special) {
        final String ADD_SPECIAL = "INSERT INTO specials " +
                "(SpecialId, Title, Description) " +
                "VALUES (?, ?, ?)";
        jdbcTemp.update(ADD_SPECIAL, special.getSpecialId(), special.getTitle(), special.getDescription());
        int newId = jdbcTemp.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        special.setSpecialId(newId);
        return special;
    }

    @Override
    @Transactional
    public boolean deleteSpecial(int specialId) {
        final String DELETE_SPECIAL = "DELETE FROM specials " +
                "WHERE SpecialId = ?";
        return jdbcTemp.update(DELETE_SPECIAL, specialId) > 0;
    }

    public static final class SpecialMapper implements RowMapper<Special> {

        @Override
        public Special mapRow(ResultSet resultSet, int i) throws SQLException {
            Special special = new Special();
            special.setSpecialId(resultSet.getInt("SpecialId"));
            special.setTitle(resultSet.getString("Title"));
            special.setDescription(resultSet.getString("Description"));
            return special;
        }
    }
}
