package com.sg.cardealership.data;

import com.sg.cardealership.model.Make;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class MakeDAODatabase implements MakeDAO {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public List<Make> getAllMakes() {
        final String SELECT_ALL_MAKES = "SELECT * FROM make";
        List<Make> makes = jdbc.query(SELECT_ALL_MAKES, new MakeMapper());
        return makes;
    }

    @Override
    @Transactional
    public Make addMake(Make make) {

        final String INSERT_MAKE = "INSERT INTO make(MakeName, DateAdded, UserId) "
                + "VALUES(?, ? ,?)";
        jdbc.update(INSERT_MAKE,
                make.getMakeName(),
                make.getDateAdded());
        make.getUserId();

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        make.setMakeId(newId);

        return make;
    }

    @Override
    public String getMakeNameById(int id) {
        Make make = getMakeById(id);
        return make.getMakeName();
    }


    @Override
    public Make getMakeByName(String name) {

        try {

            final String SELECT_MAKE_BY_NAME = "SELECT * FROM make WHERE MakeName = ?";
            Make make = jdbc.queryForObject(SELECT_MAKE_BY_NAME, new MakeMapper(), name);
            return make;

        } catch (DataAccessException ex) {
            return null;
        }

    }

    @Override
    public Make getMakeById(int id) {

        try {

            final String SELECT_MAKE = "SELECT * FROM make WHERE id = ?";
            Make make = jdbc.queryForObject(SELECT_MAKE, new MakeMapper(), id);
            return make;

        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public void deleteMakeById(int id) {
        final String DELETE_MAKE = "DELETE FROM make WHERE id = ?";
        jdbc.update(DELETE_MAKE, id);
    }


    public static final class MakeMapper implements RowMapper<Make> {

        @Override
        public Make mapRow(ResultSet rs, int index) throws SQLException {
            Make make = new Make(
                    rs.getInt("MakeId"),
                    rs.getInt("UserId"),
                    rs.getString("MakeName"),
                    rs.getDate("DateAdded").toLocalDate());

            return make;
        }

    }
}
