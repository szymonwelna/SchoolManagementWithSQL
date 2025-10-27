package org.example.schoolmanagementwithsql;

import java.sql.*;

public class BazaUczniow {

    // Dane do połączenia z bazą Oracle
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USER = "SYSTEM";
    private static final String PASS = "12345";

    public static void main(String[] args) {
        // Testowanie obu funkcji
        pobierzUczniow();
        System.out.println("\n=================================");
        pobierzPojedynczegoUcznia(5);
    }

    /**
     * Ustanawia i zwraca nowe połączenie JDBC z bazą danych Oracle.
     * @return Aktywny obiekt Connection.
     * @throws SQLException Jeśli wystąpi błąd połączenia.
     */
    private static Connection getConnection() throws SQLException {
        System.out.println("Łączenie z bazą danych...");
        // DriverManager automatycznie znajdzie sterownik (JDBC 4.0+)
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }


    public static void pobierzUczniow() {
        // Użycie Try-with-Resources do automatycznego zamykania Connection, Statement i ResultSet
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT IMIE, NAZWISKO FROM UCZEN ORDER BY ID_UCZNIA")) {

            System.out.println("\nLista uczniów:");
            System.out.println("-------------------------");

            while (rs.next()) {
                String imie = rs.getString("IMIE");
                String nazwisko = rs.getString("NAZWISKO");
                System.out.println(imie + " " + nazwisko);
            }

            System.out.println("-------------------------");

        } catch (SQLException se) {
            // Obsługa błędów JDBC
            System.err.println("Błąd podczas pobierania uczniów: " + se.getMessage());
            se.printStackTrace();
        } finally {
            System.out.println("Połączenie zamknięte.");
        }
    }


    public static void pobierzPojedynczegoUcznia(int idUcznia) {

        String sql = "SELECT IMIE, NAZWISKO FROM UCZEN WHERE ID_UCZNIA = ?";

        // Użycie Try-with-Resources do automatycznego zamykania Connection, PreparedStatement i ResultSet
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Ustawienie wartości parametru
            pstmt.setInt(1, idUcznia);

            // Wykonanie zapytania i pobranie ResultSet
            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    String imie = rs.getString("IMIE");
                    String nazwisko = rs.getString("NAZWISKO");

                    System.out.println("Znaleziono ucznia o ID " + idUcznia + ":");
                    System.out.println("Imię: " + imie);
                    System.out.println("Nazwisko: " + nazwisko);
                } else {
                    System.out.println("Nie znaleziono ucznia o ID " + idUcznia);
                }
            }

        } catch (SQLException se) {
            System.err.println("Błąd podczas pobierania ucznia ID " + idUcznia + ": " + se.getMessage());
            se.printStackTrace();
        } finally {
            System.out.println("Połączenie zamknięte.");
        }
    }
}