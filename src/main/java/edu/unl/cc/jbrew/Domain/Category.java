package edu.unl.cc.jbrew.Domain;

public class Category {

    private int idCategory;
    private String description;
    private String name;

    public Category(int idCategory, String description, String name) {
        this.idCategory = idCategory;
        this.description = description;
        this.name = name;
    }

    // Getters y Setters
    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}