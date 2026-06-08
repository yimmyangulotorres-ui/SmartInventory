package edu.unl.cc.jbrew.Domain.Inventory;

import edu.unl.cc.jbrew.Domain.Exception.InvalidProductNameException;
import edu.unl.cc.jbrew.Domain.Exception.InvalidProductPriceException;
import edu.unl.cc.jbrew.Domain.Exception.InvalidProductStockException;
import edu.unl.cc.jbrew.Domain.Reports.StockAlert;

public class Product {

    private int idProduct;
    private String name;
    private String description;
    private double salePrice;
    private double purchasePrice;
    private int stock;
    private int minStock;

    public Product() {
        salePrice = 0;
        purchasePrice = 0;
    }

    public Product(int idProduct, String name, String description, double salePrice, double purchasePrice, int stock, int minStock) throws InvalidProductNameException, InvalidProductPriceException, InvalidProductStockException {
        this.idProduct = idProduct;
        setName(name);
        this.description = description;
        setSalePrice(salePrice);
        this.purchasePrice = purchasePrice;
        setStock(stock);
}
    public void updateProduct(String name, Double salePrice) {
        this.name = name;
        this.salePrice = salePrice;
    }

    public void modifyStock(int quantity) {
        stock += quantity;
    }

    public boolean verifyStockMinimo() {
        return stock <= minStock;
    }

    public StockAlert generateStockAlert(int idAlert) {
        if (verifyStockMinimo()) {
            return new StockAlert(idAlert, this, stock, minStock, new java.util.Date());
        }
        return null;
    }

    private void validateName(String name) throws InvalidProductStockException{
            if(name == null || name.trim().isEmpty()){
                throw new InvalidProductNameException("The product name is invalid ");
            }
        if(!name.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")){
            throw new InvalidProductNameException("The product name can only contain letters and spaces");
        }

    }
    private void validateStock(int stock) throws InvalidProductStockException{
            if(stock == 0 || stock < 0){
                throw new InvalidProductStockException("The stock cannot be negative and cero");
            }
    }
    private void validatePrice(double salePrice) throws InvalidProductPriceException{
            if(salePrice <= 0 || salePrice >=100){
                throw new InvalidProductPriceException("The price outside the established range");
            }
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

    public void setName(String name) throws InvalidProductNameException{
        validateName(name);
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

    public void setSalePrice(double salePrice) throws InvalidProductPriceException{
        validatePrice(salePrice);
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

    public void setStock(int stock) throws InvalidProductStockException{
        validateStock(stock);
        this.stock = stock;
    }
    public int getMinStock() {
        return minStock;
    }


    public void setMinStock(int minStock) {this.minStock = minStock;}

}
