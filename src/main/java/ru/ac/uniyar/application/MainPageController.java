package ru.ac.uniyar.application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ru.ac.uniyar.objects.Brand;
import ru.ac.uniyar.objects.DataExtractor;
import ru.ac.uniyar.objects.Machine;

import java.util.stream.Collectors;

public class MainPageController {
    @FXML
    private Label brandsText;

    @FXML
    private Label machinesText;

    @FXML
    protected void onShowBrandsButtonClick() {
        brandsText.setText(DataExtractor.getBrands().stream().map(Brand::name).toList().toString());
    }

    @FXML
    protected void onShowMachinesButtonClick() {
        machinesText.setText(DataExtractor.getMachines().stream().map(Machine::name).toList().toString());
    }
}