package common;

/**
 * Created by mattu on 12/4/16.
 */
public class Address {
    private String city;
    private String country;
    private String postalCode;
    private String streetAddress;

    public Address(String city, String country, String postalCode, String streetAddress) {
        this.city          = city;
        this.country       = country;
        this.postalCode    = postalCode;
        this.streetAddress = streetAddress;
    }

    public String getCity() { return this.city; }
    public String getCountry() { return this.country; }
    public String getPostalCode() { return this.postalCode; }
    public String getStreetAddress() { return this.streetAddress; }
}
