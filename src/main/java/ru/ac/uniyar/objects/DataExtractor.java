package ru.ac.uniyar.objects;

import ru.ac.uniyar.SqlQueries;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.stream.Collectors;


public class DataExtractor {
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

    protected static List<Entity> getData(int queryId) {
        ArrayList<Entity> result = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(SqlQueries.getQueryById(queryId));
            ResultSet rs = ps.executeQuery();
            switch (queryId) {
                case 1: {
                    while (rs.next()) {
                        result.add(new Brand(rs.getInt(1), rs.getString(2)));
                    }
                    break;
                }
                case 2: {
                    while (rs.next()) {
                        result.add(new Machine(rs.getInt(1), rs.getString(4), rs.getInt(2),
                                rs.getInt(3)));
                    }
                    break;
                }
                case 3: {
                    while (rs.next()) {
                        result.add(new Client(rs.getInt(1), rs.getString(2), rs.getString(3),
                                rs.getString(4)));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
