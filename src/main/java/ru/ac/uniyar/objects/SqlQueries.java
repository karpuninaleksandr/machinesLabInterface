package ru.ac.uniyar.objects;

import java.util.Arrays;

public enum SqlQueries {
    GetAllBrands(1, "SELECT * FROM BRAND"),
    GetAllMachines(2, "SELECT * FROM MACHINE");

    private final int id;
    private final String query;

    SqlQueries(int id, String query) {
        this.id = id;
        this.query = query;
    }

    public static String getQueryById(int id) {
        return Arrays.stream(SqlQueries.values()).filter(it -> it.id == id).findFirst().get().query;
    }
}
