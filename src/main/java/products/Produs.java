package products;

import java.io.Serializable;

public abstract class Produs implements Serializable {

    private long id;
    private double price;
    private String name;

    public Produs(long id, double price, String name) {
        this.id = id;
        this.price = price;
        this.name = name;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "com.altex.products.Produs{" +
                "id=" + id +
                ", price=" + price +
                ", name='" + name + '\'' +
                '}';
    }
}
