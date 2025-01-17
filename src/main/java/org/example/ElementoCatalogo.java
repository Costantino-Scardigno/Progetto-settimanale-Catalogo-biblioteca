package org.example;

public class ElementoCatalogo {
    private String isbn;
    private String titolo;
    private int anno;
    private int pagine;

    public ElementoCatalogo(String isbn,String titolo, int anno, int pagine) {
        this.isbn=isbn;
        this.titolo=titolo;
        this.anno=anno;
        this.pagine=pagine;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public int getPagine() {
        return pagine;
    }

    public void setPagine(int pagine) {
        this.pagine = pagine;
    }

    @Override
    public String toString() {
        return "ElementoCatalogo{" +
                "isbn='" + isbn + '\'' +
                ", titolo='" + titolo + '\'' +
                ", anno=" + anno +
                ", pagine=" + pagine +
                '}';
    }
}
