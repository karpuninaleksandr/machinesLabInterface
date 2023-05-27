package ru.ac.uniyar.utils;

import java.util.Arrays;

public enum SqlQueries {
    GetAllBrands(1, "SELECT * FROM BRAND ORDER BY ID"),
    GetAllMachines(2, "SELECT * FROM MACHINE ORDER BY ID"),
    GetAllClients(3, "SELECT * FROM CLIENT ORDER BY ID"),
    GetAllPayments(4, "SELECT * FROM PAYMENT ORDER BY ID"),
    GetAllRentAgreements(5, "SELECT * FROM RENTAGREEMENT"),
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
    EditRentAgreementRate(18, "UPDATE RENTAGREEMENT SET RATE = ? WHERE ID = ?"),
    EditRentAgreementExpireDate(19, "UPDATE RENTAGREEMENT SET EXPIREDATE = ? WHERE ID = ?"),
    GetForwardPayers(20, "SELECT * FROM (SELECT CLIENT.ID AS ID, CLIENT.NAME, RENTAGREEMENT.ID AS RENTAGREEMENTID," +
            " CASE WHEN SUM(PAYMENT.MONEYPAID) IS NULL THEN MAX(CEIL(MONTHS_BETWEEN(LEAST(?, RENTAGREEMENT.EXPIREDATE), " +
            "RENTAGREEMENT.STARTDATE)) * MACHINE.RENTPRICE * RENTAGREEMENT.RATE) ELSE " +
            "MAX(CEIL(MONTHS_BETWEEN(LEAST(?, RENTAGREEMENT.EXPIREDATE), RENTAGREEMENT.STARTDATE)) * MACHINE.RENTPRICE" +
            " * RENTAGREEMENT.RATE) - SUM(PAYMENT.MONEYPAID) END AS DEBT FROM RENTAGREEMENT INNER JOIN CLIENT ON" +
            " RENTAGREEMENT.CLIENTID = CLIENT.ID INNER JOIN MACHINE ON RENTAGREEMENT.MACHINEID = MACHINE.ID LEFT JOIN " +
            "PAYMENT ON RENTAGREEMENT.ID = PAYMENT.RENTAGREEMENTID AND PAYMENT.\"date\" < ? WHERE RENTAGREEMENT.STARTDATE" +
            " < ? GROUP BY CLIENT.ID, CLIENT.NAME, RENTAGREEMENT.ID) WHERE DEBT < 0 ORDER BY DEBT"),
    GetDebtOwners(21, "SELECT * FROM (SELECT CLIENT.ID AS ID, CLIENT.NAME, RENTAGREEMENT.ID AS RENTAGREEMENTID," +
            " CASE WHEN SUM(PAYMENT.MONEYPAID) IS NULL THEN MAX(CEIL(MONTHS_BETWEEN(LEAST(?, RENTAGREEMENT.EXPIREDATE), " +
            "RENTAGREEMENT.STARTDATE)) * MACHINE.RENTPRICE * RENTAGREEMENT.RATE) ELSE " +
            "MAX(CEIL(MONTHS_BETWEEN(LEAST(?, RENTAGREEMENT.EXPIREDATE), RENTAGREEMENT.STARTDATE)) * MACHINE.RENTPRICE" +
            " * RENTAGREEMENT.RATE) - SUM(PAYMENT.MONEYPAID) END AS DEBT FROM RENTAGREEMENT INNER JOIN CLIENT ON" +
            " RENTAGREEMENT.CLIENTID = CLIENT.ID INNER JOIN MACHINE ON RENTAGREEMENT.MACHINEID = MACHINE.ID LEFT JOIN " +
            "PAYMENT ON RENTAGREEMENT.ID = PAYMENT.RENTAGREEMENTID AND PAYMENT.\"date\" < ? WHERE RENTAGREEMENT.STARTDATE" +
            " < ? GROUP BY CLIENT.ID, CLIENT.NAME, RENTAGREEMENT.ID) WHERE DEBT > 0 ORDER BY DEBT"),
    GetClientDebts(22, "SELECT * FROM (SELECT CLIENT.ID AS ID, CLIENT.NAME, RENTAGREEMENT.ID AS RENTAGREEMENTID," +
            "CASE WHEN SUM(PAYMENT.MONEYPAID) IS NULL THEN MAX(CEIL(MONTHS_BETWEEN(LEAST(?, RENTAGREEMENT.EXPIREDATE)," +
            "RENTAGREEMENT.STARTDATE)) * MACHINE.RENTPRICE * RENTAGREEMENT.RATE) ELSE MAX(CEIL(MONTHS_BETWEEN(LEAST(?, " +
            "RENTAGREEMENT.EXPIREDATE), RENTAGREEMENT.STARTDATE)) * MACHINE.RENTPRICE * RENTAGREEMENT.RATE) - " +
            "SUM(PAYMENT.MONEYPAID) END AS DEBT FROM RENTAGREEMENT INNER JOIN CLIENT ON RENTAGREEMENT.CLIENTID = CLIENT.ID " +
            "INNER JOIN MACHINE ON RENTAGREEMENT.MACHINEID = MACHINE.ID LEFT JOIN PAYMENT ON RENTAGREEMENT.ID = " +
            "PAYMENT.RENTAGREEMENTID AND PAYMENT.\"date\" < ? WHERE RENTAGREEMENT.STARTDATE < ? AND CLIENT.ID = ? " +
            "GROUP BY CLIENT.ID, CLIENT.NAME, RENTAGREEMENT.ID) WHERE DEBT > 0 ORDER BY DEBT DESC");

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
