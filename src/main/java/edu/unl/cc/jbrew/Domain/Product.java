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

    // Modificar producto
    public void modifyProduct(String name, String description, double salePrice, double purchasePrice) {
        this.name = name;
        this.description = description;
        this.salePrice = salePrice;
        this.purchasePrice = purchasePrice;
    }

    // Modificar stock
    public void modifyStock(int quantity) {
        this.stock += quantity;
    }

    // Verificar stock mínimo
    public boolean verifyMinimumStock() {
        return stock <= 5;
    }

    // Getters y Setters
    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}