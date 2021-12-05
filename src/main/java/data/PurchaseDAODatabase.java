package data;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.List;
import model.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
@Profile("database")
public class PurchaseDAODatabase implements PurchaseDAO {
    
    @Autowired
    private final JdbcTemplate template;

    public PurchaseDAODatabase(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public Purchase add(Purchase purchase) {
        final String sql = "INTSERT INTO purchase(SalespersonId, PurchasePrice, VehicleId, PurchaseType, Phone, Email, Street1, Street2, City, State, Zip, DateOfPurchase)"
                + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        
        template.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            // Default value generated is PurchaseId
            statement.setInt(1, purchase.getSalespersonId());
            statement.setBigDecimal(2, purchase.getPurchasePrice());
            statement.setString(3, purchase.getVehicleId());
            statement.setString(4, purchase.getPurchaseType());
            statement.setString(7, purchase.getStreet1());
            statement.setString(9, purchase.getCity());
            statement.setString(10, purchase.getState());
            statement.setTimestamp(11, Timestamp.valueOf(LocalDateTime.now()));
            
            // Sets phone value, if null or not
            if(purchase.getPhone()!=null) {
                statement.setString(5, purchase.getPhone());
            } else {
                statement.setNull(5, Types.NULL);
            }
            
            // Sets email value, if null or not
            if(purchase.getEmail()!=null) {
                statement.setString(6, purchase.getEmail());
            } else {
                statement.setNull(6, Types.NULL);
            }
            
            // Sets street2 value, if null or not
            if(purchase.getStreet2()!=null) {
                statement.setString(8, purchase.getStreet2());
            } else {
                statement.setNull(8, Types.NULL);
            }
            
            return statement;
        },keyHolder);
        
        // Select item again, contains all generated values
        return getPurchaseByPurchaseId(keyHolder.getKey().intValue());
    }

    @Override
    public Purchase getPurchaseByPurchaseId(int purchaseId) {
        final String sql = "SELECT * FROM purchase WHERE PurchaseId = ?";
        return template.queryForObject(sql, new PurchaseMapper(), purchaseId);
    }

    @Override
    public List<Purchase> getAllPurchases() {
        final String sql = "SELECT * FROM purchase";
        return template.query(sql, new PurchaseMapper());
    }

    @Override
    public Purchase getPurchaseByVehicleId(String vehicleId) {
        final String sql = "SELECT * FROM purchase WHERE VehicleId = ?";
        return template.queryForObject(sql, new PurchaseMapper(), vehicleId);
    }

    @Override
    public List<Purchase> getPurchasesBySalespersonId(int salespersonId) {
        final String sql = "SELECT * FROM purchase WHERE SalespersonId = ?";
        return template.query(sql, new PurchaseMapper(), salespersonId);
    }

    @Override
    public boolean update(Purchase purchase) {
        // TODO: FINISH THIS
        final String sql = "UPDATE purchase SET "+
                "SalespersonId = ?, " +
                "SalespersonId = ?, " +
                "SalespersonId = ?, " +
                "SalespersonId = ?, " +
                "SalespersonId = ?, " +
                "SalespersonId = ?, " +
                "SalespersonId = ?, " +
                "SalespersonId = ?, " + 
            "WHERE purchaseId=?";
        return template.update(sql) > 0;
    }

    @Override
    public boolean deleteByPurchaseId(int purchaseId) {
        final String sql = "DELETE FROM purchase WHERE PurchaseId=?;";
        return template.update(sql, purchaseId) > 0;
    }
    
    private final class PurchaseMapper implements RowMapper<Purchase> {

        @Override
        public Purchase mapRow(ResultSet rs, int i) throws SQLException {
            Purchase result = new Purchase(
                    rs.getInt("PurchaseId"),
                    rs.getInt("SalespersonId"),
                    rs.getBigDecimal("PurchasePrice").setScale(2),
                    rs.getString("VehicleId"),
                    rs.getString("PurchaseType"),
                    null, // Phone can be null
                    null, // Email can be null
                    rs.getString("Street1"),
                    null, // Street2 can be null
                    rs.getString("City"),
                    rs.getString("State"),
                    rs.getString("Zip"),
                    rs.getTimestamp("DateOfPurchase").toLocalDateTime()
            );
            try {
                result.setEmail(rs.getString("Email"));
            } catch(Exception e) { } // Email is null
            try {
                result.setPhone(rs.getString("Phone"));
            } catch(Exception e) { } // Phone is null
            try {
                result.setStreet2(rs.getString("Street2"));
            } catch(Exception e) { } // Street2 is null
            return result;
        }
            
    }
}
