package org.example;


import java.util.List;
import java.util.Scanner;

public class Main{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Archivio archivio = new Archivio();

        while (true) {
            System.out.println("\nGestione Catalogo Bibliotecario");
            System.out.println("1. Aggiungi un elemento");
            System.out.println("2. Ricerca per ISBN");
            System.out.println("3. Rimuovi un elemento per ISBN");
            System.out.println("4. Ricerca per anno di pubblicazione");
            System.out.println("5. Ricerca per autore");
            System.out.println("6. Aggiorna un elemento");
            System.out.println("7. Mostra statistiche");
            System.out.println("0. Esci");
            System.out.print("Scegli un'opzione: ");

            int scelta = scanner.nextInt();
            scanner.nextLine(); // Consuma la nuova riga

            switch (scelta) {
                case 1:
                    aggiungiElemento(scanner, archivio);
                    break;
                case 2:
                    ricercaPerIsbn(scanner, archivio);
                    break;
                case 3:
                    rimuoviElemento(scanner, archivio);
                    break;
                case 4:
                    ricercaPerAnno(scanner, archivio);
                    break;
                case 5:
                    ricercaPerAutore(scanner, archivio);
                    break;
                case 6:
                    aggiornaElemento(scanner, archivio);
                    break;
                case 7:
                    archivio.Statistiche();
                    break;
                case 0:
                    System.out.println("Uscita dal programma.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Scelta non valida. Riprova.");
            }
        }
    }

    private static void aggiungiElemento(Scanner scanner, Archivio archivio) {
        System.out.print("Vuoi aggiungere un libro o una rivista? (L/R): ");
        String tipo = scanner.nextLine().toUpperCase();

        System.out.print("Inserisci ISBN: ");
        String isbn = scanner.nextLine();

        System.out.print("Inserisci titolo: ");
        String titolo = scanner.nextLine();

        System.out.print("Inserisci anno di pubblicazione: ");
        int anno = scanner.nextInt();

        System.out.print("Inserisci numero di pagine: ");
        int pagine = scanner.nextInt();
        scanner.nextLine();

        if ("L".equals(tipo)) {
            System.out.print("Inserisci autore: ");
            String autore = scanner.nextLine();

            System.out.print("Inserisci genere: ");
            String genere = scanner.nextLine();

            archivio.aggiungiElemento(new Libro(isbn, titolo, anno, pagine, autore, genere));
        } else if ("R".equals(tipo)) {
            System.out.print("Inserisci periodicità (SETTIMANALE, MENSILE, SEMESTRALE): ");
            String periodicita = scanner.nextLine().toUpperCase();

            archivio.aggiungiElemento(new Rivista(isbn, titolo, anno, pagine, Rivista.Periodicita.valueOf(periodicita)));
        } else {
            System.out.println("Tipo non valido.");
        }
    }

    private static void ricercaPerIsbn(Scanner scanner, Archivio archivio) {
        System.out.print("Inserisci ISBN: ");
        String isbn = scanner.nextLine();

        try {
            ElementoCatalogo elemento = archivio.ricercaPerIsbn(isbn);
            System.out.println("Elemento trovato: " + elemento);
        } catch (EccezioneCustom e) {
            System.out.println(e.getMessage());
        }
    }

    private static void rimuoviElemento(Scanner scanner, Archivio archivio) {
        System.out.print("Inserisci ISBN dell'elemento da rimuovere: ");
        String isbn = scanner.nextLine();
        archivio.rimuoviElemento(isbn);
    }

    private static void ricercaPerAnno(Scanner scanner, Archivio archivio) {
        System.out.print("Inserisci anno di pubblicazione: ");
        int anno = scanner.nextInt();
        scanner.nextLine();

        List<ElementoCatalogo> elementi = archivio.ricercaPerAnno(anno);
        if (elementi.isEmpty()) {
            System.out.println("Nessun elemento trovato per l'anno specificato.");
        } else {
            elementi.forEach(System.out::println);
        }
    }

    private static void ricercaPerAutore(Scanner scanner, Archivio archivio) {
        System.out.print("Inserisci autore: ");
        String autore = scanner.nextLine();

        List<ElementoCatalogo> elementi = archivio.ricercaPerautore(autore);
        if (elementi.isEmpty()) {
            System.out.println("Nessun elemento trovato per l'autore specificato.");
        } else {
            elementi.forEach(System.out::println);
        }
    }

    private static void aggiornaElemento(Scanner scanner, Archivio archivio) {
        System.out.print("Inserisci ISBN dell'elemento da aggiornare: ");
        String isbn = scanner.nextLine();

        System.out.print("Inserisci nuovo titolo: ");
        String titolo = scanner.nextLine();

        System.out.print("Inserisci nuovo anno di pubblicazione: ");
        int anno = scanner.nextInt();

        System.out.print("Inserisci nuovo numero di pagine: ");
        int pagine = scanner.nextInt();
        scanner.nextLine();

        try {
            archivio.aggiornaElemento(isbn, titolo, anno, pagine);
            System.out.println("Elemento aggiornato con successo.");
        } catch (EccezioneCustom e) {
            System.out.println(e.getMessage());
        }
    }
}
