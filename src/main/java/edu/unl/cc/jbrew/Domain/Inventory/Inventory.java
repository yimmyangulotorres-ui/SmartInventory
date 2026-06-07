package edu.unl.cc.jbrew.Domain.Inventory;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Product> productList;
    private List<Category> categoryList;

    public Inventory() {
        productList = new ArrayList<>();
        categoryList = new ArrayList<>();
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    public void removeProduct(Product product) {
        productList.remove(product);
    }

    public Product searchProduct(String name) {
        for (Product product : productList) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        return null;
    }

    public List<Product> showProduct() {
        return productList;
    }

    public void addCategory(Category category) {
        categoryList.add(category);
    }

    public void updateCategory(Category category) {
        for (int i = 0; i < categoryList.size(); i++) {
            if (categoryList.get(i).getIdCategory() == category.getIdCategory()) {
                categoryList.set(i, category);
                return;
            }
        }
    }

    public void removeCategory(Category category) {
        categoryList.remove(category);
    }

    public List<Category> showCategory() {
        return categoryList;
    }

    // Getters y Setters
    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
}
