package com.sg.cardealership.model;

import java.util.Objects;

public class Special {

    private int specialId;
    private String title;
    private String description;

    public int getSpecialId() {
        return specialId;
    }

    public void setSpecialId(int specialId) {
        this.specialId = specialId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Special special = (Special) o;
        return specialId == special.specialId && Objects.equals(title, special.title) && Objects.equals(description, special.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(specialId, title, description);
    }
}
