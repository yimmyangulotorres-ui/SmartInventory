package edu.unl.cc.jbrew.Domain;

public class Product {

    private int idProduct;
    private String name;
    private String description;
    private double salePrice;
    private double purchasePrice;
    private int stock;

    public Product() {
        salePrice = 0;
        purchasePrice = 0;
    }

    public Product(int idProduct, String name, String description, double salePrice, double purchasePrice, int stock) {
        this.idProduct = idProduct;
        this.name = name;
        this.description = description;
        this.salePrice = salePrice;
        this.purchasePrice = purchasePrice;
        this.stock = stock;
    }

    public void updateProduct(String name, Double salePrice) {
        this.name = name;
        this.salePrice = salePrice;
    }

    public void modifyStock(int quantity) {
        stock += quantity;
    }

    public boolean verifyStockMinimo() {
        return stock <= 5;
    }

    // Getters y Setters
    public int getIdProduct() {
        return idProduct;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public int getStock() {
        return stock;
    }
}