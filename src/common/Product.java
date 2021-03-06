package common;

public class Product {
    private String name;
    private int id;
    private double value;
    private int quantity;
    private boolean active;

    public Product(int id, String name, double value, int quantity, boolean active) {
        this.name     = name;
        this.id       = id;
        this.value    = value;
        this.quantity = quantity;
        this.active   = active;
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
    public boolean getActive() {return this.active; }
}
