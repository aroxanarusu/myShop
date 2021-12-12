package com.MainApp.CustomWidgets;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import products.Produs;
import shop.Shop;

import java.util.ArrayList;
import java.util.List;

public class ActiveSearchBar extends TextField {


    public ActiveSearchBar(TableView table) {
        this.textProperty().addListener((observable, oldValue, newValue) -> {
            List<Produs> produse = getProductsBySearch(newValue);

            if (table != null) {
                table.getItems().clear();
                for (Produs produs : produse) {
                    table.getItems().add(produs);
                }
            }

        });
    }


    public List<Produs> getProductsBySearch(String toSearch) {
        Shop shop = Shop.getInstance();
        List<Produs> products = shop.getProduse();
        List<Produs> listToBeReturned = new ArrayList<>();

        for (Produs produs : products) {
            if (String.valueOf(produs.getId()).contains(toSearch) || produs.getName().contains(toSearch)) {
                listToBeReturned.add(produs);
            }
        }

        return listToBeReturned;
    }
}
