package common;

public class Product {
    private String name;
    private int id;
    private double value;
    private int quantity;

    public Product(String name, int id, double value, int quantity) {
        this.name     = name;
        this.id       = id;
        this.value    = value;
        this.quantity = quantity;
    }

    public Product(String name, double value, int quantity) {
        this.name     = name;
        this.value    = value;
        this.quantity = quantity;
    }

    public String getName() { return this.name; }
    public int getId() {
        return id;
    }
    public double getValue() {
        return this.value;
    }
    public int getQuantity() {
        return this.quantity;
    }
}
