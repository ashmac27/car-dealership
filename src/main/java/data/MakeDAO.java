package data;

import model.Make;
import java.util.List;

public interface MakeDAO {

    List<Make> getAllMakes();
    Make addMake(Make make);
    String getMakeNameById(int makeId);
    Make getMakeById(int id);
    Make getMakeByName(String name);
    void deleteMakeById(int makeId);
}
