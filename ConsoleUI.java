import java.util.Scanner;

public class ConsoleUI {
    private static final int TARGET = 15;
    private static final String HEADERS = "=== PERMAINAN KOTAK AJAIB 3x3 ===\n"
            + "\n"
            + "Isi angka 1-9 (tidak boleh sama) agar setiap baris,\n"
            + "kolom, dan diagonal berjumlah 15.\n";

    private Scanner input;

    public ConsoleUI() {
        this.input = new Scanner(System.in);
    }

    public void printHeaders() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
        System.out.println(HEADERS);
    }

    public void printBoard(MagicSquareBoard board) {
        System.out.printf("%17s%9s%9s\n", "Kolom 1", "Kolom 2", "Kolom 3");
        System.out.printf("%34s\n", "+-------+-------+-------+");

        for (int i = 0; i < board.getSize(); i++) {
            System.out.printf(" Baris %d |", i + 1);
            for (int j = 0; j < board.getSize(); j++) {
                int cell = board.getCell(i, j);
                if (cell != 0) {
                    System.out.printf("%4d%4s", cell, "|");
                } else {
                    System.out.printf("%4s%4s", ".", "|");
                }
            }
            System.out.printf("\n%34s\n", "+-------+-------+-------+");
        }
    }

    public int askInt(String prompt) {
        System.out.print(prompt);
        if (!input.hasNextInt()) {
            input.nextLine();
            return -1;
        }
        int value = input.nextInt();
        input.nextLine();
        return value;
    }

    public void pauseWithMessage(String message) {
        System.out.print(message);
        input.nextLine();
    }

    public void printResult(int[] sums, boolean isWin) {
        System.out.println("Hasil pengecekan: ");

        // Baris
        for (int i = 0; i < 3; i++) {
            String status = (sums[i] == TARGET) ? "OK" : "SALAH";
            System.out.printf("  Baris %d%6s %2d %s\n", i + 1, "=", sums[i], status);
        }

        // Kolom
        for (int i = 3; i < 6; i++) {
            String status = (sums[i] == TARGET) ? "OK" : "SALAH";
            System.out.printf("  Kolom %d%6s %2d %s\n", i - 2, "=", sums[i], status);
        }

        // Diagonal
        String diag1Status = (sums[6] == TARGET) ? "OK" : "SALAH";
        System.out.printf("  Diagonal \\%3s %2d %s\n", "=", sums[6], diag1Status);

        String diag2Status = (sums[7] == TARGET) ? "OK" : "SALAH";
        System.out.printf("  Diagonal /%3s %2d %s\n", "=", sums[7], diag2Status);

        if (isWin) {
            System.out.println("SELAMAT! Anda berhasil membuat KOTAK AJAIB!");
        } else {
            System.out.println("Belum berhasil, susunan ini bukan kotak ajaib.");
        }
    }

    public boolean askPlayAgain() {
        System.out.print("Main lagi? (y/n): ");
        String response = input.nextLine().trim().toLowerCase();
        return response.startsWith("y");
    }
}