package edu.unl.cc.jbrew.Domain.People;

public class Customer extends Person {

    public Customer(int idCustomer, String name, String phone, String email, String address) {
        super(idCustomer, name, phone, email, address);
    }

    // Getters específicos si se necesita mantener compatibilidad
    public int getIdCustomer() {
        return getId();
    }

    public void setIdCustomer(int idCustomer) {
        setId(idCustomer);
    }
}
