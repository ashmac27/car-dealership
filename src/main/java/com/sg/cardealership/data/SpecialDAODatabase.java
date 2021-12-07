package com.sg.cardealership.data;

import com.sg.cardealership.model.Special;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Represents the Special DAO implementation
 */
@Repository
public class SpecialDAODatabase implements SpecialDAO {

    @Autowired
    private JdbcTemplate jdbcTemp;

    // Gets a special by their id
    @Override
    public Special getSpecialById(int specialId) {
        final String SELECT_SPECIAL = "SELECT * " +
                "FROM specials " +
                "WHERE SpecialId = ?";
        Special special = jdbcTemp.queryForObject(SELECT_SPECIAL, new SpecialMapper(), specialId);
        return special;
    }

    // Gets the list of all specials
    @Override
    public List<Special> getSpecialList() {
        final String SELECT_LIST_OF_SPECIAL = "SELECT * " +
                "FROM specials";
        List<Special> specialList = jdbcTemp.query(SELECT_LIST_OF_SPECIAL, new SpecialMapper());
        return specialList;
    }

    // Adds a special to the database and return that special
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

    // Deletes a special, and return true if delete is successful
    @Override
    @Transactional
    public boolean deleteSpecial(int specialId) {
        final String DELETE_SPECIAL = "DELETE FROM specials " +
                "WHERE SpecialId = ?";
        return jdbcTemp.update(DELETE_SPECIAL, specialId) > 0;
    }

    // Represents the mapper for special entities
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
