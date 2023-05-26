package ru.ac.uniyar.application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import ru.ac.uniyar.utils.DataHandler;
import ru.ac.uniyar.objects.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
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
        table.getColumns().addAll(idColumn, brandNameColumn);
        mainPane.getChildren().clear();
        mainPane.getChildren().add(table);
        table.setPrefHeight(700);
        table.setPrefWidth(300);
        table.setLayoutX(650 - table.getPrefWidth() / 2);
        table.setLayoutY(400 - table.getPrefHeight() / 2);
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
        table.setPrefHeight(700);
        table.setPrefWidth(550);
        table.setLayoutX(650 - table.getPrefWidth() / 2);
        table.setLayoutY(400 - table.getPrefHeight() / 2);
    }

    @FXML
    protected void onShowClientsButtonClick() {
        mainPane.getChildren().clear();
        TableView table = makeClientTable(DataHandler.getClients());
        mainPane.getChildren().add(table);
        table.setPrefHeight(700);
        table.setPrefWidth(650);
        table.setLayoutX(650 - table.getPrefWidth() / 2);
        table.setLayoutY(400 - table.getPrefHeight() / 2);
    }

    @FXML
    protected void onShowPaymentsButtonClick() {
        ObservableList<Payment> clients = FXCollections.observableList(DataHandler.getPayments());

        TableColumn<Payment, Integer> idColumn = new TableColumn<>("ID в таблице");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Payment, String> dateColumn = new TableColumn<>("Дата платежа");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        TableColumn<Payment, String> rentAgreementIdColumn = new TableColumn<>("ID Договора");
        rentAgreementIdColumn.setCellValueFactory(new PropertyValueFactory<>("rentAgreementId"));
        TableColumn<Payment, String> moneyPaidColumn = new TableColumn<>("Размер платежа");
        moneyPaidColumn.setCellValueFactory(new PropertyValueFactory<>("moneyPaid"));

        TableView<Payment> table = new TableView<>(clients);
        table.getColumns().addAll(idColumn, dateColumn, rentAgreementIdColumn, moneyPaidColumn);
        mainPane.getChildren().clear();
        mainPane.getChildren().add(table);
        table.setPrefHeight(700);
        table.setPrefWidth(450);
        table.setLayoutX(650 - table.getPrefWidth() / 2);
        table.setLayoutY(400 - table.getPrefHeight() / 2);
    }

    @FXML
    protected void onShowRentAgreementsButtonClick() {
        mainPane.getChildren().clear();
        TableView table = makeRentAgreementTable(DataHandler.getRentAgreements());
        mainPane.getChildren().add(table);
        table.setPrefHeight(700);
        table.setPrefWidth(900);
        table.setLayoutX(650 - table.getPrefWidth() / 2);
        table.setLayoutY(400 - table.getPrefHeight() / 2);
    }

    @FXML
    protected void onAddBrandButtonClick() {
        mainPane.getChildren().clear();
        Label nameLabel = new Label("Наименование бренда:");
        TextField nameField = new TextField();
        Button addButton = new Button("Добавить");
        Label errorLabel = new Label();
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (nameField.getText().isBlank()) {
                    errorLabel.setText("Заполните все поля перед добавлением");
                } else {
                    DataHandler.addBrand(new Brand(nameField.getText()));
                    onShowBrandsButtonClick();
                }
            }
        });
        VBox inputBox = new VBox();
        inputBox.getChildren().addAll(nameLabel, nameField, addButton, errorLabel);
        mainPane.getChildren().addAll(inputBox);
        inputBox.setPrefHeight(700);
        inputBox.setPrefWidth(550);
        inputBox.setLayoutX(650 - inputBox.getPrefWidth() / 2);
        inputBox.setLayoutY(400 - inputBox.getPrefHeight() / 2);
        inputBox.setAlignment(Pos.CENTER);
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
        brandBox.setValue(brandsObservable.stream().findFirst().orElse(""));
        Button addButton = new Button("Добавить");
        Label errorLabel = new Label();
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (nameField.getText().isBlank() || rentPriceField.getText().isBlank() || brandBox.getValue().isBlank()) {
                    errorLabel.setText("Заполните все поля перед добавлением");
                } else {
                    if (!rentPriceField.getText().matches("\\d*")) {
                        errorLabel.setText("Значение поля \"Стоимость аренды:\" должно быть целочисленным");
                    } else {
                        DataHandler.addMachine(new Machine(nameField.getText(), Integer.parseInt(rentPriceField.getText()),
                                brands.stream().filter(it -> it.getName().equals(brandBox.getValue())).toList().get(0).getId()));
                        onShowMachinesButtonClick();
                    }
                }
            }
        });
        VBox inputBox = new VBox();
        inputBox.getChildren().addAll(nameLabel, nameField, rentPriceLabel, rentPriceField, brandIdLabel, brandBox,
                addButton, errorLabel);
        mainPane.getChildren().addAll(inputBox);
        inputBox.setPrefHeight(700);
        inputBox.setPrefWidth(550);
        inputBox.setLayoutX(650 - inputBox.getPrefWidth() / 2);
        inputBox.setLayoutY(400 - inputBox.getPrefHeight() / 2);
        inputBox.setAlignment(Pos.CENTER);
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
        Label errorLabel = new Label();
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (nameField.getText().isBlank() || addressField.getText().isBlank() || phoneNumberField.getText().isBlank()) {
                    errorLabel.setText("Заполните все поля перед добавлением");
                } else {
                    if (!phoneNumberField.getText().matches("\\d*") || phoneNumberField.getText().length() > 11) {
                        errorLabel.setText("Проверьте правильность заполнения поля \"Телефон:\"");
                    } else {
                        DataHandler.addClient(new Client(nameField.getText(), addressField.getText(),
                                phoneNumberField.getText()));
                        onShowClientsButtonClick();
                    }
                }
            }
        });
        VBox inputBox = new VBox();
        inputBox.getChildren().addAll(nameLabel, nameField, addressLabel, addressField, phoneNumberLabel, phoneNumberField,
                addButton);
        mainPane.getChildren().addAll(inputBox);
        inputBox.setPrefHeight(700);
        inputBox.setPrefWidth(550);
        inputBox.setLayoutX(650 - inputBox.getPrefWidth() / 2);
        inputBox.setLayoutY(400 - inputBox.getPrefHeight() / 2);
        inputBox.setAlignment(Pos.CENTER);
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
        ComboBox<String> paymentTypeBox = new ComboBox<>(FXCollections.observableList(Arrays.asList("Ежемесячная", "Оплата на перед")));
        paymentTypeBox.setValue("Ежемесячная");
        Label startDateLabel = new Label("Дата начала договора:");
        DatePicker startDatePicker = new DatePicker();
        startDatePicker.setValue(LocalDate.now());
        Label expireDateLabel = new Label("Дата окончания договора:");
        DatePicker expireDatePicker = new DatePicker();
        expireDatePicker.setValue(LocalDate.now());
        Label rateLabel = new Label("Тариф:");
        TextField rateField = new TextField();
        Label clientLabel = new Label("Клиент:");
        ComboBox<String> clientBox = new ComboBox<>(clientsObservable);
        clientBox.setValue(clientsObservable.stream().findFirst().orElse(""));
        Label machineLabel = new Label("Станок:");
        ComboBox<String> machineBox = new ComboBox<>(machinesObservable);
        machineBox.setValue(machinesObservable.stream().findFirst().orElse(""));
        Button addButton = new Button("Добавить");
        Label errorLabel = new Label();
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (paymentTypeBox.getValue().isBlank() || rateField.getText().isBlank() || clientBox.getValue().isBlank()
                        || machineBox.getValue().isBlank()) {
                    errorLabel.setText("Заполните все поля перед добавлением");
                } else {
                    if (startDatePicker.getValue().isAfter(expireDatePicker.getValue())) {
                        errorLabel.setText("Проверьте правильность заполнения полей \"Дата начала договора:\" и " +
                                "\"Дата окончания договора:\"");
                    } else {
                        if (!rateField.getText().matches("\\d+\\.\\d+") && !rateField.getText().matches("\\d*")) {
                            errorLabel.setText("Поле \"Тариф:\" должно содержать число");
                        } else {
                            DataHandler.addRentAgreement(new RentAgreement(
                                    paymentTypeBox.getValue().equals("Ежемесячная") ? "M" : "F",
                                    Date.from(startDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                                    Date.from(expireDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                                    Double.parseDouble(rateField.getText()),
                                    clients.stream().filter(it -> it.getName().equals(clientBox.getValue())).toList().get(0).getId(),
                                    machines.stream().filter(it -> it.getName().equals(machineBox.getValue())).toList().get(0).getId()));
                            onShowRentAgreementsButtonClick();
                        }
                    }
                }
            }
        });
        VBox inputBox = new VBox();
        inputBox.getChildren().addAll(paymentTypeLabel, paymentTypeBox, startDateLabel, startDatePicker, expireDateLabel,
                expireDatePicker, rateLabel, rateField, clientLabel, clientBox, machineLabel, machineBox, addButton, errorLabel);
        mainPane.getChildren().add(inputBox);
        inputBox.setPrefHeight(700);
        inputBox.setPrefWidth(550);
        inputBox.setLayoutX(650 - inputBox.getPrefWidth() / 2);
        inputBox.setLayoutY(400 - inputBox.getPrefHeight() / 2);
        inputBox.setAlignment(Pos.CENTER);
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
        rentAgreementBox.setValue(rentAgreementsObservable.get(0));
        Button addButton = new Button("Добавить");
        Label errorLabel = new Label();
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (moneyPaidField.getText().isBlank() || rentAgreementBox.getValue() == null) {
                    errorLabel.setText("Заполните все поля перед добавлением");
                } else {
                    if (!moneyPaidField.getText().matches("\\d*")) {
                        errorLabel.setText("Значение поля \"Количество внесенных средств:\" должно быьб целочисленным");
                    } else {
                        DataHandler.addPayment(new Payment(Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                                rentAgreementBox.getValue(), Integer.parseInt(moneyPaidField.getText())));
                        onShowPaymentsButtonClick();
                    }
                }
            }
        });
        VBox inputBox = new VBox();
        inputBox.getChildren().addAll(dateLabel, datePicker, moneyPaidLabel, moneyPaidField, rentAgreementLabel, rentAgreementBox, addButton);
        mainPane.getChildren().add(inputBox);
        inputBox.setPrefHeight(700);
        inputBox.setPrefWidth(550);
        inputBox.setLayoutX(650 - inputBox.getPrefWidth() / 2);
        inputBox.setLayoutY(400 - inputBox.getPrefHeight() / 2);
        inputBox.setAlignment(Pos.CENTER);
    }

    @FXML
    protected void onDeleteBrand() {
        ObservableList<Integer> brandsObservable = FXCollections.observableList(DataHandler.getBrands().stream()
                .map(Brand::getId).collect(Collectors.toList()));
        mainPane.getChildren().clear();
        Label brandIdLabel = new Label("ID бренда:");
        ComboBox<Integer> brandBox = new ComboBox<>(brandsObservable);
        brandBox.setValue(brandsObservable.stream().findFirst().orElse(null));
        Button deleteButton = new Button("Удалить");
        Label errorLabel = new Label();
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (DataHandler.deleteBrand(brandBox.getValue())) {
                    onShowBrandsButtonClick();
                } else {
                    errorLabel.setText("Бренд с ID = " + brandBox.getValue() + " не может быть удален");
                }
            }
        });
        VBox deleteBox = new VBox();
        deleteBox.getChildren().addAll(brandIdLabel, brandBox, deleteButton, errorLabel);
        mainPane.getChildren().add(deleteBox);
        deleteBox.setPrefHeight(200);
        deleteBox.setPrefWidth(150);
        deleteBox.setLayoutX(650 - deleteBox.getPrefWidth() / 2);
        deleteBox.setLayoutY(400 - deleteBox.getPrefHeight() / 2);
        deleteBox.setAlignment(Pos.CENTER);
    }

    @FXML
    protected void onDeleteMachine() {
        ObservableList<Integer> machinesObservable = FXCollections.observableList(DataHandler.getMachines().stream()
                .map(Machine::getId).collect(Collectors.toList()));
        mainPane.getChildren().clear();
        Label machineIdLabel = new Label("ID станка:");
        ComboBox<Integer> machineBox = new ComboBox<>(machinesObservable);
        machineBox.setValue(machinesObservable.stream().findFirst().orElse(null));
        Button deleteButton = new Button("Удалить");
        Label errorLabel = new Label();
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (DataHandler.deleteMachine(machineBox.getValue())) {
                    onShowMachinesButtonClick();
                } else {
                    errorLabel.setText("Станок с ID = " + machineBox.getValue() + " не может быть удален");
                }
            }
        });
        VBox deleteBox = new VBox();
        deleteBox.getChildren().addAll(machineIdLabel, machineBox, deleteButton, errorLabel);
        mainPane.getChildren().add(deleteBox);
        deleteBox.setPrefHeight(200);
        deleteBox.setPrefWidth(150);
        deleteBox.setLayoutX(650 - deleteBox.getPrefWidth() / 2);
        deleteBox.setLayoutY(400 - deleteBox.getPrefHeight() / 2);
        deleteBox.setAlignment(Pos.CENTER);
    }

    @FXML
    protected void onDeleteClient() {
        ObservableList<Integer> clientsObservable = FXCollections.observableList(DataHandler.getClients().stream()
                .map(Client::getId).collect(Collectors.toList()));
        mainPane.getChildren().clear();
        Label clientIdLabel = new Label("ID клиента:");
        ComboBox<Integer> clientBox = new ComboBox<>(clientsObservable);
        clientBox.setValue(clientsObservable.stream().findFirst().orElse(null));
        Button deleteButton = new Button("Удалить");
        Label errorLabel = new Label();
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (DataHandler.deleteClient(clientBox.getValue())) {
                    onShowClientsButtonClick();
                } else {
                    errorLabel.setText("Клиент с ID = " + clientBox.getValue() + " не может быть удален");
                }
            }
        });
        VBox deleteBox = new VBox();
        deleteBox.getChildren().addAll(clientIdLabel, clientBox, deleteButton, errorLabel);
        mainPane.getChildren().add(deleteBox);
        deleteBox.setPrefHeight(200);
        deleteBox.setPrefWidth(150);
        deleteBox.setLayoutX(650 - deleteBox.getPrefWidth() / 2);
        deleteBox.setLayoutY(400 - deleteBox.getPrefHeight() / 2);
        deleteBox.setAlignment(Pos.CENTER);
    }

    @FXML
    protected void onDeleteRentAgreement() {
        ObservableList<Integer> rentAgreementsObservable = FXCollections.observableList(DataHandler.getRentAgreements()
                .stream().map(RentAgreement::getId).collect(Collectors.toList()));
        mainPane.getChildren().clear();
        Label rentAgreementIdLabel = new Label("ID договора:");
        ComboBox<Integer> rentAgreementBox = new ComboBox<>(rentAgreementsObservable);
        rentAgreementBox.setValue(rentAgreementsObservable.stream().findFirst().orElse(null));
        Button deleteButton = new Button("Удалить");
        Label errorLabel = new Label();
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (DataHandler.deleteRentAgreement(rentAgreementBox.getValue())) {
                    onShowRentAgreementsButtonClick();
                } else {
                    errorLabel.setText("Договор с ID = " + rentAgreementBox.getValue() + " не может быть удален");
                }
            }
        });
        VBox deleteBox = new VBox();
        deleteBox.getChildren().addAll(rentAgreementIdLabel, rentAgreementBox, deleteButton, errorLabel);
        mainPane.getChildren().add(deleteBox);
        deleteBox.setPrefHeight(200);
        deleteBox.setPrefWidth(150);
        deleteBox.setLayoutX(650 - deleteBox.getPrefWidth() / 2);
        deleteBox.setLayoutY(400 - deleteBox.getPrefHeight() / 2);
        deleteBox.setAlignment(Pos.CENTER);
    }

    @FXML
    protected void onDeletePayment() {
        ObservableList<Integer> paymentsObservable = FXCollections.observableList(DataHandler.getPayments().stream()
                .map(Payment::getId).collect(Collectors.toList()));
        mainPane.getChildren().clear();
        Label paymentIdLabel = new Label("ID платежа:");
        ComboBox<Integer> paymentBox = new ComboBox<>(paymentsObservable);
        paymentBox.setValue(paymentsObservable.stream().findFirst().orElse(null));
        Button deleteButton = new Button("Удалить");
        Label errorLabel = new Label();
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (DataHandler.deletePayment(paymentBox.getValue())) {
                    onShowPaymentsButtonClick();
                } else {
                    errorLabel.setText("Платеж с ID = " + paymentBox.getValue() + " не может быть удален");
                }
            }
        });
        VBox deleteBox = new VBox();
        deleteBox.getChildren().addAll(paymentIdLabel, paymentBox, deleteButton, errorLabel);
        mainPane.getChildren().add(deleteBox);
        deleteBox.setPrefHeight(200);
        deleteBox.setPrefWidth(150);
        deleteBox.setLayoutX(650 - deleteBox.getPrefWidth() / 2);
        deleteBox.setLayoutY(400 - deleteBox.getPrefHeight() / 2);
        deleteBox.setAlignment(Pos.CENTER);
    }

    @FXML
    protected void onShowClientsViaRegexButtonClick() {
        mainPane.getChildren().clear();
        Label regexNameLabel = new Label("Имя содержит:");
        TextField regexNameField = new TextField();
        Label regexAddressLabel = new Label("Адрес содержит:");
        TextField regexAddressField = new TextField();
        Label regexPhoneNumberLabel = new Label("Телефон содержит:");
        TextField regexPhoneNumberField = new TextField();
        Button findButton = new Button("Найти");
        Label errorLabel = new Label();
        findButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                List<Client> clients = DataHandler.searchClient("%".concat(regexNameField.getText()).concat("%"),
                        "%".concat(regexAddressField.getText()).concat("%"),
                        "%".concat(regexPhoneNumberLabel.getText()).concat("%"));
                if (clients.isEmpty()) errorLabel.setText("Нет клиентов, удволетворяющих Вашему запросу");
                else {
                    VBox inputBox = (VBox) mainPane.getChildren().get(0);
                    inputBox.getChildren().clear();
                    TableView table = makeClientTable(clients);
                    inputBox.getChildren().add(table);
                    inputBox.setPrefHeight(700);
                    inputBox.setPrefWidth(650);
                    inputBox.setLayoutX(650 - inputBox.getPrefWidth() / 2);
                    inputBox.setLayoutY(400 - inputBox.getPrefHeight() / 2);
                }
            }
        });
        VBox inputBox = new VBox();
        inputBox.getChildren().addAll(regexNameLabel, regexNameField, regexAddressLabel, regexAddressField,
                regexPhoneNumberLabel, regexPhoneNumberField, findButton, errorLabel);
        mainPane.getChildren().add(inputBox);
        inputBox.setPrefHeight(700);
        inputBox.setPrefWidth(550);
        inputBox.setLayoutX(650 - inputBox.getPrefWidth() / 2);
        inputBox.setLayoutY(400 - inputBox.getPrefHeight() / 2);
        inputBox.setAlignment(Pos.CENTER);
    }

    private static TableView<Client> makeClientTable(List<Client> clients) {
        ObservableList<Client> clientsObservable = FXCollections.observableList(clients);

        TableColumn<Client, Integer> idColumn = new TableColumn<>("ID в таблице");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Client, String> nameColumn = new TableColumn<>("Имя");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Client, String> addressColumn = new TableColumn<>("Адрес");
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        TableColumn<Client, String> phoneNumberColumn = new TableColumn<>("Телефон");
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        TableView<Client> table = new TableView<>(clientsObservable);
        table.getColumns().addAll(idColumn, nameColumn, addressColumn, phoneNumberColumn);
        return table;
    }

    private static TableView<RentAgreement> makeRentAgreementTable(List<RentAgreement> rentAgreements) {
        ObservableList<RentAgreement> rentAgreementsObservable = FXCollections
                .observableList(rentAgreements);

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

        TableView<RentAgreement> table = new TableView<>(rentAgreementsObservable);
        table.getColumns().addAll(idColumn, paymentTypeColumn, startDateColumn, expireDateColumn, rateColumn,
                clientIdColumn, machineIdColumn);
        return table;
    }

    @FXML
    protected void onShowRentAgreementsBetweenDatesButtonClick() {
        mainPane.getChildren().clear();
        Label startDateLabel = new Label("От:");
        DatePicker startDatePicker = new DatePicker();
        startDatePicker.setValue(LocalDate.now());
        Label endDateLabel = new Label("До:");
        DatePicker endDatePicker = new DatePicker();
        endDatePicker.setValue(LocalDate.now());
        Button findButton = new Button("Найти");
        Label errorLabel = new Label();
        findButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (startDatePicker.getValue().isAfter(endDatePicker.getValue())) {
                    errorLabel.setText("Проверьте правильность заполнения полей \"От:\" и \"До:\"");
                } else {
                    List<RentAgreement> rentAgreements = DataHandler.searchRentAgreement(
                            Date.from(startDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                            Date.from(endDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                    if (rentAgreements.isEmpty()) errorLabel.setText("Нет договоров, заключенных за введенный период");
                    else {
                        VBox inputBox = (VBox) mainPane.getChildren().get(0);
                        inputBox.getChildren().clear();
                        TableView<RentAgreement> table = makeRentAgreementTable(rentAgreements);
                        inputBox.getChildren().add(table);
                        inputBox.setPrefHeight(700);
                        inputBox.setPrefWidth(900);
                        inputBox.setLayoutX(650 - inputBox.getPrefWidth() / 2);
                        inputBox.setLayoutY(400 - inputBox.getPrefHeight() / 2);
                    }
                }
            }
        });
        VBox inputBox = new VBox();
        inputBox.getChildren().addAll(startDateLabel, startDatePicker, endDateLabel, endDatePicker, findButton,
                errorLabel);
        mainPane.getChildren().add(inputBox);
        inputBox.setPrefHeight(300);
        inputBox.setPrefWidth(350);
        inputBox.setLayoutX(650 - inputBox.getPrefWidth() / 2);
        inputBox.setLayoutY(400 - inputBox.getPrefHeight() / 2);
        inputBox.setAlignment(Pos.CENTER);
    }

    @FXML
    protected void onEditRateButtonClick() {
        mainPane.getChildren().clear();
        List<RentAgreement> rentAgreements = DataHandler.getRentAgreements();
        ObservableList<Integer> rentAgreementIdObservable = FXCollections.observableList(rentAgreements.stream()
                .map(RentAgreement::getId).collect(Collectors.toList()));
        Label rentAgreementIdLabel = new Label("ID договора:");
        ComboBox<Integer> rentAgreementIdBox = new ComboBox<>(rentAgreementIdObservable);
        rentAgreementIdBox.setValue(rentAgreementIdObservable.stream().findFirst().orElse(null));
        Label rateLabel = new Label("Новый тариф:");
        TextField rateField = new TextField();
        Button editButton = new Button("Изменить");
        Label errorLabel = new Label();
        editButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (rentAgreementIdBox.getValue() == null || rateField.getText().isBlank())
                    errorLabel.setText("Заполните все поля перед изменением");
                else {
                    if (!rateField.getText().matches("\\d+\\.\\d+") && !rateField.getText().matches("\\d*"))
                        errorLabel.setText("Поле \"Новый тариф:\" должно содержать число");
                    else {
                        if (rentAgreements.stream().filter(it -> it.getId() == rentAgreementIdBox.getValue()).toList()
                                .get(0).getRate() == Double.parseDouble(rateField.getText()))
                            errorLabel.setText("Значение поля \"Новый тариф:\" должно отличаться от текущего тарифа договора");
                        else {
                            DataHandler.editRentAgreementRate(rentAgreementIdBox.getValue(), Double.parseDouble(rateField.getText()));
                            onShowRentAgreementsButtonClick();
                        }
                    }
                }
            }
        });
        VBox inputBox = new VBox();
        inputBox.getChildren().addAll(rentAgreementIdLabel, rentAgreementIdBox, rateLabel, rateField, editButton, errorLabel);
        mainPane.getChildren().add(inputBox);
        inputBox.setPrefHeight(400);
        inputBox.setPrefWidth(550);
        inputBox.setLayoutX(650 - inputBox.getPrefWidth() / 2);
        inputBox.setLayoutY(400 - inputBox.getPrefHeight() / 2);
        inputBox.setAlignment(Pos.CENTER);
    }

    @FXML
    protected void onEditEndDateButtonClick() {
        mainPane.getChildren().clear();
        List<RentAgreement> rentAgreements = DataHandler.getRentAgreements();
        ObservableList<Integer> rentAgreementIdObservable = FXCollections.observableList(rentAgreements.stream()
                .map(RentAgreement::getId).collect(Collectors.toList()));
        Label rentAgreementIdLabel = new Label("ID договора:");
        ComboBox<Integer> rentAgreementIdBox = new ComboBox<>(rentAgreementIdObservable);
        rentAgreementIdBox.setValue(rentAgreementIdObservable.stream().findFirst().orElse(null));
        Label endDateLabel = new Label("Новая дата истечения договора:");
        DatePicker endDatePicker = new DatePicker();
        endDatePicker.setValue(LocalDate.now());
        Button editButton = new Button("Продлить");
        Label errorLabel = new Label();
        editButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (rentAgreementIdBox.getValue() == null)
                    errorLabel.setText("Заполните все поля перед изменением");
                else {
                    if (rentAgreements.stream().filter(it -> it.getId() == rentAgreementIdBox.getValue()).toList().get(0)
                            .getExpireDate().after(Date.from(endDatePicker.getValue().atStartOfDay(ZoneId.systemDefault())
                                    .toInstant())))
                        errorLabel.setText("Проверьте правильность заполнения поля \"Новая дата истечения договора:\"");
                    else {
                        DataHandler.editRentAgreementExpireDate(rentAgreementIdBox.getValue(),
                                Date.from(endDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                        onShowRentAgreementsButtonClick();
                    }
                }
            }
        });
        VBox inputBox = new VBox();
        inputBox.getChildren().addAll(rentAgreementIdLabel, rentAgreementIdBox, endDateLabel, endDatePicker, editButton,
                errorLabel);
        mainPane.getChildren().add(inputBox);
        inputBox.setPrefHeight(400);
        inputBox.setPrefWidth(550);
        inputBox.setLayoutX(650 - inputBox.getPrefWidth() / 2);
        inputBox.setLayoutY(400 - inputBox.getPrefHeight() / 2);
        inputBox.setAlignment(Pos.CENTER);
    }

    @FXML
    protected void onGetForwardPayersButtonClick() {
        mainPane.getChildren().clear();
        Label dateLabel = new Label("Дата:");
        DatePicker datePicker = new DatePicker();
        datePicker.setValue(LocalDate.now());
        Button getButton = new Button("Найти");
        Label errorLabel = new Label();
        getButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ObservableList<ClientWithDebt> clients = FXCollections.observableList(DataHandler.getForwardPayers(Date
                        .from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())));

                if (clients.isEmpty())
                    errorLabel.setText("Нет клиентов, удовлетворяющих Вашему запросу");
                else {
                    TableColumn<ClientWithDebt, Integer> idColumn = new TableColumn<>("ID клиента в таблице");
                    idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
                    TableColumn<ClientWithDebt, String> nameColumn = new TableColumn<>("Имя");
                    nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
                    TableColumn<ClientWithDebt, String> rentAgreementIdColumn = new TableColumn<>("ID договора в таблице");
                    rentAgreementIdColumn.setCellValueFactory(new PropertyValueFactory<>("rentAgreementId"));
                    TableColumn<ClientWithDebt, String> debtColumn = new TableColumn<>("Количество средств, уплаченных наперед");
                    debtColumn.setCellValueFactory(new PropertyValueFactory<>("debt"));

                    TableView<ClientWithDebt> table = new TableView<>(clients);
                    table.getColumns().addAll(idColumn, nameColumn, rentAgreementIdColumn, debtColumn);

                    VBox inputBox = (VBox) mainPane.getChildren().get(0);
                    inputBox.getChildren().clear();
                    inputBox.getChildren().add(table);
                    inputBox.setPrefHeight(700);
                    inputBox.setPrefWidth(1000);
                    inputBox.setLayoutX(650 - inputBox.getPrefWidth() / 2);
                    inputBox.setLayoutY(400 - inputBox.getPrefHeight() / 2);
                }
            }
        });
        VBox inputBox = new VBox();
        inputBox.getChildren().addAll(dateLabel, datePicker, getButton, errorLabel);
        mainPane.getChildren().add(inputBox);
        inputBox.setPrefHeight(400);
        inputBox.setPrefWidth(450);
        inputBox.setLayoutX(650 - inputBox.getPrefWidth() / 2);
        inputBox.setLayoutY(400 - inputBox.getPrefHeight() / 2);
        inputBox.setAlignment(Pos.CENTER);
    }
    @FXML
    protected void onGetDebtOwnersButtonClick() {
        mainPane.getChildren().clear();
        Label dateLabel = new Label("Дата:");
        DatePicker datePicker = new DatePicker();
        datePicker.setValue(LocalDate.now());
        Button getButton = new Button("Найти");
        Label errorLabel = new Label();
        getButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ObservableList<ClientWithDebt> clients = FXCollections.observableList(DataHandler.getDebtOwners(Date
                        .from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())));

                if (clients.isEmpty())
                    errorLabel.setText("Нет клиентов, удовлетворяющих Вашему запросу");
                else {
                    TableColumn<ClientWithDebt, Integer> idColumn = new TableColumn<>("ID клиента в таблице");
                    idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
                    TableColumn<ClientWithDebt, String> nameColumn = new TableColumn<>("Имя");
                    nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
                    TableColumn<ClientWithDebt, String> rentAgreementIdColumn = new TableColumn<>("ID договора в таблице");
                    rentAgreementIdColumn.setCellValueFactory(new PropertyValueFactory<>("rentAgreementId"));
                    TableColumn<ClientWithDebt, String> debtColumn = new TableColumn<>("Долг");
                    debtColumn.setCellValueFactory(new PropertyValueFactory<>("debt"));

                    TableView<ClientWithDebt> table = new TableView<>(clients);
                    table.getColumns().addAll(idColumn, nameColumn, rentAgreementIdColumn, debtColumn);

                    VBox inputBox = (VBox) mainPane.getChildren().get(0);
                    inputBox.getChildren().clear();
                    inputBox.getChildren().add(table);
                    inputBox.setPrefHeight(700);
                    inputBox.setPrefWidth(1000);
                    inputBox.setLayoutX(650 - inputBox.getPrefWidth() / 2);
                    inputBox.setLayoutY(400 - inputBox.getPrefHeight() / 2);
                }
            }
        });
        VBox inputBox = new VBox();
        inputBox.getChildren().addAll(dateLabel, datePicker, getButton, errorLabel);
        mainPane.getChildren().add(inputBox);
        inputBox.setPrefHeight(400);
        inputBox.setPrefWidth(450);
        inputBox.setLayoutX(650 - inputBox.getPrefWidth() / 2);
        inputBox.setLayoutY(400 - inputBox.getPrefHeight() / 2);
        inputBox.setAlignment(Pos.CENTER);
    }

    @FXML
    protected void onGetClientDebtsButtonClick() {
        mainPane.getChildren().clear();
        ObservableList<Integer> clients = FXCollections.observableList(DataHandler.getClients().stream().map(Client::getId)
                .collect(Collectors.toList()));
        Label clientLabel = new Label("ID клиента:");
        ComboBox<Integer> clientBox = new ComboBox<>(clients);
        Label dateLabel = new Label("Дата:");
        DatePicker datePicker = new DatePicker();
        datePicker.setValue(LocalDate.now());
        clientBox.setValue(clients.stream().findFirst().orElse(null));
        Button getButton = new Button("Найти задолженности");
        Label errorLabel = new Label();
        getButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (clientBox.getValue() == null) errorLabel.setText("Заполните все поля перед вызовом функции");
                else {
                    ObservableList<ClientWithDebt> clients = FXCollections.observableList(DataHandler.getClientDebts(
                            clientBox.getValue(), Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault())
                                    .toInstant())));
                    if (clients.isEmpty())
                        errorLabel.setText("У клиента нет задолженностей на выбранное время");
                    else {
                        TableColumn<ClientWithDebt, Integer> idColumn = new TableColumn<>("ID клиента в таблице");
                        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
                        TableColumn<ClientWithDebt, String> nameColumn = new TableColumn<>("Имя");
                        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
                        TableColumn<ClientWithDebt, String> rentAgreementIdColumn = new TableColumn<>("ID договора в таблице");
                        rentAgreementIdColumn.setCellValueFactory(new PropertyValueFactory<>("rentAgreementId"));
                        TableColumn<ClientWithDebt, String> debtColumn = new TableColumn<>("Долг");
                        debtColumn.setCellValueFactory(new PropertyValueFactory<>("debt"));

                        TableView<ClientWithDebt> table = new TableView<>(clients);
                        table.getColumns().addAll(idColumn, nameColumn, rentAgreementIdColumn, debtColumn);

                        VBox inputBox = (VBox) mainPane.getChildren().get(0);
                        inputBox.getChildren().clear();
                        inputBox.getChildren().add(table);
                        inputBox.setPrefHeight(700);
                        inputBox.setPrefWidth(1000);
                        inputBox.setLayoutX(650 - inputBox.getPrefWidth() / 2);
                        inputBox.setLayoutY(400 - inputBox.getPrefHeight() / 2);
                    }
                }
            }
        });
        VBox inputBox = new VBox();
        inputBox.getChildren().addAll(clientLabel, clientBox, dateLabel, datePicker, getButton, errorLabel);
        mainPane.getChildren().add(inputBox);
        inputBox.setPrefHeight(400);
        inputBox.setPrefWidth(450);
        inputBox.setLayoutX(650 - inputBox.getPrefWidth() / 2);
        inputBox.setLayoutY(400 - inputBox.getPrefHeight() / 2);
        inputBox.setAlignment(Pos.CENTER);
    }

    @FXML
    protected void onAboutAppButtonClick() {
        mainPane.getChildren().clear();
        VBox outBox = new VBox();
        outBox.getChildren().addAll(new Label("                               О приложении"),
                new Label("   Приложение - реализация лабораторной работы по курсу"),
                new Label("Система управления базами данных Oracle."),
                new Label("Все управление в приложении реализовано через меню в верхней"),
                new Label("его части, в центральной будет отрисовываться выбранный"),
                new Label("пользователем в меню контент."),
                new Label("Все поля имеют свои правила заполнения, все они интуитивно понятны, а если"),
                new Label("формат входящих данных будет неправилен, появится контекстное сообщение"),
                new Label("о неправильном вводе."),
                new Label("Важная ремарка ввода нецелочисленных значений: при вводе такого числа"),
                new Label("разделителем между целой и дробной частью следует использовать точку, а не запятую."));
        mainPane.getChildren().add(outBox);
        outBox.setPrefHeight(700);
        outBox.setPrefWidth(550);
        outBox.setLayoutX(650 - outBox.getPrefWidth() / 2);
        outBox.setLayoutY(450 - outBox.getPrefHeight() / 2);
    }

    @FXML
    protected void onAboutProgrammerButtonClick() {
        mainPane.getChildren().clear();
        VBox outBox = new VBox();
        outBox.getChildren().addAll(new Label("                               О разработчике"),
                new Label("   Разработчиком данного приложения является студент ЯрГУ имени П.Г. Демидова"),
                new Label("группы ИВТ-32 Карпунин Александр Олегович."));
        mainPane.getChildren().add(outBox);
        outBox.setPrefHeight(700);
        outBox.setPrefWidth(550);
        outBox.setLayoutX(650 - outBox.getPrefWidth() / 2);
        outBox.setLayoutY(450 - outBox.getPrefHeight() / 2);
    }
}
