import java.util.Scanner;

public abstract class GridGame {
    Scanner sc = new Scanner(System.in);
    int[][] box = new int[3][3];
    boolean complete = false;

    String[] headers;

    public void printHeaders() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
        for (String i : headers) {
            System.out.println(i);
        }
    }

    public void printGrid() {
        System.out.printf("%17s%9s%9s\n", "Kolom 1", "Kolom 2", "Kolom 3");
        System.out.printf("%34s\n", "+-------+-------+-------+");
        for (int i = 0; i < box.length; i++) {
            System.out.printf(" Baris %d |", i + 1);
            for (int j = 0; j < box[0].length; j++) {
                if (box[i][j] != 0) {
                    System.out.printf("%4d%4s", box[i][j], "|");
                } else {
                    System.out.printf("%4s%4s", ".", "|");
                }
            }
            System.out.printf("\n%34s\n", "+-------+-------+-------+");
        }
    }

    public void takeInput() {
        int[] coords = readCoordinates();
        processMove(coords[0], coords[1]);
    }

    protected int[] readCoordinates() {
        int maxBaris = box.length;
        int maxKolom = box[0].length;

        while (true) {
            System.out.print("Masukkan baris (1-3) : ");
            if (!sc.hasNextInt()) {
                System.out.println("Input harus merupakan angka!");
                sc.nextLine();
                continue;
            }
            int baris = sc.nextInt();

            if (baris < 1 || baris > maxBaris) {
                System.out.println("Indeks yang dimasukkan tidak valid!");
                sc.nextLine();
                continue;
            }
            sc.nextLine();

            System.out.print("Masukkan kolom (1-3) : ");
            if (!sc.hasNextInt()) {
                System.out.println("Input harus merupakan angka!");
                sc.nextLine();
                continue;
            }
            int kolom = sc.nextInt();

            if (kolom < 1 || kolom > maxKolom) {
                System.out.println("Indeks yang dimasukkan tidak valid!");
                sc.nextLine();
                continue;
            }
            sc.nextLine();

            return new int[] { baris - 1, kolom - 1 };
        }
    }

    protected abstract void processMove(int baris, int kolom);

    protected abstract void check();

    public void playRound() {
        do {
            printHeaders();
            printGrid();
            takeInput();
            if (complete) {
                printHeaders();
                printGrid();
                check();
            }
        } while (!complete);
    }
}