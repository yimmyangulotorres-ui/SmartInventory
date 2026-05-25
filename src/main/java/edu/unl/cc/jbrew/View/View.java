package edu.unl.cc.jbrew.View;

import edu.unl.cc.jbrew.Domain.*;

public class View {

    public void displayInventoryProductList(Inventory inventory) {
        System.out.println("===== PRODUCT LIST FROM INVENTORY =====");
        for (Product product : inventory.showProduct()) {
            System.out.println("Product ID: " + product.getIdProduct());
            System.out.println("Name: " + product.getName());
            System.out.println("Description: " + product.getDescription());
            System.out.println("Sale Price: " + product.getSalePrice());
            System.out.println("Purchase Price: " + product.getPurchasePrice());
            System.out.println("Stock: " + product.getStock());
            System.out.println("----------------------------------------");
        }
    }

    public void displayMovementDetails(Movement movement) {
        System.out.println("===== MOVEMENT DETAILS =====");
        System.out.println("Movement ID: " + movement.getIdMovement());
        System.out.println("Movement Type: " + movement.getMovementType());
        System.out.println("Date: " + movement.getDate());
        System.out.println("Description: " + movement.getDescription());
        System.out.println("Total: " + movement.calculateTotal());
        System.out.println("----- MOVEMENT ITEMS -----");
        for (MovementDetail detail : movement.getDetailList()) {
            System.out.println("Product: " + detail.getProduct().getName() + " | Quantity: " + detail.getQuantity() +
                    " | Unit Price: " + detail.getUnitPrice() + " | Subtotal: " + detail.getSubtotal());
        }
    }

    public void displaySaleDetails(Sale sale) {
        System.out.println("===== SALE DETAILS =====");
        System.out.println("Sale ID: " + sale.getIdSale());
        System.out.println("Sale Date: " + sale.getSaleDate());
        System.out.println("Total Sale Amount: " + sale.getSaleTotal());
        System.out.println("Payment Method: " + sale.getPaymentMethod());
    }

    public void displaySupplierDetails(Supplier supplier) {
        System.out.println("===== SUPPLIER DETAILS =====");
        System.out.println("Supplier ID: " + supplier.getIdSupplier());
        System.out.println("Name: " + supplier.getName());
        System.out.println("Phone: " + supplier.getPhone());
        System.out.println("Email: " + supplier.getEmail());
        System.out.println("Address: " + supplier.getAddress());
    }

}