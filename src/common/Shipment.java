package common;

import javax.print.DocFlavor;

public class Shipment {
    private int workerID;
    private int athleteID;
    private int creatorID;
    private int quantity;
    private String product;
    private Boolean fulfilled;

    public Shipment(int workerID, int athleteID, int creatorID, String product, int quantity, boolean fulfilled) {
        this.workerID = workerID;
        this.athleteID = athleteID;
        this.creatorID = creatorID;
        this.product   = product;
        this.quantity  = quantity;
        this.fulfilled = fulfilled;
    }

    //Getters
    public int getWorkerID() {
        return workerID;
    }

    public int getAthleteID() {
        return athleteID;
    }

    public int getCreatorID() {
        return creatorID;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getProduct() {
        return product;
    }

    public Boolean getFulfilled() { return fulfilled; }
}