package org.example.schoolmanagementwithsql.data;

import java.sql.*;

public class BazaPrzedmiotow {

    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USER = "SYSTEM";
    private static final String PASS = "12345";

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public static void pobierzPrzedmioty() {
        String sql = "SELECT ID_PRZEDMIOTU, NAZWA, OPIS FROM PRZEDMIOT ORDER BY ID_PRZEDMIOTU";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("Lista przedmiotów:");
            while (rs.next()) {
                int id = rs.getInt("ID_PRZEDMIOTU");
                String nazwa = rs.getString("NAZWA");
                String opis = rs.getString("OPIS");
                System.out.println(id + " | " + nazwa + " | " + opis);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void pobierzPojedynczyPrzedmiot(int idPrzedmiotu) {
        String sql = "SELECT NAZWA, OPIS FROM PRZEDMIOT WHERE ID_PRZEDMIOTU = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idPrzedmiotu);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Znaleziono: " + rs.getString("NAZWA") + " | " + rs.getString("OPIS"));
                } else {
                    System.out.println("Brak przedmiotu o ID " + idPrzedmiotu);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // opcjonalne main do szybkiego testu
    public static void main(String[] args) {
        pobierzPrzedmioty();
        pobierzPojedynczyPrzedmiot(1);
    }
}
