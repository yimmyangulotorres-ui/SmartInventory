package edu.unl.cc.jbrew.Domain;

public class Supplier {

    private int idSupplier;
    private String name;
    private String phone;
    private String email;
    private String address;

    public Supplier(int idSupplier, String name, String phone, String email, String address) {
        this.idSupplier = idSupplier;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    // Getters y Setters
    public int getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(int idSupplier) {
        this.idSupplier = idSupplier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}