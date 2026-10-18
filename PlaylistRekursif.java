/*

 * Data Structures and Algorithm Analysis (COSC6025)
 * Tugas Kelompok ke-3 - Week 8
 * Algorithmic Analysis, Mathematical Induction, and Recursive Functions

 * Kelompok : Group 3
 * Anggota  :
 *   1. Bima Wardana              - 2902826772
 *   2. Moreno Rahman             - 2902829720
 *   3. Vieri Peter Delan Kalalo  - 2902829244
 *   4. Ely Rahmawati             - 2902822686
 *   5. Cahyo Fajar Riyanto       - 2902812350
 
 * Cara menjalankan:
 *   javac Lagu.java PlaylistRekursif.java
 *   java PlaylistRekursif

 */

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Locale;

public class PlaylistRekursif {
    private final Lagu[] playlist;

    private static final int ITER_RINGAN = 100_000;
    private static final int ITER_TAMPIL = 2_000;
    private static final int ITER_WARMUP = 50_000;
    private static final int RONDE = 5;
    private static volatile double sink;

    public PlaylistRekursif(Lagu[] playlist) {
        if (playlist == null) {
            throw new IllegalArgumentException("Playlist tidak boleh null.");
        }
        this.playlist = playlist;
    }

    public Lagu[] getPlaylist() {
        return playlist;
    }

    /**
     * Tujuan: menjumlahkan durasi n lagu pertama.
     * Base case: n <= 0, kembalikan 0.
     * Recursive case: durasi lagu ke-(n-1) + total n-1 lagu.
     * Waktu O(n), ruang O(n) karena call stack rekursif.
     */
    static double totalDurasi(Lagu[] list, int n) {
        if (n <= 0) {
            return 0.0;
        }
        return list[n - 1].getDurasi() + totalDurasi(list, n - 1);
    }

    /**
     * Tujuan: menampilkan lagu dari indeks terakhir sampai indeks pertama.
     * Base case: index < 0, hentikan rekursi.
     * Recursive case: cetak elemen sekarang lalu panggil index - 1.
     * Waktu O(n), ruang O(n) karena call stack rekursif.
     */
    static void tampilkanMundur(Lagu[] list, int index) {
        if (index < 0) {
            return;
        }
        System.out.println((index + 1) + ". " + list[index].getJudul());
        tampilkanMundur(list, index - 1);
    }

    /**
     * Tujuan: mengembalikan durasi terpanjang dalam playlist.
     * Base case: index == 0, kembalikan durasi elemen pertama.
     * Recursive case: cari maksimum pada indeks sebelumnya, lalu bandingkan.
     * Prasyarat: list berisi minimal satu elemen dan index valid.
     * Waktu O(n), ruang O(n) karena call stack rekursif.
     */
    static double cariDurasiTerpanjang(Lagu[] list, int index) {
        if (index == 0) {
            return list[0].getDurasi();
        }
        double durasiSebelumnya = cariDurasiTerpanjang(list, index - 1);
        return Math.max(list[index].getDurasi(), durasiSebelumnya);
    }

    /**
     * Fungsi bantu agar judul dan artis lagu terpanjang dapat ditampilkan.
     * Base case: index == 0, kembalikan lagu pertama.
     * Recursive case: bandingkan lagu saat ini dengan hasil indeks sebelumnya.
     * Waktu O(n), ruang O(n).
     */
    static Lagu cariLaguTerpanjang(Lagu[] list, int index) {
        if (index == 0) {
            return list[0];
        }
        Lagu sebelumnya = cariLaguTerpanjang(list, index - 1);
        return list[index].getDurasi() > sebelumnya.getDurasi()
                ? list[index] : sebelumnya;
    }

    private static Lagu[] buatDataLagu() {
        // Lima data pertama diambil dari Tugas Kelompok sebelumnya.
        // Lima data tambahan membantu eksperimen hingga n = 10 lagu.
        return new Lagu[] {
            new Lagu("Shape of My Heart", "Backstreet Boys", 3.50),
            new Lagu("I Want It That Way", "Backstreet Boys", 3.33),
            new Lagu("Birds of a Feather", "Billie Eilish", 3.30),
            new Lagu("The Scientist", "Coldplay", 5.89),
            new Lagu("Amazing", "Rex Orange County", 3.29),
            new Lagu("Fix You", "Coldplay", 4.52),
            new Lagu("Perfect", "Ed Sheeran", 4.23),
            new Lagu("Shivers", "Ed Sheeran", 3.50),
            new Lagu("Yellow", "Coldplay", 4.29),
            new Lagu("A Sky Full of Stars", "Coldplay", 4.28)
        };
    }

    private static String fmt(double nilai) {
        return String.format(Locale.US, "%.2f", nilai);
    }

    private static String waktuMs(long mulaiNano) {
        return String.format(Locale.US, "%.4f", (System.nanoTime() - mulaiNano) / 1_000_000.0);
    }

    private static void demo(Lagu[] semua) {
        // Lima lagu pertama dipakai untuk menampilkan contoh output ringkas.
        Lagu[] list = Arrays.copyOf(semua, Math.min(5, semua.length));
        int n = list.length;
        if (n == 0) {
            System.out.println("Playlist kosong.");
            return;
        }

        long mulai = System.nanoTime();
        double total = totalDurasi(list, n);
        String waktuTotal = waktuMs(mulai);

        mulai = System.nanoTime();
        double terpanjang = cariDurasiTerpanjang(list, n - 1);
        String waktuCari = waktuMs(mulai);
        Lagu lagu = cariLaguTerpanjang(list, n - 1);

        System.out.println("=== ANALISIS REKURSIF PLAYLIST ===");
        System.out.println("Jumlah lagu     : " + n);
        System.out.println("Total durasi    : " + fmt(total) + " menit");
        System.out.println("Lagu terpanjang : \"" + lagu.getJudul() + "\" - "
                + lagu.getArtis() + " (" + fmt(terpanjang) + " menit)");
        System.out.println();

        System.out.println("Daftar lagu (ditampilkan terbalik):");
        mulai = System.nanoTime();
        tampilkanMundur(list, n - 1);
        String waktuMundur = waktuMs(mulai);
        System.out.println();

        System.out.println("Execution Time (totalDurasi)         : " + waktuTotal + " ms");
        System.out.println("Execution Time (tampilkanMundur)     : " + waktuMundur + " ms");
        System.out.println("Execution Time (cariDurasiTerpanjang): " + waktuCari + " ms");
        System.out.println("(Satu kali panggil; hasil bergantung pada perangkat dan kondisi JVM.)");
    }

    private static double ukurTotal(Lagu[] data, int n) {
        double acc = 0.0;
        long mulai = System.nanoTime();
        for (int i = 0; i < ITER_RINGAN; i++) {
            acc += totalDurasi(data, n);
        }
        double hasil = (System.nanoTime() - mulai) / 1_000_000.0;
        sink = acc;
        return hasil;
    }

    private static double ukurCari(Lagu[] data, int n) {
        double acc = 0.0;
        long mulai = System.nanoTime();
        for (int i = 0; i < ITER_RINGAN; i++) {
            acc += cariDurasiTerpanjang(data, n - 1);
        }
        double hasil = (System.nanoTime() - mulai) / 1_000_000.0;
        sink = acc;
        return hasil;
    }

    private static double ukurMundur(Lagu[] data, int n, PrintStream kosong) {
        PrintStream konsol = System.out;
        try {
            System.setOut(kosong); // buang teks selama benchmark agar I/O layar tidak mendominasi
            long mulai = System.nanoTime();
            for (int i = 0; i < ITER_TAMPIL; i++) {
                tampilkanMundur(data, n - 1);
            }
            return (System.nanoTime() - mulai) / 1_000_000.0;
        } finally {
            System.setOut(konsol);
        }
    }

    private static double median(double[] nilai) {
        double[] salinan = nilai.clone();
        Arrays.sort(salinan);
        return salinan[salinan.length / 2];
    }

    private static void benchmark(Lagu[] semua) {
        PrintStream kosong = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) {
                // Sengaja kosong: hasil cetak tidak ditampilkan selama benchmark.
            }
        });

        try {
            for (int n = 2; n <= semua.length; n++) {
                Lagu[] data = Arrays.copyOf(semua, n);
                for (int i = 0; i < ITER_WARMUP; i++) {
                    sink += totalDurasi(data, n);
                    sink += cariDurasiTerpanjang(data, n - 1);
                }
                ukurMundur(data, n, kosong);
            }

            System.out.println("=== EKSPERIMEN WAKTU EKSEKUSI ===");
            System.out.println("totalDurasi & cariDurasiTerpanjang: " + ITER_RINGAN
                    + "x panggil | tampilkanMundur: " + ITER_TAMPIL + "x panggil (output dimatikan)");
            System.out.println("Setiap nilai adalah median dari " + RONDE + " ronde pengukuran.");
            System.out.println();
            System.out.printf("%-4s %-14s %-22s %-18s%n",
                    "n", "totalDurasi", "cariDurasiTerpanjang", "tampilkanMundur");

            for (int n = 2; n <= semua.length; n++) {
                Lagu[] data = Arrays.copyOf(semua, n);
                double[] a = new double[RONDE];
                double[] b = new double[RONDE];
                double[] c = new double[RONDE];
                for (int r = 0; r < RONDE; r++) {
                    a[r] = ukurTotal(data, n);
                    b[r] = ukurCari(data, n);
                    c[r] = ukurMundur(data, n, kosong);
                }
                double msTotal = median(a);
                double msCari = median(b);
                double msMundur = median(c);
                System.out.printf(Locale.US, "%-4d %-14.3f %-22.3f %-18.3f%n",
                        n, msTotal, msCari, msMundur);
            }
        } finally {
            kosong.close();
        }
    }

    public static void main(String[] args) {
        Lagu[] semua = buatDataLagu();
        demo(semua);
        System.out.println();
        benchmark(semua);
    }
}
