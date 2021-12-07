package com.sg.cardealership.data;

import com.sg.cardealership.model.Special;

import java.util.List;

/**
 * Represents the Special DAO functionality of the system
 */
public interface SpecialDAO {

    // Gets special by id
    Special getSpecialById(int specialId);

    // Gets list of all special
    List<Special> getSpecialList();

    // Adds special to database
    Special addSpecial(Special special);

    // Deletes special from database
    boolean deleteSpecial(int specialId);
}
