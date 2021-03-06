package com.sg.cardealership.model;

import java.time.LocalDate;
import java.util.Objects;

public class Model {
    int modelId, makeId, userId;
    String modelName;
    LocalDate dateAdded;

    public Model() {

    }

    public Model(int modelId, int makeId, int userId, String modelName, LocalDate dateAdded) {
        this.modelId = modelId;
        this.makeId = makeId;
        this.userId = userId;
        this.modelName = modelName;
        this.dateAdded = dateAdded;
    }
    
    /**
     * Creates a new model (for inserting into DAO)
     * @param makeId ID of the make
     * @param userId ID of user who made this
     * @param modelName Name of the model
     */
    public Model(int makeId, int userId, String modelName) {
        this.makeId = makeId;
        this.userId = userId;
        this.modelName = modelName;
        this.dateAdded = LocalDate.now();
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public int getMakeId() {
        return makeId;
    }

    public void setMakeId(int makeId) {
        this.makeId = makeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Model model = (Model) o;
        return modelId == model.modelId &&
                makeId == model.makeId &&
                userId == model.userId &&
                Objects.equals(modelName, model.modelName) &&
                Objects.equals(dateAdded, model.dateAdded);
    }

    @Override
    public int hashCode() {

        return Objects.hash(modelId, makeId, userId, modelName, dateAdded);
    }

}