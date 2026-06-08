package edu.unl.cc.jbrew.Domain.Movements;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import edu.unl.cc.jbrew.Domain.Inventory.Product;
import edu.unl.cc.jbrew.Domain.Invoice.Invoice;
import edu.unl.cc.jbrew.Domain.Kardex.Kardex;

public class Movement {
    private int idMovement;
    private MovementType movementType;
    private MovementStatus status;
    private Date date;
    private String description;
    private List<ProductMovement> productMovementList;
    private double total;
    private Invoice invoice;

    public Movement() {
        productMovementList = new ArrayList<>();
        this.status = MovementStatus.PENDING;
    }

    public Movement(int idMovement, MovementType movementType, Date date, String description) {
        this();
        this.idMovement = idMovement;
        this.movementType = movementType;
        this.date = date;
        this.description = description;
    }

    public void addProductMovement(Product product, int quantity, double unitPrice) {
        ProductMovement productMovement = new ProductMovement(productMovementList.size() + 1, product, quantity, unitPrice);
        productMovementList.add(productMovement);
    }

    public void showMovement() {
        System.out.println("===== MOVEMENT DATA =====");
        System.out.println("Movement ID: " + idMovement);
        System.out.println("Movement Type: " + movementType);
        System.out.println("Status: " + status);
        System.out.println("Date: " + date);
        System.out.println("Description: " + description);
        for (ProductMovement productMovement : productMovementList) {
            System.out.println("Product: " + productMovement.getProduct().getName() + " | Quantity: " + productMovement.getQuantity() +
                    " | Unit Price: " + productMovement.getUnitPrice() + " | Subtotal: " + productMovement.getSubtotal());
        }
    }

    public void processMovement() {
        for (ProductMovement productMovement : productMovementList) {
            if (movementType == MovementType.ENTRY) {
                productMovement.getProduct().modifyStock(productMovement.getQuantity());
            } else {
                productMovement.getProduct().modifyStock(-productMovement.getQuantity());
            }
        }
        this.status = MovementStatus.CONFIRMED;
        calculateTotal();
    }

    public void processMovementWithKardex(Kardex kardex) {
        for (ProductMovement productMovement : productMovementList) {
            if (movementType == MovementType.ENTRY) {
                productMovement.getProduct().modifyStock(productMovement.getQuantity());
            } else {
                productMovement.getProduct().modifyStock(-productMovement.getQuantity());
            }
            productMovement.updateKardex(kardex, movementType);
        }
        this.status = MovementStatus.CONFIRMED;
        calculateTotal();
    }

    public void generateInvoice(Invoice invoice) {
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
