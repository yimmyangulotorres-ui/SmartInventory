package edu.unl.cc.jbrew.Domain.Invoice;

import java.util.Date;
import edu.unl.cc.jbrew.Domain.Movements.Movement;

public abstract class Invoice {
    private int idInvoice;
    private Date invoiceDate;
    private double total;
    private String invoiceNumber;
    private Movement movement;

    public Invoice() {
    }

    public Invoice(int idInvoice, Date invoiceDate, String invoiceNumber, Movement movement) {
        this.idInvoice = idInvoice;
        this.invoiceDate = invoiceDate;
        this.invoiceNumber = invoiceNumber;
        this.movement = movement;
    }

    public abstract void calculateTotal();

    public abstract void generateInvoice();

    // Getters and Setters
    public int getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(int idInvoice) {
        this.idInvoice = idInvoice;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Movement getMovement() {
        return movement;
    }

    public void setMovement(Movement movement) {
        this.movement = movement;
    }
}
