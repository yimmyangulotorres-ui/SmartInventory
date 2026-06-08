package edu.unl.cc.jbrew.Domain.Reports;

import java.util.Date;
import edu.unl.cc.jbrew.Domain.Inventory.Product;

public class StockAlert {

    private int idAlert;
    private Product product; // Composición con Product
    private int currentStock;
    private int minStock;
    private Date alertDate;
    private boolean isResolved;

    public StockAlert(int idAlert, Product product, int currentStock, int minStock, Date alertDate) { // Asociación con Product
        this.idAlert = idAlert;
        this.product = product;
        this.currentStock = currentStock;
        this.minStock = minStock;
        this.alertDate = alertDate;
        this.isResolved = false;
    }

    public void checkStockLevel() {
        if (currentStock <= minStock) {
            System.out.println("ALERTA: Stock bajo para producto " + product.getName() + 
                             " - Stock actual: " + currentStock + 
                             " - Stock mínimo: " + minStock);
        }
    }

    public void resolveAlert() {
        this.isResolved = true;
        System.out.println("Alerta resuelta para producto: " + product.getName());
    }

    public void showAlert() {
        System.out.println("===== STOCK ALERT =====");
        System.out.println("Alert ID: " + idAlert);
        System.out.println("Product: " + product.getName());
        System.out.println("Current Stock: " + currentStock);
        System.out.println("Minimum Stock: " + minStock);
        System.out.println("Alert Date: " + alertDate);
        System.out.println("Status: " + (isResolved ? "RESOLVED" : "PENDING"));
    }

    // Getters and Setters
    public int getIdAlert() {
        return idAlert;
    }

    public void setIdAlert(int idAlert) {
        this.idAlert = idAlert;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(int currentStock) {
        this.currentStock = currentStock;
    }

    public int getMinStock() {
        return minStock;
    }

    public void setMinStock(int minStock) {
        this.minStock = minStock;
    }

    public Date getAlertDate() {
        return alertDate;
    }

    public void setAlertDate(Date alertDate) {
        this.alertDate = alertDate;
    }

    public boolean isResolved() {
        return isResolved;
    }

    public void setResolved(boolean resolved) {
        isResolved = resolved;
    }
}
