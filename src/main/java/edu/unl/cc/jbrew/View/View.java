package edu.unl.cc.jbrew.View;

import edu.unl.cc.jbrew.Domain.Inventory.Inventory;
import edu.unl.cc.jbrew.Domain.Inventory.Product;
import edu.unl.cc.jbrew.Domain.Inventory.Category;
import edu.unl.cc.jbrew.Domain.Movements.Movement;
import edu.unl.cc.jbrew.Domain.Movements.ProductMovement;
import edu.unl.cc.jbrew.Domain.Invoice.SaleInvoice;
import edu.unl.cc.jbrew.Domain.People.Supplier;
import edu.unl.cc.jbrew.Domain.People.Customer;
import edu.unl.cc.jbrew.Domain.Kardex.Kardex;
import edu.unl.cc.jbrew.Domain.Reports.StockAlert;
import java.util.Scanner;

public class View {
    private Scanner scanner; // Asociación con Scanner

    public View() {
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("\n===== SMART INVENTORY SYSTEM =====");
        System.out.println("1. Inventory Management");
        System.out.println("2. Movement Management");
        System.out.println("3. Invoice Management");
        System.out.println("4. People Management");
        System.out.println("5. Kardex View");
        System.out.println("6. Reports");
        System.out.println("7. Stock Alerts");
        System.out.println("0. Exit");
        System.out.print("Select an option: ");
    }

    public int getIntInput() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public String getStringInput() {
        return scanner.nextLine();
    }

    public double getDoubleInput() {
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void displayInventoryProductList(Inventory inventory) { // Asociación con Inventory
        System.out.println("===== PRODUCT LIST FROM INVENTORY =====");
        for (Product product : inventory.showProduct()) {
            System.out.println("Product ID: " + product.getIdProduct());
            System.out.println("Name: " + product.getName());
            System.out.println("Description: " + product.getDescription());
            System.out.println("Sale Price: " + product.getSalePrice());
            System.out.println("Purchase Price: " + product.getPurchasePrice());
            System.out.println("Stock: " + product.getStock());
            System.out.println("Min Stock: " + product.getMinStock());
            System.out.println("----------------------------------------");
        }
    }

    public void displayCategoryList(Inventory inventory) { // Asociación con Inventory
        System.out.println("===== CATEGORY LIST =====");
        for (Category category : inventory.showCategory()) {
            System.out.println("Category ID: " + category.getIdCategory());
            System.out.println("Name: " + category.getName());
            System.out.println("----------------------------------------");
        }
    }

    public void displayMovementDetails(Movement movement) { // Asociación con Movement
        System.out.println("===== MOVEMENT DETAILS =====");
        System.out.println("Movement ID: " + movement.getIdMovement());
        System.out.println("Movement Type: " + movement.getMovementType());
        System.out.println("Status: " + movement.getStatus());
        System.out.println("Date: " + movement.getDate());
        System.out.println("Description: " + movement.getDescription());
        System.out.println("Total: " + movement.calculateTotal());
        System.out.println("----- MOVEMENT ITEMS -----");
        for (ProductMovement detail : movement.getProductMovementList()) {
            System.out.println("Product: " + detail.getProduct().getName() + " | Quantity: " + detail.getQuantity() +
                    " | Unit Price: " + detail.getUnitPrice() + " | Subtotal: " + detail.getSubtotal());
        }
    }

    public void displaySaleInvoiceDetails(SaleInvoice saleInvoice) { // Asociación con SaleInvoice
        System.out.println("===== SALE INVOICE DETAILS =====");
        System.out.println("Invoice ID: " + saleInvoice.getIdInvoice());
        System.out.println("Invoice Number: " + saleInvoice.getInvoiceNumber());
        System.out.println("Invoice Date: " + saleInvoice.getInvoiceDate());
        System.out.println("Total: " + saleInvoice.getTotal());
        System.out.println("Customer: " + saleInvoice.getCustomer().getName());
        System.out.println("Payment Method: " + saleInvoice.getPaymentMethod());
    }

    public void displaySupplierDetails(Supplier supplier) { // Asociación con Supplier
        System.out.println("===== SUPPLIER DETAILS =====");
        System.out.println("Supplier ID: " + supplier.getIdSupplier());
        System.out.println("Name: " + supplier.getName());
        System.out.println("Phone: " + supplier.getPhone());
        System.out.println("Email: " + supplier.getEmail());
        System.out.println("Address: " + supplier.getAddress());
    }

    public void displayCustomerDetails(Customer customer) { // Asociación con Customer
        System.out.println("===== CUSTOMER DETAILS =====");
        System.out.println("Customer ID: " + customer.getIdCustomer());
        System.out.println("Name: " + customer.getName());
        System.out.println("Phone: " + customer.getPhone());
        System.out.println("Email: " + customer.getEmail());
        System.out.println("Address: " + customer.getAddress());
    }

    public void displayKardexEntry(Kardex kardex) { // Asociación con Kardex
        System.out.println("===== KARDEX ENTRY =====");
        System.out.println("Kardex ID: " + kardex.getIdKardex());
        System.out.println("Product: " + kardex.getProduct().getName());
        System.out.println("Date: " + kardex.getDate());
        System.out.println("Movement Type: " + kardex.getMovementType());
        System.out.println("Quantity: " + kardex.getQuantity());
        System.out.println("Balance: " + kardex.getBalance());
        System.out.println("Description: " + kardex.getDescription());
    }

    public void displayStockAlert(StockAlert alert) { // Asociación con StockAlert
        System.out.println("===== STOCK ALERT =====");
        System.out.println("Alert ID: " + alert.getIdAlert());
        System.out.println("Product: " + alert.getProduct().getName());
        System.out.println("Current Stock: " + alert.getCurrentStock());
        System.out.println("Minimum Stock: " + alert.getMinStock());
        System.out.println("Alert Date: " + alert.getAlertDate());
        System.out.println("Status: " + (alert.isResolved() ? "RESOLVED" : "PENDING"));
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void closeScanner() {
        scanner.close();
    }
}