package com.sg.cardealership.data;

import com.sg.cardealership.model.SearchCriteria;
import com.sg.cardealership.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the Vehicle DAO implementation
 */
@Repository
public class VehicleDAODatabase implements VehicleDAO {

    @Autowired
    private JdbcTemplate jdbcTemp;

    // Gets the list of all vehicles in the system
    @Override
    public List<Vehicle> getVehicleList() {
        final String SELECT_LIST_OF_VEHICLE = "SELECT * " +
                "FROM vehicle";
        List<Vehicle> vehicleList = jdbcTemp.query(SELECT_LIST_OF_VEHICLE, new VehicleMapper());
        return vehicleList;
    }

    // Gets the list of all featured vehicle in the system
    @Override
    public List<Vehicle> getFeaturedVehicleList() {
        final String SELECT_LIST_OF_VEHICLE = "SELECT * " +
                "FROM vehicle " +
                "WHERE IsFeatured = true";
        List<Vehicle> vehicleList = jdbcTemp.query(SELECT_LIST_OF_VEHICLE, new VehicleMapper());
        return vehicleList;
    }

    // Gets a list of vehicle given the search criteria
    @Override
    public List<Vehicle> getVehicleListBySearchCriteria(SearchCriteria criteria) {
        final String SELECT_LIST_OF_VEHICLE = "SELECT * " +
                "FROM vehicle v " +
                "INNER JOIN model mo ON v.ModelId = mo.ModelId " +
                "INNER JOIN make ma ON v.MakeId = ma.MakeId" +
                generateSearchCriteriaCondition(criteria);
        List<Vehicle> vehicleList = jdbcTemp.query(SELECT_LIST_OF_VEHICLE, new VehicleMapper());
        return vehicleList;
    }

    // Gets a vehicle by their Id (VIN)
    @Override
    public Vehicle getVehicleById(String VIN) {
        final String SELECT_VEHICLE = "SELECT * " +
                "FROM vehicle " +
                "WHERE VIN = ?";
        Vehicle vehicle = jdbcTemp.queryForObject(SELECT_VEHICLE, new VehicleMapper(), VIN);
        return vehicle;
    }

    // Adds that vehicle to the system and return that vehicle
    @Override
    @Transactional
    public Vehicle addVehicle(Vehicle vehicle) {
        final String ADD_VEHICLE = "INSERT INTO vehicle " +
                "(VIN, MakeId, ModelId, `Type`, BodyStyle, `Year`, Transmission, " +
                "Color, Interior, Mileage, SalePrice, MSRP, `Description`, " +
                "IsFeatured, IsSold, Picture) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemp.update(ADD_VEHICLE,
                vehicle.getVIN(),
                vehicle.getMakeId(),
                vehicle.getModelId(),
                vehicle.getType(),
                vehicle.getBodyStyle(),
                vehicle.getYear(),
                vehicle.getTransmission(),
                vehicle.getColor(),
                vehicle.getInterior(),
                vehicle.getMileage(),
                vehicle.getSalePrice(),
                vehicle.getMsrp(),
                vehicle.getDescription(),
                vehicle.isFeatured(),
                vehicle.isSold(),
                vehicle.getPicture());
        return vehicle;
    }

    // Deletes a vehicle from the system, and return true if delete is successful
    @Override
    @Transactional
    public boolean deleteVehicle(String VIN) {
        final String DELETE_VEHICLE = "DELETE FROM vehicle " +
                "WHERE VIN = ?";
        return jdbcTemp.update(DELETE_VEHICLE, VIN) > 0;
    }

    // Deletes vehicle from the system, and return true if the edit is successful
    @Override
    @Transactional
    public boolean editVehicle(String vin, Vehicle vehicle) {
        final String UPDATE_VEHICLE = "UPDATE vehicle SET " +
                "MakeId = ?, " +
                "ModelId = ?, " +
                "`Type` = ?, " +
                "BodyStyle = ?, " +
                "`Year` = ?, " +
                "Transmission = ?, " +
                "Color = ?, " +
                "Interior = ?, " +
                "Mileage = ?, " +
                "SalePrice = ?, " +
                "MSRP = ?, " +
                "`Description` = ?, " +
                "IsFeatured = ?, " +
                "IsSold = ?, " +
                "Picture = ? " +
                "WHERE VIN = ?";

        return jdbcTemp.update(UPDATE_VEHICLE,
                vehicle.getMakeId(),
                vehicle.getModelId(),
                vehicle.getType(),
                vehicle.getBodyStyle(),
                vehicle.getYear(),
                vehicle.getTransmission(),
                vehicle.getColor(),
                vehicle.getInterior(),
                vehicle.getMileage(),
                vehicle.getSalePrice(),
                vehicle.getMsrp(),
                vehicle.getDescription(),
                vehicle.isFeatured(),
                vehicle.isSold(),
                vehicle.getPicture(),
                vin) > 0;
    }

    // Generate the Search Criteria condition query, and return the string for that query
    private String generateSearchCriteriaCondition(SearchCriteria criteria) {

        if(criteria == null) {
            return "";
        }

        List<String> listOfConditions = new ArrayList<>();

        if (criteria.getType() != null) {
            listOfConditions.add("v.Type = \"" + criteria.getType() + "\"");
        }
        if (criteria.getSold() != null) {
            listOfConditions.add("v.IsSold = " + criteria.getSold().toString());
        }
        if (criteria.getModel() != null) {
            listOfConditions.add("mo.ModelName = \"" + criteria.getModel() + "\"");
        }
        if (criteria.getMake() != null) {
            listOfConditions.add("ma.MakeName = \"" + criteria.getMake() + "\"");
        }
        if (criteria.getYear() != null) {
            listOfConditions.add("v.`Year` = " + criteria.getYear().toString());
        }
        if (criteria.getMinYear() != null) {
            listOfConditions.add("v.`Year` >= " + criteria.getMinYear().toString());
        }
        if (criteria.getMaxYear() != null) {
            listOfConditions.add("v.`Year` <= " + criteria.getMaxYear().toString());
        }
        if (criteria.getMinPrice() != null) {
            listOfConditions.add("v.SalePrice >= " + criteria.getMinPrice().toString());
        }
        if (criteria.getMaxPrice() != null) {
            listOfConditions.add("v.SalePrice <= " + criteria.getMaxPrice().toString());
        }

        if(listOfConditions.isEmpty()) {
           return "";
        }

        String conditionQuery = " WHERE " + listOfConditions.get(0);
        for(int i = 1; i < listOfConditions.size(); i++){
            conditionQuery += " " + listOfConditions.get(i);
        }
        return conditionQuery;
    }

    // Represents the mapper for vehicles entities
    public final static class VehicleMapper implements RowMapper<Vehicle> {

        @Override
        public Vehicle mapRow(ResultSet resultSet, int i) throws SQLException {
            Vehicle vehicle = new Vehicle();
            vehicle.setVIN(resultSet.getString("VIN"));
            vehicle.setMakeId(resultSet.getInt("MakeId"));
            vehicle.setModelId(resultSet.getInt("ModelId"));
            vehicle.setType(resultSet.getString("Type"));
            vehicle.setBodyStyle(resultSet.getString("BodyStyle"));
            vehicle.setYear(resultSet.getInt("Year"));
            vehicle.setTransmission(resultSet.getString("Transmission"));
            vehicle.setColor(resultSet.getString("Color"));
            vehicle.setInterior(resultSet.getString("Interior"));
            vehicle.setMileage(resultSet.getInt("Mileage"));
            vehicle.setSalePrice(resultSet.getBigDecimal("SalePrice"));
            vehicle.setMsrp(resultSet.getBigDecimal("MSRP"));
            vehicle.setDescription(resultSet.getString("Description"));
            vehicle.setFeatured(resultSet.getBoolean("IsFeatured"));
            vehicle.setSold(resultSet.getBoolean("IsSold"));
            vehicle.setPicture(resultSet.getString("Picture"));
            return vehicle;
        }
    }
}
