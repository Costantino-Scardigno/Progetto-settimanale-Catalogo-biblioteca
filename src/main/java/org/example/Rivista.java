package org.example;

public class Rivista extends ElementoCatalogo {
    enum Periodicita {
        SETTIMANALE, MENSILE, SEMESTRALE
    }
    private Periodicita periodicita;

    public Rivista(String isbn, String titolo, int anno, int pagine, Periodicita periodicita) {
        super(isbn, titolo, anno, pagine);
        this.periodicita = periodicita;
    }
    public String toString() {
        return super.toString() + ", Periodicità: " + periodicita;
    }
}
