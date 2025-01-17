package org.example;

public class Libro extends ElementoCatalogo {
    private String autore;
    private String genere;


    public Libro(String isbn, String titolo, int anno, int pagine,String autore,String genere) {
        super(isbn, titolo, anno, pagine);
        this.autore=autore;
        this.genere=genere;
    }

    public String getAutore() {
        return autore;
    }
    public String toString() {
        return super.toString()+ " Autore="+ autore + " " + "Genere=" + genere;
    }
}
