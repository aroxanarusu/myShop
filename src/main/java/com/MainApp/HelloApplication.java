package com.MainApp;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import products.Laptop;
import products.Phone;
import products.Produs;
import shop.Shop;
import utility.NumericTextField;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {

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

    private void clearTextFields(GridPane gridPane) {

        for (Node node : gridPane.getChildren()) {
            if (node instanceof TextField) {
                TextField text = (TextField) node;
                text.setText("");
            }
        }
    }

    private void addAdminButtons(GridPane gridPane) {
//        Button addProductButton = new Button("Adaugare produs");


        MenuItem laptop = new MenuItem("Laptop");
        MenuItem phone = new MenuItem("Phone");
        MenuItem cartus = new MenuItem("Cartus");
        MenuButton addMenuButton = new MenuButton("Adaugare produs", null, laptop, phone, cartus);

        laptop.setOnAction(event -> generateAddLaptopView());

        phone.setOnAction(event -> generateAddPhoneView());

//        addProductButton.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                generateAddProductView();
//            }
//        });
        Button deleteProductButton = new Button("Eliminare produs");
        Button modifyProductButton = new Button("Modificare produs");

        Button refreshTable = new Button("Refresh Table");
        refreshTable.setOnAction(actionEvent -> refreshTable(getTable(gridPane)));
        gridPane.add(addMenuButton, 0, 1);
        gridPane.add(deleteProductButton, 0, 2);
        gridPane.add(modifyProductButton, 0, 3);
        gridPane.add(refreshTable, 0, 4);

        generateListOfArticles(gridPane);

    }

    private void generateAddPhoneView() {
        Stage addProductStage = new Stage();

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(800, 600);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setAlignment(Pos.TOP_CENTER);

        generatePhoneLabelsAndFields(gridPane, addProductStage);

        Scene scene = new Scene(gridPane);
        addProductStage.setTitle("Add product");
        addProductStage.setScene(scene);
        addProductStage.show();

    }

    private void generatePhoneLabelsAndFields(GridPane gridPane, Stage addProductStage) {
        Label titleLabel = new Label("Add phone");
        gridPane.add(titleLabel, 2, 0);

        Label idLabel = new Label("ID");
        NumericTextField idTextField = new NumericTextField();

        Label priceLabel = new Label("Price");
        NumericTextField priceTextField = new NumericTextField();

        Label productNameLabel = new Label("Product name");
        TextField productNameTextField = new TextField();

        CheckBox hasTouch = new CheckBox("Yes");
        CheckBox doesntHaveTouch = new CheckBox("No");
        Label touchScreen = new Label("TouchScreen: ");

        Label numbOfSims = new Label("Number of sims");
        NumericTextField numbOfSimsTextField = new NumericTextField();

        Label voltajLabel = new Label("Voltage");
        NumericTextField voltajTextField = new NumericTextField();

        HBox hbox = new HBox();
        hbox.getChildren().addAll(hasTouch, doesntHaveTouch);

        Button addProductButton = new Button("Add product");

        addProductButton.setOnAction(actionEvent -> {

            String id = idTextField.getText();
            String price = priceTextField.getText();
            boolean phoneTouchScreen = hasTouch.isSelected();
            String numberOfSims = numbOfSimsTextField.getText();
            String voltaj = voltajTextField.getText();
            String productName = productNameTextField.getText();

            Phone phone = new Phone(Long.parseLong(id), Double.parseDouble(price), productName, Integer.parseInt(numberOfSims), phoneTouchScreen, Integer.parseInt(voltaj));

            Shop shop = Shop.getInstance();
            shop.addProd(phone);
            refreshTable(getTable(gridPane));
            addProductStage.close();
        });

        setPhoneLabelsAndTextFieldsOnGrid(gridPane, addProductStage, idLabel, idTextField, priceLabel, priceTextField, productNameLabel, productNameTextField, touchScreen, numbOfSims, numbOfSimsTextField, voltajLabel, voltajTextField, hbox, addProductButton);
    }

    private void setPhoneLabelsAndTextFieldsOnGrid(GridPane gridPane, Stage addProductStage, Label idLabel, NumericTextField idTextField, Label priceLabel, NumericTextField priceTextField, Label productNameLabel, TextField productNameTextField, Label touchScreen, Label numbOfSims, NumericTextField numbOfSimsTextField, Label voltajLabel, NumericTextField voltajTextField, HBox hbox, Button addProductButton) {
        Button clearButton = new Button("Clear");
        clearButton.setOnAction(actionEvent -> clearTextFields(gridPane));

        Button closeButton = new Button("Close");
        closeButton.setOnAction(actionEvent -> addProductStage.close());

        HBox hbox2 = new HBox();
        hbox2.getChildren().addAll(addProductButton, clearButton, closeButton);


        gridPane.add(idLabel, 1, 1);
        gridPane.add(idTextField, 2, 1);

        gridPane.add(priceLabel, 1, 2);
        gridPane.add(priceTextField, 2, 2);

        gridPane.add(productNameLabel, 1, 3);
        gridPane.add(productNameTextField, 2, 3);

        gridPane.add(touchScreen, 1, 4);
        gridPane.add(hbox, 2, 4);

        gridPane.add(numbOfSims, 1, 5);
        gridPane.add(numbOfSimsTextField, 2, 5);

        gridPane.add(voltajLabel, 1, 6);
        gridPane.add(voltajTextField, 2, 6);
        gridPane.add(new Label(""), 0, 7);


        gridPane.add(hbox2, 2, 8);
    }

    public void generateAddLaptopView() {
        Stage addProductStage = new Stage();

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(800, 600);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setAlignment(Pos.TOP_CENTER);


        generateAddLaptopView(gridPane, addProductStage);

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

//        TableColumn graphicCardColumn = new TableColumn("Graphic Card");
//        graphicCardColumn.setCellValueFactory(new PropertyValueFactory("hasGraphicCard"));
//
//        TableColumn numberOfPortsColumn = new TableColumn("Number of ports");
//        numberOfPortsColumn.setCellValueFactory(new PropertyValueFactory("numbOfPorts"));
//
//        TableColumn voltajColumn = new TableColumn("Voltaj");
//        voltajColumn.setCellValueFactory(new PropertyValueFactory("voltaj"));


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
    }

    private void generateAddLaptopView(GridPane gridPane, Stage addProductStage) {
        Label titleLabel = new Label("Add laptop");
        gridPane.add(titleLabel, 2, 0);

        Label idLabel = new Label("ID");
        NumericTextField idTextField = new NumericTextField();


        Label priceLabel = new Label("Price");
        NumericTextField priceTextField = new NumericTextField();

        Label productNameLabel = new Label("Product name");
        TextField productNameTextField = new TextField();

        CheckBox hasGraphic = new CheckBox("Yes");
        CheckBox doesntHaveGraphic = new CheckBox("No");
        Label graphicCard = new Label("Has graphic card");

        Label numbOfPortsLabel = new Label("Number of ports");
        NumericTextField numbOfPortsTextField = new NumericTextField();

        Label voltajLabel = new Label("Voltage");
        NumericTextField voltajTextField = new NumericTextField();

        HBox hbox = new HBox();
        hbox.getChildren().addAll(hasGraphic, doesntHaveGraphic);

        Button addProductButton = new Button("Add product");

        addProductButton.setOnAction(actionEvent -> {
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
            addProductStage.close();


        });

        setLaptopLabelsAndTextFieldsOnGrid(gridPane, addProductStage, idLabel, idTextField, priceLabel, priceTextField, productNameLabel, productNameTextField, graphicCard, numbOfPortsLabel, numbOfPortsTextField, voltajLabel, voltajTextField, hbox, addProductButton);
    }

    private void setLaptopLabelsAndTextFieldsOnGrid(GridPane gridPane, Stage addProductStage, Label idLabel, NumericTextField idTextField, Label priceLabel, NumericTextField priceTextField, Label productNameLabel, TextField productNameTextField, Label graphicCard, Label numbOfPortsLabel, NumericTextField numbOfPortsTextField, Label voltajLabel, NumericTextField voltajTextField, HBox hbox, Button addProductButton) {
        Button clearButton = new Button("Clear");
        clearButton.setOnAction(actionEvent -> clearTextFields(gridPane));

        Button closeButton = new Button("Close");
        closeButton.setOnAction(actionEvent -> addProductStage.close());

        HBox hbox2 = new HBox();
        hbox2.getChildren().addAll(addProductButton, clearButton, closeButton);

        gridPane.add(idLabel, 1, 1);
        gridPane.add(idTextField, 2, 1);

        gridPane.add(priceLabel, 1, 2);
        gridPane.add(priceTextField, 2, 2);

        gridPane.add(productNameLabel, 1, 3);
        gridPane.add(productNameTextField, 2, 3);

        gridPane.add(graphicCard, 1, 4);
        gridPane.add(hbox, 2, 4);

        gridPane.add(numbOfPortsLabel, 1, 5);
        gridPane.add(numbOfPortsTextField, 2, 5);

        gridPane.add(voltajLabel, 1, 6);
        gridPane.add(voltajTextField, 2, 6);
        gridPane.add(new Label(""), 0, 7);

        gridPane.add(hbox2, 2, 8);
    }

    public static void main(String[] args) {
        launch();
    }
}