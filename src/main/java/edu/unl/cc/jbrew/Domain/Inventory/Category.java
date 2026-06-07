package edu.unl.cc.jbrew.Domain.Inventory;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private int idCategory;
    private String name;
    private List<Product> productList;

    public Category(int idCategory, String name) {
        this.idCategory = idCategory;
        this.name = name;
        this.productList = new ArrayList<>();
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    public void removeProduct(Product product) {
        productList.remove(product);
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    // Getters y Setters
    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
