package pl.mobile.fuelradar.data.model;

/**
 * Created by zjuroszek on 10.05.16.
 */
public class FueilingStation {

    private String address;
    private String price;
    private String url;

    public FueilingStation(String address, String price, String url) {
        this.address = address;
        this.price = price;
        this.url = url;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
