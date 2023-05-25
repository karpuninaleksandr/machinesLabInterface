package ru.ac.uniyar.utils;

import java.util.Arrays;

public enum SqlQueries {
    GetAllBrands(1, "SELECT * FROM BRAND ORDER BY ID"),
    GetAllMachines(2, "SELECT * FROM MACHINE ORDER BY ID"),
    GetAllClients(3, "SELECT * FROM CLIENT ORDER BY ID"),
    GetAllPayments(4, "SELECT * FROM PAYMENT ORDER BY ID"),
    GetAllRentAgreements(5, "SELECT * FROM RENTAGREEMENT ORDER BY ID"),
    AddBrand(6, "INSERT INTO BRAND (NAME) VALUES (?)"),
    AddMachine(7, "INSERT INTO MACHINE (NAME, RENTPRICE, BRANDID) VALUES (?, ?, ?)"),
    AddClient(8, "INSERT INTO CLIENT (NAME, ADDRESS, PHONENUMBER) VALUES (?, ?, ?)"),
    AddRentAgreement(9, "INSERT INTO RENTAGREEMENT (PAYMENTTYPE, STARTDATE, EXPIREDATE, RATE, CLIENTID, MACHINEID)" +
            " VALUES (?, ?, ?, ?, ?, ?)"),
    AddPayment(10, "INSERT INTO PAYMENT (\"date\", RENTAGREEMENTID, MONEYPAID) VALUES (?, ?, ?)"),
    DeleteBrand(11, "DELETE FROM BRAND WHERE ID = ?"),
    DeleteMachine(12, "DELETE FROM MACHINE WHERE ID = ?"),
    DeleteClient(13, "DELETE FROM CLIENT WHERE ID = ?"),
    DeleteRentAgreement(14, "DELETE FROM RENTAGREEMENT WHERE ID = ?"),
    DeletePayment(15, "DELETE FROM PAYMENT WHERE ID = ?"),
    SearchClient(16, "SELECT * FROM CLIENT WHERE NAME LIKE ? AND ADDRESS LIKE ? AND PHONENUMBER LIKE ?"),
    SearchRentAgreement(17, "SELECT * FROM RENTAGREEMENT WHERE STARTDATE BETWEEN ? AND ?"),
    EditRentAgreementRate(18, "UPDATE RENTAGREEMENT SET RATE = ? WHERE ID = ?");

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
