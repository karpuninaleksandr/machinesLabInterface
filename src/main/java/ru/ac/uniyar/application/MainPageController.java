package ru.ac.uniyar.application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import ru.ac.uniyar.utils.DataHandler;
import ru.ac.uniyar.objects.*;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


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

        TableColumn<RentAgreement, Integer> idColumn = new TableColumn<>("ID в таблице:");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<RentAgreement, String> paymentTypeColumn = new TableColumn<>("Тип платежей:");
        paymentTypeColumn.setCellValueFactory(new PropertyValueFactory<>("paymentType"));
        TableColumn<RentAgreement, String> startDateColumn = new TableColumn<>("Дата начала договора:");
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        TableColumn<RentAgreement, String> expireDateColumn = new TableColumn<>("Дата окончания договора:");
        expireDateColumn.setCellValueFactory(new PropertyValueFactory<>("expireDate"));
        TableColumn<RentAgreement, String> rateColumn = new TableColumn<>("Тариф:");
        rateColumn.setCellValueFactory(new PropertyValueFactory<>("rate"));
        TableColumn<RentAgreement, String> clientIdColumn = new TableColumn<>("ID клиента в таблице:");
        clientIdColumn.setCellValueFactory(new PropertyValueFactory<>("clientId"));
        TableColumn<RentAgreement, String> machineIdColumn = new TableColumn<>("ID станка в таблице:");
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
                onShowBrandsButtonClick();
            }
        });
        VBox inputBox = new VBox();
        inputBox.getChildren().addAll(nameLabel, nameField, addButton);
        mainPane.getChildren().addAll(inputBox);
    }

    @FXML
    protected void onAddMachineButtonClick() {
        List<Brand> brands = DataHandler.getBrands();
        ObservableList<String> brandsObservable = FXCollections.observableList(brands.stream().map(Brand::getName)
                .collect(Collectors.toList()));
        mainPane.getChildren().clear();
        Label nameLabel = new Label("Наименование станка:");
        TextField nameField = new TextField();
        Label rentPriceLabel = new Label("Стоимость аренды:");
        TextField rentPriceField = new TextField();
        Label brandIdLabel = new Label("ID бренда:");
        ComboBox<String> brandBox = new ComboBox<>(brandsObservable);
        brandBox.setValue(brandsObservable.get(0));
        Button addButton = new Button("Добавить");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DataHandler.addMachine(new Machine(nameField.getText(), Integer.parseInt(rentPriceField.getText()),
                        brands.stream().filter(it -> it.getName().equals(brandBox.getValue())).toList().get(0).getId()));
                onShowMachinesButtonClick();
            }
        });
        VBox inputBox = new VBox();
        inputBox.getChildren().addAll(nameLabel, nameField, rentPriceLabel, rentPriceField, brandIdLabel, brandBox, addButton);
        mainPane.getChildren().addAll(inputBox);
    }

    @FXML
    protected void onAddClientButtonClick() {
        mainPane.getChildren().clear();
        Label nameLabel = new Label("Имя:");
        TextField nameField = new TextField();
        Label addressLabel = new Label("Адрес:");
        TextField addressField = new TextField();
        Label phoneNumberLabel = new Label("Телефон:");
        TextField phoneNumberField = new TextField();
        Button addButton = new Button("Добавить");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DataHandler.addClient(new Client(nameField.getText(), addressField.getText(),
                        phoneNumberField.getText()));
                onShowClientsButtonClick();
            }
        });
        VBox inputBox = new VBox();
        inputBox.getChildren().addAll(nameLabel, nameField, addressLabel, addressField, phoneNumberLabel, phoneNumberField,
                addButton);
        mainPane.getChildren().addAll(inputBox);
    }

    @FXML
    protected void onAddRentAgreementButtonClick() {
        List<Client> clients = DataHandler.getClients();
        List<Machine> machines = DataHandler.getMachines();
        ObservableList<String> clientsObservable = FXCollections.observableList(clients.stream().map(Client::getName)
                .collect(Collectors.toList()));
        ObservableList<String> machinesObservable = FXCollections.observableList(machines.stream().map(Machine::getName)
                .collect(Collectors.toList()));
        mainPane.getChildren().clear();
        Label paymentTypeLabel = new Label("Вид оплаты:");
        TextField paymentTypeField = new TextField();
        Label startDateLabel = new Label("Дата начала договора:");
        DatePicker startDatePicker = new DatePicker();
        Label expireDateLabel = new Label("Дата окончания договора");
        DatePicker expireDatePicker = new DatePicker();
        Label rateLabel = new Label("Тариф:");
        TextField rateField = new TextField();
        Label clientLabel = new Label("Клиент:");
        ComboBox<String> clientBox = new ComboBox<>(clientsObservable);
        clientBox.setValue(clientsObservable.get(0));
        Label machineLabel = new Label("Станок:");
        ComboBox<String> machineBox = new ComboBox<>(machinesObservable);
        machineBox.setValue(machinesObservable.get(0));
        Button addButton = new Button("Добавить");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DataHandler.addRentAgreement(new RentAgreement(paymentTypeField.getText(),
                        Date.from(startDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                        Date.from(expireDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                        Double.parseDouble(rateField.getText()),
                        clients.stream().filter(it -> it.getName().equals(clientBox.getValue())).toList().get(0).getId(),
                        machines.stream().filter(it -> it.getName().equals(machineBox.getValue())).toList().get(0).getId()));
                onShowRentAgreementsButtonClick();
            }
        });
        VBox inputBox = new VBox();
        inputBox.getChildren().addAll(paymentTypeLabel, paymentTypeField, startDateLabel, startDatePicker, expireDateLabel,
                expireDatePicker, rateLabel, rateField, clientLabel, clientBox, machineLabel, machineBox, addButton);
        mainPane.getChildren().add(inputBox);
    }

    @FXML
    protected void onAddPaymentButtonClick() {
        mainPane.getChildren().clear();
        List<RentAgreement> rentAgreements = DataHandler.getRentAgreements();
        ObservableList<Integer> rentAgreementsObservable = FXCollections.observableList(rentAgreements.stream()
                .map(RentAgreement::getId).collect(Collectors.toList()));
        Label dateLabel = new Label("Дата платежа:");
        DatePicker datePicker = new DatePicker();
        Label moneyPaidLabel = new Label("Количество внесенных средств:");
        TextField moneyPaidField = new TextField();
        Label rentAgreementLabel = new Label("Номер договора аренды (ID):");
        ComboBox<Integer> rentAgreementBox = new ComboBox<>(rentAgreementsObservable);
        Button addButton = new Button("Добавить");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DataHandler.addPayment(new Payment(Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                        Integer.parseInt(moneyPaidField.getText()), rentAgreementBox.getValue()));
                onShowPaymentsButtonClick();
            }
        });
        VBox inputBox = new VBox();
        inputBox.getChildren().addAll(dateLabel, datePicker, moneyPaidLabel, moneyPaidField, rentAgreementLabel, rentAgreementBox, addButton);
        mainPane.getChildren().add(inputBox);
    }
}
