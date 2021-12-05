package data;

import model.Special;

import java.util.List;

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
