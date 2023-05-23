package ru.ac.uniyar.utils;

import java.util.Arrays;

public enum SqlQueries {
    GetAllBrands(1, "SELECT * FROM BRAND"),
    GetAllMachines(2, "SELECT * FROM MACHINE"),
    GetAllClients(3, "SELECT * FROM CLIENT"),
    GetAllPayments(4, "SELECT * FROM PAYMENT"),
    GetAllRentAgreements(5, "SELECT * FROM RENTAGREEMENT"),
    AddBrand(6, "INSERT INTO BRAND (NAME) VALUES (?)"),
    AddMachine(7, "INSERT INTO MACHINE (NAME, RENTPRICE, BRANDID) VALUES (?, ?, ?)"),
    AddClient(8, "INSERT INTO CLIENT (NAME, ADDRESS, PHONENUMBER) VALUES (?, ?, ?)"),
    AddRentAgreement(9, "INSERT INTO RENTAGREEMENT (PAYMENTTYPE, STARTDATE, EXPIREDATE, RATE, CLIENTID, MACHINEID)" +
            " VALUES (?, ?, ?, ?, ?, ?)"),
    AddPayment(10, "INSERT INTO PAYMENT (DATE, RENTAGREEMENTID, MONEYPAID) VALUES (?, ?, ?)");

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
