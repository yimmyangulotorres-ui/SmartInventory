package edu.unl.cc.jbrew.Domain.Movements;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import edu.unl.cc.jbrew.Domain.Inventory.Product;
import edu.unl.cc.jbrew.Domain.Invoice.Invoice;
import edu.unl.cc.jbrew.Domain.Kardex.Kardex;

public class Movement {
    private int idMovement;
    private MovementType movementType; // Asociación con MovementType (enum)
    private MovementStatus status; // Asociación con MovementStatus (enum)
    private Date date;
    private String description;
    private List<ProductMovement> productMovementList; // Composición con ProductMovement
    private double total;
    private Invoice invoice; // Asociación con Invoice

    public Movement() {
        productMovementList = new ArrayList<>();
        this.status = MovementStatus.PENDING;
    }

    public Movement(int idMovement, MovementType movementType, Date date, String description) { // Asociación con MovementType
        this();
        this.idMovement = idMovement;
        this.movementType = movementType;
        this.date = date;
        this.description = description;
    }

    public void addProductMovement(Product product, int quantity, double unitPrice) { // Asociación con Product
        ProductMovement productMovement = new ProductMovement(productMovementList.size() + 1, product, quantity, unitPrice); // Composición con ProductMovement
        productMovementList.add(productMovement);
    }


    private void processStockChanges() {
        for (ProductMovement productMovement : productMovementList) {
            if (movementType == MovementType.ENTRY) {
                productMovement.getProduct().modifyStock(productMovement.getQuantity());
            } else {
                productMovement.getProduct().modifyStock(-productMovement.getQuantity());
            }
        }
    }

    public void processMovement() {
        processStockChanges();
        this.status = MovementStatus.CONFIRMED;
        calculateTotal();
    }

    public void processMovementWithKardex(Kardex kardex) { // Asociación con Kardex
        processStockChanges();
        for (ProductMovement productMovement : productMovementList) {
            productMovement.updateKardex(kardex, movementType);
        }
        this.status = MovementStatus.CONFIRMED;
        calculateTotal();
    }

    public void generateInvoice(Invoice invoice) { // Asociación con Invoice
        this.invoice = invoice;
        invoice.generateInvoice();
    }

    public void cancelMovement() {
        this.status = MovementStatus.CANCELLED;
    }

    public double calculateTotal() {
        total = 0;
        for (ProductMovement productMovement : productMovementList) {
            total += productMovement.getSubtotal();
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

    public MovementStatus getStatus() {
        return status;
    }

    public void setStatus(MovementStatus status) {
        this.status = status;
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

    public List<ProductMovement> getProductMovementList() {
        return productMovementList;
    }

    public void setProductMovementList(List<ProductMovement> productMovementList) {
        this.productMovementList = productMovementList;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}
