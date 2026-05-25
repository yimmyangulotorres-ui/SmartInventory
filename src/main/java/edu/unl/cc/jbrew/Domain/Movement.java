package edu.unl.cc.jbrew.Domain;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Movement {
    private int idMovement;
    private MovementType movementType;
    private Date date;
    private String description;
    private List<MovementDetail> detailList;
    private double total;

    public Movement() {
        detailList = new ArrayList<>();
    }

    public void addItem(Product product, int quantity, double unitPrice) {
        MovementDetail detail = new MovementDetail(detailList.size() + 1, product, quantity, unitPrice);
        detailList.add(detail);
    }

    public void showMovement() {
        System.out.println("===== MOVEMENT DATA =====");
        System.out.println("Movement ID: " + idMovement);
        System.out.println("Movement Type: " + movementType);
        System.out.println("Date: " + date);
        System.out.println("Description: " + description);
        for (MovementDetail detail : detailList) {
            System.out.println("Product: " + detail.getProduct().getName() + " | Quantity: " + detail.getQuantity() +
                    " | Unit Price: " + detail.getUnitPrice() + " | Subtotal: " + detail.getSubtotal());
        }
    }

    public void processMovement() {
        for (MovementDetail detail : detailList) {
            detail.getProduct().modifyStock(-detail.getQuantity());
        }
        calculateTotal();
    }

    public double calculateTotal() {
        total = 0;
        for (MovementDetail detail : detailList) {
            total += detail.getSubtotal();
        }
        return total;
    }

    // Getters and Setters
    public int getIdMovement() {
        return idMovement;
    }

    public void setIdMovement(int idMovement) {
        this.idMovement = idMovement;
    }

    public MovementType getMovementType() {
        return movementType;
    }

    public void setMovementType(MovementType movementType) {
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

    public List<MovementDetail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<MovementDetail> detailList) {
        this.detailList = detailList;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}