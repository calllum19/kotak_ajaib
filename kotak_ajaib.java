import java.util.*;

// Tolong diubah jadi OOP

public class kotak_ajaib {
  static Scanner input = new Scanner(System.in);
  static int[][] box = new int[3][3];
  static boolean complete = false;

  // hi cal
  // aku kerjain yah, aku tidur dulu
  // cal ganteng
  public static void printHeaders() {
    for (int i = 0; i < 100; i++) {
      System.out.println();
    }
    System.out.println("=== PERMAINAN KOTAK AJAIB 3x3 ===");
    System.out.println("\nIsi angka 1-9 (tidak boleh sama) agar setiap baris,");
    System.out.println("kolom, dan diagonal berjumlah 15.\n");
  }

  public static void printGrid() {
    // hi
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

  public static void takeInput() {
    int amount = 0;
    System.out.print("Masukkan baris (1-3) : ");
    int baris = input.nextInt();
    input.nextLine();
    if (baris < 1 || baris > 3) {
      System.out.print("Baris tidak valid! Tekan Enter untuk lanjut...");
      input.nextLine();
      return;
    }
    System.out.print("Masukkan kolom (1-3) : ");
    int kolom = input.nextInt();
    input.nextLine();
    if (kolom < 1 || kolom > 3) {
      System.out.print("Kolom tidak valid! Tekan Enter untuk lanjut...");
      input.nextLine();
      return;
    }
    System.out.print("Masukkan angka (1-9) : ");
    int angka = input.nextInt();
    input.nextLine();
    if (angka < 1 || angka > 9) {
      System.out.print("Angka harus 1-9! Tekan Enter untuk lanjut...");
      input.nextLine();
      return;
    }
    for (int i = 0; i < box.length; i++) {
      for (int j = 0; j < box[0].length; j++) {
        if (box[i][j] == angka) {
          box[i][j] = 0;
        } else if (box[i][j] != 0) {
          amount++;
        }
      }
    }
    box[baris - 1][kolom - 1] = angka;
    if (amount == 8) {
      complete = true;
    }
  }

  public static void check() {
    int[] sum = new int[8];
    // hori
    for (int i = 0; i < box.length; i++) {
      for (int j = 0; j < box[0].length; j++) {
        sum[i] += box[i][j];
      }
    }

    // vert
    for (int j = 0; j < box.length; j++) {
      for (int i = 0; i < box[0].length; i++) {
        sum[j + box.length] += box[i][j];
      }
    }

    // diagonal \
    for (int i = 0, j = 0; i < box.length && j < box.length; i++, j++) {
      sum[6] += box[i][j];
    }

    // diagonal /
    for (int i = 2, j = 0; i >= 0 && j < box.length; i--, j++) {
      sum[7] += box[i][j];
    }

    System.out.println("Hasil pengecekan: ");
    String kondisiSesuai = "SALAH";
    int sudahBenar = 0;
    // Baris
    for (int i = 0; i < 3; i++) {
      if (sum[i] == 15) {
        kondisiSesuai = "OK";
        sudahBenar++;
      }
      System.out.printf("  Baris %d%6s %2d %s\n", i + 1, "=", sum[i], kondisiSesuai);
      kondisiSesuai = "SALAH";
    }
    // Kolom
    for (int i = 3; i < 6; i++) {
      if (sum[i] == 15) {
        kondisiSesuai = "OK";
        sudahBenar++;
      }
      System.out.printf("  Kolom %d%6s %2d %s\n", i + 1, "=", sum[i], kondisiSesuai);
      kondisiSesuai = "SALAH";
    }
    // Diagonal
    if (sum[6] == 15) {
      kondisiSesuai = "OK";
      sudahBenar++;
    }
    System.out.printf("  Diagonal \\%3s %2d %s\n", "=", sum[6], kondisiSesuai);
    kondisiSesuai = "SALAH";
    if (sum[7] == 15) {
      kondisiSesuai = "OK";
      sudahBenar++;
    }
    System.out.printf("  Diagonal /%3s %2d %s\n", "=", sum[7], kondisiSesuai);

    String evaluasiAkhir;
    if (sudahBenar == 15) {
      evaluasiAkhir = "SELAMAT! Anda berhasil membuat KOTAK AJAIB!";
    } else {
      evaluasiAkhir = "Belum berhasil, susunan ini bukan kotak ajaib.";
    }

    System.out.print("Main lagi? (y/n): ");
    if (input.next().charAt(0) == 'y') {
      complete = false;
      for (int i = 0; i < box.length; i++) {
        for (int j = 0; j < box[0].length; j++) {
          box[i][j] = 0;
        }
      }
    }
  }

  public static void main(String[] args) {
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
  // clear console
  // loop game
}
