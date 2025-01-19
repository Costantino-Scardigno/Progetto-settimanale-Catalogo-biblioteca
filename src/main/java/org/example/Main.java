package org.example;



import java.util.Scanner;

public class Main{

    public static void main(String[] args)  {
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
            System.out.print("\nScegli un'opzione: ");

            int scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {
                case 1:
                    archivio.aggiungiElemento();
                    break;
                case 2:
                    archivio.ricercaPerIsbn();
                    break;
                case 3:
                    archivio.rimuoviElemento();

                    break;
                case 4:
                    archivio.ricercaPerAnno();

                    break;
                case 5:
                    archivio.ricercaPerautore();

                    break;
                case 6:
                    archivio.aggiornaElemento();

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
    }}






