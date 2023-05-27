package ru.ac.uniyar.utils;

import ru.ac.uniyar.objects.*;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.stream.Collectors;


public class DataHandler {
    private static Connection connection;

    public static void init() {
        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM",
                    "sKARPer2002");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Brand> getBrands() {
        return getData(1).stream().map(it -> (Brand) it).collect(Collectors.toList());
    }

    public static List<Machine> getMachines() {
        return getData(2).stream().map(it -> (Machine) it).collect(Collectors.toList());
    }

    public static List<Client> getClients() {
        return getData(3).stream().map(it -> (Client) it).collect(Collectors.toList());
    }

    public static List<Payment> getPayments() {
        return getData(4).stream().map(it -> (Payment) it).collect(Collectors.toList());
    }

    public static List<RentAgreement> getRentAgreements() {
        return getData(5).stream().map(it -> (RentAgreement) it).collect(Collectors.toList());
    }

    private static List<Entity> getData(int queryId) {
        ArrayList<Entity> result = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(SqlQueries.getQueryById(queryId));
            ResultSet rs = ps.executeQuery();
            switch (queryId) {
                case 1 -> {
                    while (rs.next()) {
                        result.add(new Brand(rs.getInt(1), rs.getString(2)));
                    }
                }
                case 2 -> {
                    while (rs.next()) {
                        result.add(new Machine(rs.getInt(1), rs.getString(2), rs.getInt(3),
                                rs.getInt(4)));
                    }
                }
                case 3 -> {
                    while (rs.next()) {
                        result.add(new Client(rs.getInt(1), rs.getString(2), rs.getString(3),
                                rs.getString(4)));
                    }
                }
                case 4 -> {
                    while (rs.next()) {
                        result.add(new Payment(rs.getInt(1), rs.getDate(2), rs.getInt(3),
                                rs.getInt(4)));
                    }
                }
                case 5 -> {
                    while (rs.next()) {
                        result.add(new RentAgreement(rs.getInt(1), rs.getString(2), rs.getDate(3),
                                rs.getDate(4), rs.getDouble(5), rs.getInt(6), rs.getInt(7)));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void addBrand(Brand brand) {
        addEntity(6, brand);
    }

    public static void addMachine(Machine machine) {
        addEntity(7, machine);
    }

    public static void addClient(Client client) {
        addEntity(8, client);
    }

    public static void addRentAgreement(RentAgreement rentAgreement) {
        addEntity(9, rentAgreement);
    }

    public static void addPayment(Payment payment) {
        addEntity(10, payment);
    }

    private static void addEntity(int queryId, Entity entity) {
        try {
            PreparedStatement ps = connection.prepareStatement(SqlQueries.getQueryById(queryId));
            switch (queryId) {
                case 6 -> {
                    ps.setString(1, ((Brand) entity).getName());
                }
                case 7 -> {
                    ps.setString(1, ((Machine) entity).getName());
                    ps.setInt(2, ((Machine) entity).getRentPrice());
                    ps.setInt(3, ((Machine) entity).getBrandId());
                }
                case 8 -> {
                    ps.setString(1, ((Client) entity).getName());
                    ps.setString(2, ((Client) entity).getAddress());
                    ps.setString(3, ((Client) entity).getPhoneNumber());
                }
                case 9 -> {
                    ps.setString(1, ((RentAgreement) entity).getPaymentType());
                    ps.setDate(2, new Date(((RentAgreement) entity).getStartDate().getTime()));
                    ps.setDate(3, new Date(((RentAgreement) entity).getExpireDate().getTime()));
                    ps.setDouble(4, ((RentAgreement) entity).getRate());
                    ps.setInt(5, ((RentAgreement) entity).getClientId());
                    ps.setInt(6, ((RentAgreement) entity).getMachineId());
                }
                case 10 -> {
                    ps.setDate(1, new Date(((Payment) entity).getDate().getTime()));
                    ps.setInt(2, ((Payment) entity).getRentAgreementId());
                    ps.setInt(3, ((Payment) entity).getMoneyPaid());
                }
            }
            ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean deleteBrand(int id) {
        return deleteEntity(id, 11);
    }

    public static boolean deleteMachine(int id) {
        return deleteEntity(id, 12);
    }

    public static boolean deleteClient(int id) {
        return deleteEntity(id, 13);
    }

    public static boolean deleteRentAgreement(int id) {
        return deleteEntity(id, 14);
    }

    public static boolean deletePayment(int id) {
        return deleteEntity(id, 15);
    }

    private static boolean deleteEntity(int id, int queryId) {
        try {
            PreparedStatement ps = connection.prepareStatement(SqlQueries.getQueryById(queryId));
            ps.setInt(1, id);
            ps.executeQuery();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public static List<Client> searchClient(String regexName, String regexAddress, String regexPhoneNumber) {
        List<Client> result = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(SqlQueries.getQueryById(16));
            ps.setString(1, regexName);
            ps.setString(2, regexAddress);
            ps.setString(3, regexPhoneNumber);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                result.add(new Client(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<RentAgreement> searchRentAgreement(java.util.Date startDate, java.util.Date endDate) {
        List<RentAgreement> result = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(SqlQueries.getQueryById(17));
            ps.setDate(1, new Date(startDate.getTime()));
            ps.setDate(2, new Date(endDate.getTime()));
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                result.add(new RentAgreement(rs.getInt(1), rs.getString(2), rs.getDate(3),
                        rs.getDate(4), rs.getDouble(5), rs.getInt(6), rs.getInt(7)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void editRentAgreementRate(int id, double rate) {
        try {
            PreparedStatement ps = connection.prepareStatement(SqlQueries.getQueryById(18));
            ps.setDouble(1, rate);
            ps.setInt(2, id);
            ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void editRentAgreementExpireDate(int id, java.util.Date expireDate) {
        try {
            PreparedStatement ps = connection.prepareStatement(SqlQueries.getQueryById(19));
            ps.setDate(1, new Date(expireDate.getTime()));
            ps.setInt(2, id);
            ps.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<ClientWithDebt> getDebtOwners(java.util.Date date) {
        return getClientsWithDebt(21, date);
    }

    public static List<ClientWithDebt> getForwardPayers(java.util.Date date) {
        return getClientsWithDebt(20, date);
    }

    private static List<ClientWithDebt> getClientsWithDebt(int queryId, java.util.Date date) {
        List<ClientWithDebt> result = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(SqlQueries.getQueryById(queryId));
            for (int i = 1; i < 5; ++i) ps.setDate(i, new Date(date.getTime()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) result.add(new ClientWithDebt(rs.getInt(1), rs.getString(2),
                    rs.getInt(3), rs.getDouble(4)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static List<ClientWithDebt> getClientDebts(int id, java.util.Date date) {
        List<ClientWithDebt> result = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(SqlQueries.getQueryById(22));
            for (int i = 1; i < 5; ++i) ps.setDate(i, new Date(date.getTime()));
            ps.setInt(5, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) result.add(new ClientWithDebt(rs.getInt(1), rs.getString(2),
                    rs.getInt(3), rs.getDouble(4)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
