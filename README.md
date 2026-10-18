# Tugas Kelompok 3 - Week 8 (COSC6025)

Program analisis playlist dengan rekursi dalam Java. Lima data lagu pertama mengikuti Tugas Kelompok sebelumnya; lima lagu tambahan digunakan agar eksperimen dapat diuji sampai n = 10.

## Anggota kelompok
1. Bima Wardana - 2902826772
2. Moreno Rahman - 2902829720
3. Vieri Peter Delan Kalalo - 2902829244
4. Ely Rahmawati - 2902822686
5. Cahyo Fajar Riyanto - 2902812350

> Sebelum submit, isi bagian `Kelas` di header `PlaylistRekursif.java` sesuai kelas yang tercantum di LMS.

## Berkas
- `Lagu.java`: class `Lagu` yang dipisahkan dari kode tugas sebelumnya; mempertahankan atribut, constructor, getter/setter, dan `tampilkanInfo()`.
- `PlaylistRekursif.java`: program utama, tiga fungsi rekursif, pengukuran waktu, dan benchmark.
- `Laporan_TK3_Week8_Group3.pdf`: analisis kompleksitas, growth rate, grafik, dan hasil eksperimen.
- `hasil_eksekusi.txt`: log dari satu eksekusi program.

## Cara menjalankan
Pastikan JDK terpasang. Dari folder proyek:

```bash
javac Lagu.java PlaylistRekursif.java
java PlaylistRekursif
```

Atau gunakan Replit dengan template Java dan unggah kedua berkas `.java`, lalu jalankan class `PlaylistRekursif`.

## Catatan submit
1. Lengkapi identitas kelas.
2. Jalankan program di lingkungan kelompok dan ambil screenshot asli dari output terminal/Replit apabila diwajibkan.
3. Unggah proyek ke GitHub/Replit atau rekam demo, lalu salin URL yang benar-benar sudah tersedia.
4. Unggah seluruh `.java`, PDF laporan, bukti eksekusi, dan link ke LMS.

Waktu eksekusi berbeda antarmesin dan antar-run; angka dalam laporan adalah satu sesi contoh menggunakan Java 21.
- `output_program.png`: tampilan visual log output (dirender dari log, bukan screenshot desktop asli).
