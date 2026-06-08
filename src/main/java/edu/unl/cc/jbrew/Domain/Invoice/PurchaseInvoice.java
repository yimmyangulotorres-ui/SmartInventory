package edu.unl.cc.jbrew.Domain.Invoice;

import java.util.Date;
import edu.unl.cc.jbrew.Domain.People.Supplier;
import edu.unl.cc.jbrew.Domain.Movements.Movement;

public class PurchaseInvoice extends Invoice { // Herencia de Invoice
    private Supplier supplier; // Composición con Supplier
    private String purchaseOrderNumber;

    public PurchaseInvoice() {
        super();
    }

    public PurchaseInvoice(int idInvoice, Date invoiceDate, String invoiceNumber, Supplier supplier, String purchaseOrderNumber, Movement movement) { // Asociación con Movement
        super(idInvoice, invoiceDate, invoiceNumber, movement);
        this.supplier = supplier;
        this.purchaseOrderNumber = purchaseOrderNumber;
    }

    @Override
    public void calculateTotal() {
        System.out.println("Calculating total for purchase invoice: " + getInvoiceNumber());
    }

    @Override
    public void generateInvoice() {
        System.out.println("Generating purchase invoice: " + getInvoiceNumber() + " for supplier: " + supplier.getName());
    }

    // Getters and Setters
    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public String getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    public void setPurchaseOrderNumber(String purchaseOrderNumber) {
        this.purchaseOrderNumber = purchaseOrderNumber;
    }
}
