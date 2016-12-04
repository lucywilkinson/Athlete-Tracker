package common;

/**
 * Created by mattu on 12/4/16.
 */
public class Shipment {
    private User customer;
    private Product product;
    private Address address;

    public Shipment(User customer, Product product, Address address) {
        this.customer = customer;
        this.product  = product;
        this.address  = address;
    }

    public User getCustomer() {return this.customer; }
    public Product getProduct() {return this.product; }
    public Address getAddress() {return this.address; }
}
