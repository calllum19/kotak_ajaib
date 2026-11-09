public class KotakAjaib extends GridGame {
    public KotakAjaib() {
        headers = new String[] {
                "=== PERMAINAN KOTAK AJAIB 3x3 ===",
                "\nIsi angka 1-9 (tidak boleh sama) agar setiap baris,",
                "kolom, dan diagonal berjumlah 15.\n"
        };
    }

    @Override
    protected void processMove(int baris, int kolom) {
        int angka;
        while (true) {
            System.out.print("Masukkan angka (1-9) : ");
            if (!sc.hasNextInt()) {
                System.out.println("sc harus merupakan angka!");
                sc.nextLine();
                continue;
            }
            angka = sc.nextInt();
            if (angka < 1 || angka > 9) {
                System.out.print("Angka harus 1-9! Tekan Enter untuk lanjut...");
                sc.nextLine();
                continue;
            }
            sc.nextLine();

            int amount = 0;
            for (int i = 0; i < box.length; i++) {
                for (int j = 0; j < box[0].length; j++) {
                    if (box[i][j] == angka) {
                        box[i][j] = 0;
                    } else if (box[i][j] != 0) {
                        amount++;
                    }
                }
            }
            box[baris][kolom] = angka;
            if (amount == 8)
                complete = true;
            break;
        }
    }

    @Override
    protected void check() {
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
        if (sudahBenar == 8) {
            evaluasiAkhir = "SELAMAT! Anda berhasil membuat KOTAK AJAIB!";
        } else {
            evaluasiAkhir = "Belum berhasil, susunan ini bukan kotak ajaib.";
        }
        System.out.println(evaluasiAkhir);
        System.out.print("Main lagi? (y/n): ");
        if (sc.next().charAt(0) == 'y') {
            complete = false;
            for (int i = 0; i < box.length; i++) {
                for (int j = 0; j < box[0].length; j++) {
                    box[i][j] = 0;
                }
            }
        }
    }

}
