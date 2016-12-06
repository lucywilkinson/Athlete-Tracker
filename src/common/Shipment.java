package common;

import javax.print.DocFlavor;

/**
 * Created by lucywilkinson on 12/5/16.
 */
public class Shipment {
    private int shipmentId;
    private String product;
    private String creator;
    private String worker;
    private String reciever;
    private String address;
    private int quantity;
    private boolean fulfilled = false;

    /**
     * Shipment object to create and add a new shipment to the DB
     * @param product
     * @param creator
     * @param worker
     * @param reciever
     * @param address
     * @param quantity
     * @param fulfilled
     */
    public Shipment(String product, String creator, String worker, String reciever, String address, int quantity, boolean fulfilled) {
        this.product = product;
        this.creator = creator;
        this.worker = worker;
        this.reciever = reciever;
        this.address = address;
        this.quantity = quantity;
        this.fulfilled = fulfilled;
    }

    /**
     * Existing shipment
     * @param id
     * @param product
     * @param creator
     * @param worker
     * @param reciever
     * @param address
     * @param quantity
     * @param fulfilled
     */
    public Shipment(int id, String product, String creator, String worker, String reciever, String address, int quantity, boolean fulfilled) {
        this.shipmentId = id;
        this.product = product;
        this.creator = creator;
        this.worker = worker;
        this.reciever = reciever;
        this.address = address;
        this.quantity = quantity;
        this.fulfilled = fulfilled;
    }

    //Getters
    public int getShipmentId() {
        return this.shipmentId;
    }
    public String getProduct() {
        return this.product;
    }
    public String getCreator() {
        return this.creator;
    }
    public String getWorker() {
        return this.worker;
    }
    public String getReciever() {
        return this.reciever;
    }
    public String getAddress() {
        return this.address;
    }
    public int getQuantity() {
        return this.quantity;
    }
    public boolean getFulfilled() {
        return this.fulfilled;
    }
}