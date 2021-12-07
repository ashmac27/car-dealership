package data;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import model.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameterValue;
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
        final String sql = "INTSERT INTO purchase(SalespersonId, PurchasePrice, VIN, PurchaseType, Name, Phone, Email, Street1, Street2, City, State, Zip)"
                + " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        
        template.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            // Default value generated is PurchaseId
            statement.setInt(1, purchase.getSalespersonId());
            statement.setBigDecimal(2, purchase.getPurchasePrice());
            statement.setString(3, purchase.getVIN());
            statement.setString(4, purchase.getPurchaseType());
            statement.setString(5, purchase.getName());
            statement.setString(8, purchase.getStreet1());
            statement.setString(10, purchase.getCity());
            statement.setString(11, purchase.getState());
            
            // Sets phone value, if null or not
            if(purchase.getPhone()!=null) {
                statement.setString(6, purchase.getPhone());
            } else {
                statement.setNull(6, Types.NULL);
            }
            
            // Sets email value, if null or not
            if(purchase.getEmail()!=null) {
                statement.setString(7, purchase.getEmail());
            } else {
                statement.setNull(7, Types.NULL);
            }
            
            // Sets street2 value, if null or not
            if(purchase.getStreet2()!=null) {
                statement.setString(9, purchase.getStreet2());
            } else {
                statement.setNull(9, Types.NULL);
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
        final String sql = "SELECT * FROM purchase WHERE VIN = ?";
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
                "VIN = ?, " +
                "PurchaseType = ?, " +
                "PurchasePrice = ?, " +
                "Name = ?, " +
                "Phone = ?, " +
                "Email = ?, " +
                "Street1 = ?, " +
                "Street2 = ?, " + 
                "City = ?, " + 
                "State = ?, " + 
                "Zip = ? " + 
            "WHERE purchaseId=?";
        return template.update(sql,
                purchase.getSalespersonId(),
                purchase.getVIN(),
                purchase.getPurchaseType(),
                purchase.getPurchasePrice(),
                purchase.getName(),
                (purchase.getPhone()==null) ? new SqlParameterValue(Types.NULL,"Phone") : purchase.getPhone(),
                (purchase.getEmail()==null) ? new SqlParameterValue(Types.NULL,"Email") : purchase.getEmail(),
                purchase.getStreet1(),
                (purchase.getStreet2()==null) ? new SqlParameterValue(Types.NULL,"Street2") : purchase.getStreet2(),
                purchase.getCity(),
                purchase.getState(),
                purchase.getZip(),
                purchase.getPurchaseId()
        ) > 0;
    }

    @Override
    public boolean deleteByPurchaseId(int purchaseId) {
        final String sql = "DELETE FROM purchase WHERE PurchaseId=?;";
        return template.update(sql, purchaseId) > 0;
    }

    @Override
    public List<Map<String, Object>> getSalesReport(Integer salespersonId, LocalDate toDate, LocalDate fromDate) {
        String sql = "SELECT CONCAT(user.FirstName, ' ', user.LastName) AS 'User', SUM(purchase.PurchasePrice) AS 'Total Sales', COUNT(*) AS 'Total Vehicles'" + 
                " FROM purchase INNER JOIN user ON user.UserId = purchase.SalespersonId WHERE Role='sales' purchase.DateOfPurchase >= ? AND purchase.DateOfPurchase <= ?";
        if(salespersonId==null) {
            sql += " AND SalespersonId <> ?";
            salespersonId = 0; // <> 0 should be every user
        } else {
            sql += " AND SalespersonId = ?";
        }
        sql += "GROUP BY purchase.SalespersonId ORDER BY User ASC";
        if(toDate==null) toDate = LocalDate.now();
        if(fromDate==null) fromDate = LocalDate.MIN;
        return template.query(sql, new ColumnMapRowMapper(),
                Timestamp.valueOf(LocalDateTime.of(fromDate, LocalTime.MIN)), // earliest possible date if not specified
                Timestamp.valueOf(LocalDateTime.of(toDate, LocalTime.MAX)), // Maximum time of today if not specified
                salespersonId
        ); // Custom mapper for report, a model that is only selected once
    }

    @Override
    public List<Map<String, Object>> getInventoryReport(boolean used) {
        final String sql = "SELECT vehicle.Year, make.MakeName AS Make, model.ModelName AS Model, COUNT(*) AS Count, SUM(vehicle.MSRP) AS 'Stock Value' "+
                "FROM vehicle INNER JOIN make ON make.MakeId = vehicle.MakeId INNER JOIN model ON model.ModelId = vehicle.ModelId WHERE "+
                "vehicle.IsSold=0 AND vehicle.Type = "+(used ? "'used'" : "'new'")+" GROUP BY vehicle.MakeId, vehicle.ModelId, vehicle.Year";
        return template.query(sql, new ColumnMapRowMapper());
    }
    
    private final class PurchaseMapper implements RowMapper<Purchase> {

        @Override
        public Purchase mapRow(ResultSet rs, int i) throws SQLException {
            Purchase result = new Purchase(
                    rs.getInt("PurchaseId"),
                    rs.getInt("SalespersonId"),
                    rs.getBigDecimal("PurchasePrice").setScale(2),
                    rs.getString("VIN"),
                    rs.getString("PurchaseType"),
                    rs.getString("Name"),
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
