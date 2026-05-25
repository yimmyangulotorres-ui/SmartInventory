package edu.unl.cc.jbrew.Domain;

public class MovementDetail {

    private int idDetail;
    private Product product;
    private int quantity;
    private double unitPrice;
    private double subtotal;

    public MovementDetail(int idDetail, Product product, int quantity, double unitPrice) {
        this.idDetail = idDetail;
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subtotal = calculateSubtotal();
    }

    public double calculateSubtotal() {
        return quantity * unitPrice;
    }

    // Getters y Setters
    public int getIdDetail() {
        return idDetail;
    }

    public void setIdDetail(int idDetail) {
        this.idDetail = idDetail;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}