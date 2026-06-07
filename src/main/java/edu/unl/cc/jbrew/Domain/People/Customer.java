package edu.unl.cc.jbrew.Domain.People;

public class Customer {

    private int idCustomer;
    private String name;
    private String phone;
    private String email;
    private String address;

    public Customer(int idCustomer, String name, String phone, String email, String address) {
        this.idCustomer = idCustomer;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    // Getters y Setters
    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
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
