package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Archivio {
    public List <ElementoCatalogo> catalogo;
    public Archivio() {
        this.catalogo = new ArrayList<>();
    }

    public  void aggiungiElemento(ElementoCatalogo elemento){
        if (catalogo.stream().anyMatch(e->e.getIsbn().equals(elemento.getIsbn()))) {
            System.out.println("Elemento con ISBN "+ elemento.getIsbn()+ " gia presente");
        } else {
            catalogo.add(elemento);
            System.out.println("Elemento aggiunto correttamente");
        }
    }
    public ElementoCatalogo  ricercaPerIsbn(String isbn) throws EccezioneCustom{
        return catalogo.stream().filter(e->e.getIsbn().equals(isbn)).findFirst().orElseThrow(() -> new EccezioneCustom("Elemento con ISBN "+ isbn+ " non trovato"));
    }

    public void rimuoviElemento(String isbn) {
        catalogo.removeIf(e->e.getIsbn().equals(isbn));
        System.out.println("Elemento rimosso correttamente");
    }

    public List<ElementoCatalogo> ricercaPerAnno(int anno) {
        return catalogo.stream().filter(e-> e.getAnno()== anno).collect(Collectors.toList());
    }

    public List<ElementoCatalogo> ricercaPerautore (String autore) {
        return catalogo.stream().filter(e-> e instanceof Libro && ((Libro)e).getAutore().equals(autore)).collect(Collectors.toList());
    }

    public void aggiornaElemento (String isbn, String titolo,int anno, int pagine) throws EccezioneCustom {
        ElementoCatalogo elemento = ricercaPerIsbn(isbn);
        elemento.setTitolo(titolo);
        elemento.setAnno(anno);
        elemento.setPagine(pagine);
    }

    public void Statistiche(){
        long numeroLibri= catalogo.stream().filter(e-> e instanceof Libro).count();
        long numeroRiviste= catalogo.stream().filter(e-> e instanceof Rivista).count();

        ElementoCatalogo maxPagine = catalogo.stream().max(Comparator.comparingInt(ElementoCatalogo::getPagine)).orElse(null);

        double mediaPagine= catalogo.stream().mapToInt(ElementoCatalogo::getPagine).average().orElse(0);

        System.out.println("Numero totale di libri: " + numeroLibri);
        System.out.println("Numero totale di riviste: " + numeroRiviste);
        System.out.println("Elemento con il maggior numero di pagine: " + maxPagine);
        System.out.println("Media del numero di pagine: " + mediaPagine);
    }




}
