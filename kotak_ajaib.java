public class kotak_ajaib {
  static int[][] box = new int[3][3];

  public static void printHeaders() {
    System.out.println("=== PERMAINAN KOTAK AJAIB 3x3 ===");
    System.out.println("\nIsi angka 1-9 (tidak boleh sama) agar setiap baris,");
    System.out.println("kolom, dan diagonal berjumlah 15.\n");
  }

  public static void printGrid() {
    // hi
    System.out.printf("%17s%9s%9s\n", "Kolom 1", "Kolom 2", "Kolom 3");
    System.out.printf("%34s\n", "+-------+-------+-------+");
    for (int i = 0; i < box.length; i++) {
      System.out.printf(" Baris %d |", i);
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

  public static void main(String[] args) {
    boolean complete = false;
    printHeaders();
    printGrid();
  }
}
