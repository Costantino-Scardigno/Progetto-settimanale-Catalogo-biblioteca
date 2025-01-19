package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Archivio {
    public List <ElementoCatalogo> catalogo;
    public Archivio() {
        this.catalogo = new ArrayList<>();
    }
    Scanner scanner = new Scanner(System.in);

    public  void aggiungiElemento()  {
        String tipo = "";

        while (true) {
            System.out.print("\nVuoi aggiungere un libro o una rivista? (L/R): ");
             tipo = scanner.nextLine().toUpperCase();
            if (tipo.equals("L") || tipo.equals("R")) {
                break;
            } else {
                System.out.println("Tipo non valido! Devi inserire 'L' per libro o 'R' per rivista.");

            }
        }
            System.out.print("Inserisci ISBN: ");
            String isbn = scanner.nextLine();
          if (catalogo.stream().anyMatch(e -> e.getIsbn().equals(isbn))){
            System.out.println("Elemento con ISBN "+ isbn  + " gia presente!! Prova con un altro ISBN.");
            return;
        }
            System.out.print("Inserisci titolo: ");
            String titolo = scanner.nextLine();

        int anno = 0;
        int pagine = 0;
        while (true) {
            try {
                System.out.print("Inserisci anno di pubblicazione: ");
                 anno = scanner.nextInt();
                scanner.nextLine();
                if (anno <= 0) {
                    throw new NumberFormatException("L'anno deve essere un numero positivo!");
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Errore: Devi inserire dei NUMERI !!!");
                scanner.nextLine();
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print("Inserisci numero di pagine: ");
                 pagine = scanner.nextInt();
                scanner.nextLine();
                if (pagine <= 0) {
                    throw new NumberFormatException("Il numero di pagine deve essere un numero positivo!");
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Errore: Devi inserire un NUMERO valido per le pagine!");
                scanner.nextLine();
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
            if ("L".equals(tipo)) {
                System.out.print("Inserisci autore: ");
                String autore = scanner.nextLine();

                Libro.Genere genereEnum = null;
                boolean genereValido = false;

                while (!genereValido) {
                    try {
                        System.out.print("Inserisci genere (HORROR, FANTASY, THRILLER, ROMANZO, UMORISTICO, BIOGRAFIA): ");
                        String genere = scanner.nextLine().toUpperCase();
                        genereEnum = Libro.Genere.valueOf(genere);
                        genereValido = true;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Genere non valido! Inserisci un genere tra quelli specificati.");
                    }
                }

                catalogo.add(new Libro(isbn, titolo, anno, pagine, autore, genereEnum));
                System.out.println("Libro aggiunto con successo.");
            }
            if ("R".equals(tipo)) {

                Rivista.Periodicita periodicitaEnum = null;
                while (true) {
                    try {
                        System.out.print("\nInserisci periodicità (SETTIMANALE, MENSILE, SEMESTRALE): ");
                        String periodicita = scanner.nextLine().toUpperCase();
                        periodicitaEnum = Rivista.Periodicita.valueOf(periodicita);
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Periodicità non valida! Inserisci una tra SETTIMANALE, MENSILE, SEMESTRALE.");
                    }
                }
                catalogo.add(new Rivista(isbn, titolo, anno, pagine, periodicitaEnum));
                System.out.println("Rivista aggiunta con successo.");
            }


    }
    public   ElementoCatalogo ricercaPerIsbn() {
        System.out.print("Inserisci ISBN: ");
        String isbn = scanner.nextLine();
        try {
            ElementoCatalogo elemento = catalogo.stream()
                    .filter(e -> e.getIsbn().equals(isbn))
                    .findFirst()
                    .orElseThrow(() -> new EccezioneCustom("Elemento con ISBN " + isbn + " non trovato"));

            System.out.println("Elemento trovato: " + elemento);
            return elemento;
        } catch (EccezioneCustom e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void rimuoviElemento() {
        System.out.print("Inserisci ISBN dell'elemento da rimuovere: ");
        String isbn = scanner.nextLine();
        catalogo.removeIf(e ->e.getIsbn().equals(isbn));
            System.out.println("Elemento rimosso correttamente!");
    }

    public  List<ElementoCatalogo> ricercaPerAnno() {
        int anno = 0;
        boolean annoValido = false;
        while (!annoValido) {
            try {
                System.out.print("Inserisci anno di pubblicazione: ");
                anno = scanner.nextInt();
                scanner.nextLine();
                annoValido = true;
            } catch (InputMismatchException e) {
                System.out.println("Errore: Inserisci un NUMERO valido per l'anno!!");
                scanner.nextLine();
            }
        }

        final int annoFinale = anno;
        List<ElementoCatalogo> elementi = catalogo.stream()
                .filter(e -> e.getAnno() == annoFinale)
                .collect(Collectors.toList());
        if (elementi.isEmpty()) {
            System.out.println("Nessun elemento trovato!");
        } else {
            elementi.forEach(System.out::println);
        }
        return elementi;
    }


    public List<ElementoCatalogo> ricercaPerautore () {
        System.out.print("Inserisci autore: ");
        String autore = scanner.nextLine();
        List <ElementoCatalogo> elementi= catalogo.stream().filter(e-> e instanceof Libro && ((Libro)e).getAutore().equals(autore)).collect(Collectors.toList());
        if (elementi.isEmpty()) {
            System.out.println("Nessun elemento trovato per l'autore specificato!");
        } else {
            elementi.forEach(System.out::println);
        }
        return elementi;
    }

    public List<ElementoCatalogo> aggiornaElemento() {
        System.out.print("Inserisci ISBN dell'elemento da aggiornare: ");
        String isbn = scanner.nextLine();
        ElementoCatalogo elemento = catalogo.stream()
                .filter(e -> e.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);

        if (elemento != null) {
            System.out.print("Inserisci nuovo titolo: ");
            String titolo = scanner.nextLine();
            elemento.setTitolo(titolo);

            while (true) {
                try {
                    System.out.print("Inserisci anno di pubblicazione: ");
                     int anno = scanner.nextInt();
                    scanner.nextLine();
                    if (anno <= 0) {
                        throw new NumberFormatException("L'anno deve essere un numero positivo!");
                    }

                    elemento.setAnno(anno);
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Errore: Devi inserire un NUMERO valido per l'anno!");
                    scanner.nextLine();
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                }
            }

            while (true) {
                try {
                    System.out.print("Inserisci numero di pagine: ");
                   int pagine = scanner.nextInt();
                    scanner.nextLine();
                    if (pagine <= 0) {
                        throw new NumberFormatException("Il numero di pagine deve essere un numero positivo!");
                    }

                    elemento.setPagine(pagine);
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Errore: Devi inserire un NUMERO valido per le pagine!");
                    scanner.nextLine();
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                }
            }

            System.out.println("Elemento aggiornato con successo!");
        } else {
            System.out.println("Elemento con ISBN non trovato.");
        }

        return catalogo;
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
