package edu.unl.cc.jbrew.Domain.Invoice;

import java.util.Date;
import edu.unl.cc.jbrew.Domain.People.Customer;
import edu.unl.cc.jbrew.Domain.Movements.Movement;

public class SaleInvoice extends Invoice {
    private Customer customer;
    private String paymentMethod;

    public SaleInvoice() {
        super();
    }

    public SaleInvoice(int idInvoice, Date invoiceDate, String invoiceNumber, Customer customer, String paymentMethod, Movement movement) {
        super(idInvoice, invoiceDate, invoiceNumber, movement);
        this.customer = customer;
        this.paymentMethod = paymentMethod;
    }

    @Override
    public void calculateTotal() {
        // Implementation for calculating sale invoice total
        System.out.println("Calculating total for sale invoice: " + getInvoiceNumber());
    }

    @Override
    public void generateInvoice() {
        System.out.println("Generating sale invoice: " + getInvoiceNumber() + " for customer: " + customer.getName());
    }

    // Getters and Setters
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
