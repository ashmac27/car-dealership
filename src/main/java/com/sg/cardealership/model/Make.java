package com.sg.cardealership.model;

import java.time.LocalDate;
import java.util.Objects;

public class Make {
    int makeId, userId;
    String makeName;
    LocalDate dateAdded;

    public Make() {

    }

    public Make(int makeId, int userId, String makeName, LocalDate dateAdded) {
        this.makeId = makeId;
        this.userId = userId;
        this.makeName = makeName;
        this.dateAdded = dateAdded;
    }
    
    /**
     * Creates a new make (for inserting into DAO)
     * @param userId ID of user who made this
     * @param makeName Name of the model
     */

    public Make(int userId, String makeName) {
        this.userId = userId;
        this.makeName = makeName;
        this.dateAdded = LocalDate.now();
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

    public String getMakeName() {
        return makeName;
    }

    public void setMakeName(String makeName) {
        this.makeName = makeName;
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
        Make make = (Make) o;
        return makeId == make.makeId && userId == make.userId && Objects.equals(makeName, make.makeName) && Objects.equals(dateAdded, make.dateAdded);
    }

    @Override
    public int hashCode() {
        return Objects.hash(makeId, userId, makeName, dateAdded);
    }
}
