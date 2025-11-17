package org.example.schoolmanagementwithsql.data;

public class Przedmiot {
    private final int id;
    private final String nazwa;
    private final String opis;

    public Przedmiot(int id, String nazwa, String opis) {
        this.id = id;
        this.nazwa = nazwa;
        this.opis = opis;
    }

    public int getId() { return id; }
    public String getNazwa() { return nazwa; }
    public String getOpis() { return opis; }

    @Override
    public String toString() {
        return nazwa + (opis != null && !opis.isEmpty() ? " — " + opis : "");
    }
}
