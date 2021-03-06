package com.sg.cardealership.data;

import com.sg.cardealership.model.Make;
import java.util.List;

public interface MakeDAO {

    List<Make> getAllMakes();
    Make addMake(Make make);
    String getMakeNameById(int id);
    Make getMakeById(int id);
    Make getMakeByName(String name);
    void deleteMakeById(int id);
}
