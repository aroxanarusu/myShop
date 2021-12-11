package shop;


import products.Produs;
import utility.Utility;

import java.util.ArrayList;
import java.util.List;

public class Shop {

    private static Shop shop;

    public static Shop getInstance() {
        if (shop == null) {
            shop = new Shop();
        }

        return shop;
    }

    private List<Produs> produse;

    public Shop() {
        this.produse = new ArrayList<>();
    }

    public void addProd(Produs produs) {
        produse.add(produs);
    }

    public void printProducts() {
        for (Produs produs : produse) {
            System.out.println(produs);
        }
    }

    public List<Produs> getProduse() {
        return produse;
    }

    public void loadObjects() {

        this.produse = Utility.getInstance().readFromFile();
    }

    public void saveObjects() {

        Utility.getInstance().writeToFile(this.produse);
    }

    public void removeProduct(long id) {

        if (produse.indexOf(getProductById(id)) != -1) {
            produse.remove(produse.indexOf(getProductById(id)));
        }
    }

    public Produs getProductById(long id) {

        for (Produs produs : produse) {
            if (id == produs.getId()) {
                return produs;
            }
        }
        return null;
    }
}
