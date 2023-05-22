package ru.ac.uniyar.application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import ru.ac.uniyar.utils.DataHandler;
import ru.ac.uniyar.objects.*;


public class MainPageController {
    @FXML
    private AnchorPane mainPane;

    @FXML
    protected void onShowBrandsButtonClick() {
        ObservableList<Brand> brands = FXCollections.observableList(DataHandler.getBrands());

        TableColumn<Brand, Integer> idColumn = new TableColumn<>("ID в таблице");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Brand, String> brandNameColumn = new TableColumn<>("Наименование");
        brandNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableView<Brand> table = new TableView<>(brands);
        table.setPrefHeight(500);
        table.setPrefWidth(250);
        table.getColumns().addAll(idColumn, brandNameColumn);
        mainPane.getChildren().clear();
        mainPane.getChildren().add(table);
    }

    @FXML
    protected void onShowMachinesButtonClick() {
        ObservableList<Machine> machines = FXCollections.observableList(DataHandler.getMachines());

        TableColumn<Machine, Integer> idColumn = new TableColumn<>("ID в таблице");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Machine, String> nameColumn = new TableColumn<>("Наименование");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Machine, String> rentPriceColumn = new TableColumn<>("Цена аренды");
        rentPriceColumn.setCellValueFactory(new PropertyValueFactory<>("rentPrice"));
        TableColumn<Machine, String> brandIdColumn = new TableColumn<>("ID бренда в таблице");
        brandIdColumn.setCellValueFactory(new PropertyValueFactory<>("brandId"));

        TableView<Machine> table = new TableView<>(machines);
        table.setPrefHeight(500);
        table.setPrefWidth(450);
        table.getColumns().addAll(idColumn, nameColumn, rentPriceColumn, brandIdColumn);
        mainPane.getChildren().clear();
        mainPane.getChildren().add(table);
    }

    @FXML
    protected void onShowClientsButtonClick() {
        ObservableList<Client> clients = FXCollections.observableList(DataHandler.getClients());

        TableColumn<Client, Integer> idColumn = new TableColumn<>("ID в таблице");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Client, String> nameColumn = new TableColumn<>("Имя");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Client, String> addressColumn = new TableColumn<>("Адрес");
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        TableColumn<Client, String> phoneNumberColumn = new TableColumn<>("Телефон");
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        TableView<Client> table = new TableView<>(clients);
        table.setPrefHeight(500);
        table.setPrefWidth(450);
        table.getColumns().addAll(idColumn, nameColumn, addressColumn, phoneNumberColumn);
        mainPane.getChildren().clear();
        mainPane.getChildren().add(table);
    }

    @FXML
    protected void onShowPaymentsButtonClick() {
        ObservableList<Payment> clients = FXCollections.observableList(DataHandler.getPayments());

        TableColumn<Payment, Integer> idColumn = new TableColumn<>("ID в таблице");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Payment, String> dateColumn = new TableColumn<>("Дата платежа");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        TableColumn<Payment, String> rentAgreementIdColumn = new TableColumn<>("ID Договора");
        rentAgreementIdColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        TableColumn<Payment, String> moneyPaidColumn = new TableColumn<>("Размер платежа");
        moneyPaidColumn.setCellValueFactory(new PropertyValueFactory<>("moneyPaid"));

        TableView<Payment> table = new TableView<>(clients);
        table.setPrefHeight(500);
        table.setPrefWidth(450);
        table.getColumns().addAll(idColumn, dateColumn, rentAgreementIdColumn, moneyPaidColumn);
        mainPane.getChildren().clear();
        mainPane.getChildren().add(table);
    }

    @FXML
    protected void onShowRentAgreementsButtonClick() {
        ObservableList<RentAgreement> clients = FXCollections.observableList(DataHandler.getRentAgreements());

        TableColumn<RentAgreement, Integer> idColumn = new TableColumn<>("ID в таблице");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<RentAgreement, String> paymentTypeColumn = new TableColumn<>("Тип платежа");
        paymentTypeColumn.setCellValueFactory(new PropertyValueFactory<>("paymentType"));
        TableColumn<RentAgreement, String> startDateColumn = new TableColumn<>("Дата начала договора");
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        TableColumn<RentAgreement, String> expireDateColumn = new TableColumn<>("Дата окончания договора");
        expireDateColumn.setCellValueFactory(new PropertyValueFactory<>("expireDate"));
        TableColumn<RentAgreement, String> rateColumn = new TableColumn<>("Тариф");
        rateColumn.setCellValueFactory(new PropertyValueFactory<>("rate"));
        TableColumn<RentAgreement, String> clientIdColumn = new TableColumn<>("ID клиента в таблице");
        clientIdColumn.setCellValueFactory(new PropertyValueFactory<>("clientId"));
        TableColumn<RentAgreement, String> machineIdColumn = new TableColumn<>("ID станка в таблице");
        machineIdColumn.setCellValueFactory(new PropertyValueFactory<>("machineId"));

        TableView<RentAgreement> table = new TableView<>(clients);
        table.setPrefHeight(500);
        table.setPrefWidth(650);
        table.getColumns().addAll(idColumn, paymentTypeColumn, startDateColumn, expireDateColumn, rateColumn,
                clientIdColumn, machineIdColumn);
        mainPane.getChildren().clear();
        mainPane.getChildren().add(table);
    }

    @FXML
    protected void onAddBrandButtonClick() {
        mainPane.getChildren().clear();
        Label nameLabel = new Label("Наименование бренда:");
        TextField nameField = new TextField();
        Button addButton = new Button("Добавить");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DataHandler.addBrand(new Brand(nameField.getText()));
            }
        });
        mainPane.getChildren().addAll(nameLabel, nameField, addButton);
    }

    @FXML
    protected void onAddMachineButtonClick() {

    }

    @FXML
    protected void onAddClientButtonClick() {

    }

    @FXML
    protected void onAddRentAgreementButtonClick() {

    }

    @FXML
    protected void onAddPaymentButtonClick() {

    }
}
