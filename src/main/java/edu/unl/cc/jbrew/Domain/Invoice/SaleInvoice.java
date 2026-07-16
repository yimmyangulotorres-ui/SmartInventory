package edu.unl.cc.jbrew.Domain.Invoice;

import java.util.Date;
import edu.unl.cc.jbrew.Domain.People.Customer;
import edu.unl.cc.jbrew.Domain.Movements.Movement;

public class SaleInvoice extends Invoice { // Herencia de Invoice
    private Customer customer; // Composición con Customer
    private String paymentMethod;

    public SaleInvoice() {
        super();
    }

    public SaleInvoice(int idInvoice, Date invoiceDate, String invoiceNumber, Customer customer, String paymentMethod, Movement movement) { // Asociación con Movement
        super(idInvoice, invoiceDate, invoiceNumber, movement);
        this.customer = customer;
        this.paymentMethod = paymentMethod;
    }


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
