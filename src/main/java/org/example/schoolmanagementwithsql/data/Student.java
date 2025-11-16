package org.example.schoolmanagementwithsql.data;

public class Student {

    private final int id;
    private final String imie;
    private final String nazwisko;

    public Student(int id, String imie, String nazwisko) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    public int getId() { return id; }
    public String getImie() { return imie; }
    public String getNazwisko() { return nazwisko; }
}
