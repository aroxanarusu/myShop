package com.MainApp;

import com.MainApp.CustomWidgets.ActiveSearchBar;
import com.MainApp.ProductViews.LaptopView;
import com.MainApp.ProductViews.PhoneView;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import products.Produs;
import shop.Shop;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {

        Shop shop = new Shop();
//        try {
//        Utility uc = Utility.getInstance();
////            uc.readFromCSVFile("laptops.csv");
//            uc.readFromCSVFile("phones.csv");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

        shop.loadObjects();


        GridPane gridPane = new GridPane();
        gridPane.setMinSize(800, 600);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        refreshTable(getTable(gridPane));
        addAdminButtons(gridPane);


        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.TOP_CENTER);
        Scene scene = new Scene(gridPane);
        stage.setTitle("Shop");
        stage.setScene(scene);
        stage.show();

    }

    private void addAdminButtons(GridPane gridPane) {

        MenuItem laptop = new MenuItem("Laptop");
        MenuItem phone = new MenuItem("Phone");
        MenuItem cartus = new MenuItem("Cartus");
        MenuButton addMenuButton = new MenuButton("Adaugare produs", null, laptop, phone, cartus);

        laptop.setOnAction(event -> {
            LaptopView laptopView = new LaptopView();
            laptopView.show();
        });

        phone.setOnAction(event -> {
            PhoneView phoneView = new PhoneView();
            phoneView.show();
        });

        Button deleteProductButton = new Button("Eliminare produs");
        Button modifyProductButton = new Button("Modificare produs");

        Button refreshTable = new Button("Refresh Table");
        refreshTable.setOnAction(actionEvent -> refreshTable(getTable(gridPane)));
        gridPane.add(addMenuButton, 0, 1);
        gridPane.add(deleteProductButton, 0, 2);
        gridPane.add(modifyProductButton, 0, 3);
        gridPane.add(refreshTable, 0, 4);

        setDeleteButtonAction(gridPane, deleteProductButton);

        generateListOfArticles(gridPane);

    }

    private void setDeleteButtonAction(GridPane gridPane, Button deleteProductButton) {
        deleteProductButton.setOnAction(actionEvent -> {
            TableView tableView = (TableView) getTable(gridPane);
            Produs produs = (Produs) tableView.getSelectionModel().getSelectedItem();
            Shop.getInstance().removeProduct(produs.getId());
            refreshTable(getTable(gridPane));

        });
    }

    public void refreshTable(Node node) {
        TableView table = (TableView) node;

        if (table != null) {
            table.getItems().clear();
            Shop shop = Shop.getInstance();
            for (Produs produs : shop.getProduse()) {
                table.getItems().add(produs);
            }
        }
        Shop shop = Shop.getInstance();
        shop.saveObjects();
    }

    public Node getTable(GridPane gridPane) {
        for (Node node : gridPane.getChildren()) {
            if (gridPane.getRowIndex(node) == 8 && gridPane.getColumnIndex(node) == 0) {
                return node;
            }
        }
        return null;
    }

    private void generateListOfArticles(GridPane gridPane) {

        TableColumn<Produs, String> idTableColumn = new TableColumn<>("ID");
        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn priceTableColumn = new TableColumn("Price");
        priceTableColumn.setCellValueFactory(new PropertyValueFactory("price"));

        TableColumn nameTableColumn = new TableColumn("Name");
        nameTableColumn.setCellValueFactory(new PropertyValueFactory("name"));

        TableView table = new TableView();
        table.getColumns().addAll(idTableColumn, priceTableColumn, nameTableColumn/*, graphicCardColumn, numberOfPortsColumn, voltajColumn*/);

        table.setRowFactory(rw -> {
            TableRow row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Produs produs = (Produs) row.getItem();

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("About product");
                    alert.setHeaderText("Informatiile tehnice ale produsului: ");
                    alert.setContentText(produs.prettyPrint());

                    alert.showAndWait();

                }
            });
            return row;
        });
        gridPane.add(table, 0, 8);

        TextField searchTextField = new ActiveSearchBar(table);
        Label searchLabel = new Label("Search products");
        gridPane.add(searchTextField, 0, 10);
        gridPane.add(searchLabel, 0, 9);

    }

    public static void main(String[] args) {
        launch();
    }
}