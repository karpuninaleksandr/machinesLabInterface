package ru.ac.uniyar.application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import ru.ac.uniyar.objects.Brand;
import ru.ac.uniyar.objects.Client;
import ru.ac.uniyar.objects.DataExtractor;
import ru.ac.uniyar.objects.Machine;


public class MainPageController {
    @FXML
    private AnchorPane mainPane;

    @FXML
    protected void onShowBrandsButtonClick(ActionEvent actionEvent) {
        ObservableList<Brand> brands = FXCollections.observableList(DataExtractor.getBrands());

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
        ObservableList<Machine> machines = FXCollections.observableList(DataExtractor.getMachines());

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
        ObservableList<Client> clients = FXCollections.observableList(DataExtractor.getClients());

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
}