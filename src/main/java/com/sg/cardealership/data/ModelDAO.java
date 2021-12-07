package com.sg.cardealership.data;

import com.sg.cardealership.model.Model;
import java.util.List;

public interface ModelDAO {

    Model getModelById(int id);
    Model getModelByNameAndMake(String name, String make);
    List<Model> getAllModels();
    Model addModel(Model model);
    void deleteModelById(int id);

}
