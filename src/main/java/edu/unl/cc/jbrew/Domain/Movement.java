package edu.unl.cc.jbrew.Domain;

import java.util.Date;

public class Movement {

    // Attributes
    private int movementId;
    private String movementType;
    private Date date;
    private String description;

    // Empty constructor
    public Movement() {
    }

    // Constructor with parameters
    public Movement(int movementId, String movementType, Date date, String description) {
        this.movementId = movementId;
        this.movementType = movementType;
        this.date = date;
        this.description = description;
    }

    // Getters and Setters
    public int getMovementId() {
        return movementId;
    }

    public void setMovementId(int movementId) {
        this.movementId = movementId;
    }

    public String getMovementType() {
        return movementType;
    }

    public void setMovementType(String movementType) {
        this.movementType = movementType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // UML methods
    public void registerMovement() {
        System.out.println("Movement registered successfully.");
    }

    public void showMovement() {
        System.out.println("===== MOVEMENT DATA =====");
        System.out.println("Movement ID: " + movementId);
        System.out.println("Movement Type: " + movementType);
        System.out.println("Date: " + date);
        System.out.println("Description: " + description);
    }
}
