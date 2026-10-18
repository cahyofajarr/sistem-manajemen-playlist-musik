/**
 * Class Lagu berdasarkan Tugas Kelompok sebelumnya.
 * Menyimpan judul, artis, dan durasi lagu serta menyediakan getter/setter.
 */
public class Lagu {
    private String judul;
    private String artis;
    private double durasi;

    public Lagu(String judul, String artis, double durasi) {
        this.judul = judul;
        this.artis = artis;
        this.durasi = durasi;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getArtis() {
        return artis;
    }

    public void setArtis(String artis) {
        this.artis = artis;
    }

    public double getDurasi() {
        return durasi;
    }

    public void setDurasi(double durasi) {
        this.durasi = durasi;
    }

    public void tampilkanInfo() {
        System.out.printf("Judul  : %s%n", judul);
        System.out.printf("Artis  : %s%n", artis);
        System.out.printf("Durasi : %.2f menit%n", durasi);
    }
}
