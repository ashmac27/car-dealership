package data;

import model.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ModelDAODatabase implements ModelDAO{

    @Autowired
    JdbcTemplate jdbc;

    @Override
    @Transactional
    public Model addModel(Model model) {
        final String INSERT_MODEL = "INSERT INTO model(MakeId, ModelName, DateAdded, UserId) "
                + "VALUES(?, ?, ?, ?)";
        jdbc.update(INSERT_MODEL,
                model.getMakeId(),
                model.getModelName(),
                model.getDateAdded(),
                model.getUserId());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        model.setModelId(newId);

        return model;
    }

    @Override
    public Model getModelById(int id) {
        try {

            final String SELECT_MODEL = "SELECT * FROM model WHERE id = ?";
            Model model = jdbc.queryForObject(SELECT_MODEL, new ModelMapper(), id);
            return model;

        } catch(DataAccessException ex) {
            return null;
        }

    }

    @Override
    public Model getModelByNameAndMake(String modelName, String makeName) {
        try {

            final String SELECT_MODEL = "SELECT * FROM model JOIN make ON model.MakeId = make.MakeId "
                    + "WHERE model.ModelName = ? AND make.MakeName = ?";
            Model model = jdbc.queryForObject(SELECT_MODEL, new ModelMapper(), modelName, makeName);
            return model;

        } catch(DataAccessException ex) {
            return null;
        }

    }


    @Override
    public List<Model> getAllModels() {
        final String SELECT_ALL_MODEL = "SELECT * FROM model";
        List<Model> models = jdbc.query(SELECT_ALL_MODEL, new ModelMapper());
        return models;
    }

    @Override
    @Transactional
    public void deleteModelById(int id) {
        final String DELETE_MODEL = "DELETE FROM model WHERE id = ?";
        jdbc.update(DELETE_MODEL, id);
    }

    public static final class ModelMapper implements RowMapper<Model> {

        @Override
        public Model mapRow(ResultSet rs, int index) throws SQLException {

            Model model = new Model(
                    rs.getInt("ModelId"),
                    rs.getInt("MakeId"),
                    rs.getInt("UserId"),
                    rs.getString("ModelName"),
                    rs.getDate("DateAdded").toLocalDate()
            );

            return model;
        }


    }
}
