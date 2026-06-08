package edu.unl.cc.jbrew.Domain.Inventory;

<<<<<<< HEAD
import edu.unl.cc.jbrew.Domain.Exception.InvalidCategoryNameException;

=======
>>>>>>> abe90b3b496c4414377c009b51fd0b7209a3304a
import java.util.ArrayList;
import java.util.List;

public class Category {

    private int idCategory;
    private String name;
    private List<Product> productList;

<<<<<<< HEAD
    public Category(int idCategory, String name) throws InvalidCategoryNameException {
        this.idCategory = idCategory;
        setName(name);
        this.productList = new ArrayList<>();
    }
    private void validateName(String name) throws InvalidCategoryNameException{

        if(name == null || name.trim().isEmpty()){
            throw new InvalidCategoryNameException("The Category name cannot be Empty ");
        }
        if(name.matches(".*\\d.*")){
            throw new InvalidCategoryNameException("The Category name cannot be numbers");
        }
    }
=======
    public Category(int idCategory, String name) {
        this.idCategory = idCategory;
        this.name = name;
        this.productList = new ArrayList<>();
    }

>>>>>>> abe90b3b496c4414377c009b51fd0b7209a3304a
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

<<<<<<< HEAD
    public void setName(String name) throws InvalidCategoryNameException{
        validateName(name);
=======
    public void setName(String name) {
>>>>>>> abe90b3b496c4414377c009b51fd0b7209a3304a
        this.name = name;
    }
}
