package edu.unl.cc.jbrew.Domain.Kardex;

import java.util.Date;
import edu.unl.cc.jbrew.Domain.Inventory.Product;
import edu.unl.cc.jbrew.Domain.Movements.MovementType;

public class Kardex {
    private int idKardex;
    private Product product;
    private Date date;
    private MovementType movementType;
    private int quantity;
    private int balance;
    private String description;

    public Kardex(int idKardex, Product product, Date date, MovementType movementType, int quantity, int balance, String description) {
        this.idKardex = idKardex;
        this.product = product;
        this.date = date;
        this.movementType = movementType;
        this.quantity = quantity;
        this.balance = balance;
        this.description = description;
    }

    public void registerEntry(int quantity) {
        this.quantity = quantity;
        this.movementType = MovementType.ENTRY;
        this.balance += quantity;
    }

    public void registerExit(int quantity) {
        this.quantity = quantity;
        this.movementType = MovementType.EXIT;
        this.balance -= quantity;
    }

    public void showKardexEntry() {
        System.out.println("===== KARDEX ENTRY =====");
        System.out.println("Kardex ID: " + idKardex);
        System.out.println("Product: " + product.getName());
        System.out.println("Date: " + date);
        System.out.println("Movement Type: " + movementType);
        System.out.println("Quantity: " + quantity);
        System.out.println("Balance: " + balance);
        System.out.println("Description: " + description);
    }

    // Getters and Setters
    public int getIdKardex() {
        return idKardex;
    }

    public void setIdKardex(int idKardex) {
        this.idKardex = idKardex;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public MovementType getMovementType() {
        return movementType;
    }

    public void setMovementType(MovementType movementType) {
        this.movementType = movementType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
