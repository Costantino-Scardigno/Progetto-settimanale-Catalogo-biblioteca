package org.example;

public class Libro extends ElementoCatalogo {
    private String autore;
    public enum Genere {
        HORROR,FANTASY,THRILLER,ROMANZO,UMORISTICO,BIOGRAFIA
    }
    private Genere genere;




    public Libro(String isbn, String titolo, int anno, int pagine,String autore,Genere genere)  {
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
