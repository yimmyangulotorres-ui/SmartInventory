package edu.unl.cc.jbrew.Domain;

import java.util.Date;

public class Sale {

    private int idSale;
    private Date saleDate;
    private double saleTotal;
    private String paymentMethod;

    public Sale(int idSale, Date saleDate, double saleTotal, String paymentMethod) {
        this.idSale = idSale;
        this.saleDate = saleDate;
        this.saleTotal = saleTotal;
        this.paymentMethod = paymentMethod;
    }

    public void registerSale(Date saleDate) {
        this.saleDate = saleDate;
    }

    // Getters y Setters
    public int getIdSale() {
        return idSale;
    }

    public void setIdSale(int idSale) {
        this.idSale = idSale;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public double getSaleTotal() {
        return saleTotal;
    }

    public void setSaleTotal(double saleTotal) {
        this.saleTotal = saleTotal;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}