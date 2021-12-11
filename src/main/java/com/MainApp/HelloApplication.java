package com.MainApp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import products.Laptop;
import products.Produs;
import shop.Shop;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Shop shop = new Shop();
        shop.loadObjects();

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(800, 600);
        gridPane.setPadding(new Insets(10, 10, 10, 10));

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
        Button addProductButton = new Button("Adaugare produs");


        addProductButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                generateAddProductView();
            }
        });
        Button deleteProductButton = new Button("Eliminare produs");
        Button modifyProductButton = new Button("Modificare produs");
        gridPane.add(addProductButton, 0, 1);
        gridPane.add(deleteProductButton, 0, 2);
        gridPane.add(modifyProductButton, 0, 3);
    }

    public void generateAddProductView() {
        Stage addProductStage = new Stage();

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(800, 600);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setAlignment(Pos.TOP_CENTER);


        generatePorductLabelsAndFields(gridPane);
        generateListOfArticles(gridPane);

        Scene scene = new Scene(gridPane);
        addProductStage.setTitle("Add product");
        addProductStage.setScene(scene);
        addProductStage.show();

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

        TableColumn<Produs, String> idTableColumn = new TableColumn("ID");
        idTableColumn.setCellValueFactory(new PropertyValueFactory("id"));

        TableColumn priceTableColumn = new TableColumn("Price");
        priceTableColumn.setCellValueFactory(new PropertyValueFactory("price"));

        TableColumn nameTableColumn = new TableColumn("Name");
        nameTableColumn.setCellValueFactory(new PropertyValueFactory("name"));

        TableColumn graphicCardColumn = new TableColumn("Graphic Card");
        graphicCardColumn.setCellValueFactory(new PropertyValueFactory("hasGraphicCard"));

        TableColumn numberOfPortsColumn = new TableColumn("Number of ports");
        numberOfPortsColumn.setCellValueFactory(new PropertyValueFactory("numbOfPorts"));

        TableColumn voltajColumn = new TableColumn("Voltaj");
        voltajColumn.setCellValueFactory(new PropertyValueFactory("voltaj"));


        TableView table = new TableView();
        table.getColumns().addAll(idTableColumn, priceTableColumn, nameTableColumn, graphicCardColumn, numberOfPortsColumn, voltajColumn);

        gridPane.add(table, 0, 8);
    }

    private void generatePorductLabelsAndFields(GridPane gridPane) {
        Label titleLabel = new Label("Add product");
        gridPane.add(titleLabel, 0, 0);

        Label idLabel = new Label("ID");
        TextField idTextField = new TextField();

        Label priceLabel = new Label("Price");
        TextField priceTextField = new TextField();

        Label productNameLabel = new Label("Product name");
        TextField productNameTextField = new TextField();

        CheckBox hasGraphic = new CheckBox("Yes");
        CheckBox doesntHaveGraphic = new CheckBox("No");
        Label graphicCard = new Label("Has graphic card");

        Label numbOfPortsLabel = new Label("Number of ports");
        TextField numbOfPortsTextField = new TextField();

        Label voltajLabel = new Label("Voltaj");
        TextField voltajTextField = new TextField();

        Button addProductButton = new Button("Add product");
        addProductButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                String id = idTextField.getText();
                String price = priceTextField.getText();
                boolean hasGraphicCard = hasGraphic.isSelected();
                String numerOfPorts = numbOfPortsTextField.getText();
                String voltaj = voltajTextField.getText();
                String productName = productNameTextField.getText();

                Laptop laptop = new Laptop(Long.parseLong(id), Double.parseDouble(price), productName, hasGraphicCard, Integer.parseInt(numerOfPorts), Integer.parseInt(voltaj));

                Shop shop = Shop.getInstance();
                shop.addProd(laptop);
                refreshTable(getTable(gridPane));
            }
        });

        gridPane.add(idLabel, 1, 1);
        gridPane.add(idTextField, 2, 1);

        gridPane.add(priceLabel, 1, 2);
        gridPane.add(priceTextField, 2, 2);

        gridPane.add(productNameLabel, 1, 3);
        gridPane.add(productNameTextField, 2, 3);

        gridPane.add(graphicCard, 1, 4);
        gridPane.add(hasGraphic, 2, 4);
        gridPane.add(doesntHaveGraphic, 3, 4);

        gridPane.add(numbOfPortsLabel, 1, 5);
        gridPane.add(numbOfPortsTextField, 2, 5);

        gridPane.add(voltajLabel, 1, 6);
        gridPane.add(voltajTextField, 2, 6);

        gridPane.add(addProductButton, 0, 7);

    }

    public static void main(String[] args) {
        launch();
    }
}