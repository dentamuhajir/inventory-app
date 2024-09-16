# Spring Boot REST API Application

## Deskripsi

Aplikasi ini adalah contoh aplikasi REST API yang dibangun menggunakan Spring Boot. Aplikasi ini memiliki arsitektur berlapis dengan tujuan memisahkan tanggung jawab dalam berbagai lapisan untuk memudahkan pengembangan, pemeliharaan, dan pengujian. Berikut adalah penjelasan tentang setiap lapisan dalam aplikasi ini:

## Struktur Proyek

### 1. Model

**Fungsi**: 
Lapisan ini berfungsi sebagai representasi dari data yang akan disimpan di basis data. Model adalah kelas-kelas POJO (Plain Old Java Object) yang merepresentasikan entitas-entitas dalam aplikasi.

**Contoh**:
- `User`: Kelas yang merepresentasikan entitas pengguna dengan atribut seperti `id`, `name`, dan `email`.

### 2. Repository

**Fungsi**: 
Lapisan ini bertanggung jawab untuk berinteraksi dengan basis data. Repository menggunakan JPA (Java Persistence API) untuk melakukan operasi CRUD (Create, Read, Update, Delete) dan query terhadap basis data. Anda bisa menggunakan metode JPA yang sudah ada atau membuat query kustom menggunakan native query.

**Contoh**:
- `UserRepository`: Antarmuka yang memperluas `JpaRepository` dan menyediakan metode untuk menemukan pengguna berdasarkan ID atau nama.

### 3. DTO (Data Transfer Object)

**Fungsi**: 
DTO adalah objek yang digunakan untuk mentransfer data antara lapisan aplikasi, terutama antara lapisan kontroler dan lapisan layanan. DTO memisahkan model domain dari data yang dikirimkan melalui API dan menghindari pengiriman data yang tidak perlu atau sensitif.

**Contoh**:
- `UserDTO`: Objek yang berisi informasi seperti `id`, `name`, dan `email`, tanpa menyertakan atribut lain yang ada di model `User`.

### 4. Service

**Fungsi**: 
Lapisan layanan berfungsi untuk mengimplementasikan logika bisnis dari aplikasi. Di sini, Anda akan menyusun aturan-aturan dan proses-proses yang mengatur bagaimana data diproses sebelum atau setelah diakses dari basis data. Layanan ini sering kali menggunakan repository untuk berinteraksi dengan basis data.

**Contoh**:
- `UserService`: Antarmuka yang mendefinisikan metode untuk mendaftar pengguna baru, memperbarui informasi pengguna, atau memvalidasi data sebelum disimpan.

### 5. ServiceImpl

**Fungsi**: 
Ini adalah implementasi konkret dari antarmuka layanan. Lapisan ini menyediakan implementasi riil dari metode-metode yang didefinisikan di antarmuka layanan. Biasanya, implementasi ini akan memanggil repository untuk melakukan operasi pada data.

**Contoh**:
- `UserServiceImpl`: Kelas yang mengimplementasikan `UserService` dan menyediakan logika bisnis yang sebenarnya, menggunakan `UserRepository` untuk menyimpan dan mengambil data pengguna.

### 6. Controller

**Fungsi**: 
Lapisan kontroler berfungsi sebagai titik masuk utama untuk permintaan HTTP dari klien. Kontroler menerima permintaan, memprosesnya, dan mengarahkan ke lapisan layanan untuk memproses data. Setelah data diproses, kontroler mengembalikan respons kepada klien.

**Contoh**:
- `UserController`: Kelas yang menangani permintaan seperti `GET /users` untuk mendapatkan daftar pengguna atau `POST /users` untuk menambahkan pengguna baru.

## Cara Menjalankan Aplikasi

1. **Clone Repository**:
    ```bash
    git clone https://github.com/username/repository.git
    ```

2. **Masuk ke Direktori Proyek**:
    ```bash
    cd repository
    ```

3. **Jalankan Aplikasi**:
    ```bash
    ./mvnw spring-boot:run
    ```

4. **Akses API**:
    - **GET /users**: Mendapatkan daftar pengguna.
    - **POST /users**: Menambahkan pengguna baru.

## Dependensi

- Spring Boot
- Spring Data JPA
- H2 Database (atau database lain yang digunakan)

## Kontribusi

Jika Anda ingin berkontribusi pada proyek ini, silakan fork repositori ini dan kirimkan pull request dengan perubahan Anda.

## Lisensi

Proyek ini dilisensikan di bawah [MIT License](LICENSE).


============GET LIST STOCK=================

# Alur Data dari Controller ke Database

Proyek ini adalah aplikasi Spring Boot yang mengelola data stok. Data mengalir dari controller ke database dan kembali ke client mengikuti arsitektur berlapis.

## Alur Data

### 1. Controller Layer
- **Endpoint:**  
  Request HTTP diterima di endpoint `/api/stocks` dan diproses oleh metode `StockController.findAllStock()`.
  
- **Pemanggilan Service:**  
  Controller memanggil `stockService.listStocks()` untuk mengambil daftar stok.

### 2. Service Layer
- **Metode Service:**  
  `StockServiceImpl.listStocks()` dipanggil, yang kemudian memanggil `stockRepository.findAll()` untuk mengambil data dari database.

- **Konversi Data:**  
  Setelah data stok dalam bentuk `List<Stock>` didapatkan dari repository, terjadi iterasi (looping) untuk mengonversi setiap entitas `Stock` ke dalam DTO (`StockListResponseDTO`).

### 3. Repository Layer
- **Query Database:**  
  `StockRepository.findAll()` menjalankan query untuk mengambil semua stok dari tabel `stocks` di database.

- **Pengambilan Data:**  
  Data yang diambil berupa list dari objek `Stock`.

### 4. Service Layer (Lanjutan)
- **List DTO:**  
  Setelah proses iterasi selesai, list yang berisi `StockListResponseDTO` dikembalikan ke controller.

### 5. Controller Layer (Lanjutan)
- **Respon ke Client:**  
  Data dikembalikan ke client dalam bentuk JSON dengan HTTP status `200 OK`.

---

## Looping dan Logika di Service Layer

- **Proses Looping:**  
  Di dalam `listStocks()`, looping digunakan untuk mengonversi setiap entitas `Stock` menjadi `StockListResponseDTO`. Proses ini penting agar hanya field yang relevan (ID, nama, gambar) yang dikembalikan ke client.

- **Tidak Ada Kondisi:**  
  Tidak ada kondisi `if` di dalam looping karena pada level ini, semua data stok dianggap valid dan langsung dikonversi ke dalam bentuk DTO.

---

## Kesimpulan
1. Controller memproses HTTP request dan mengarahkan ke service untuk mengambil data stok.
2. Service berperan dalam mengelola logika bisnis, termasuk mengonversi data entitas menjadi DTO.
3. Repository melakukan query ke database dan mengembalikan data.
4. Model merepresentasikan struktur data yang disimpan di dalam database.

==================== UPDATE STOCK BY ID ==========================
# Alur Data dari Controller ke Database (Update Stok)

Proyek ini adalah aplikasi Spring Boot yang mengelola data stok, termasuk memperbarui stok yang ada. Alur data dari controller ke database untuk proses update stok dijelaskan sebagai berikut.

## Alur Data

### 1. Controller Layer
- **Endpoint:**  
  Request HTTP PUT dikirimkan ke endpoint `/api/stocks/{id}` untuk mengupdate stok.

- **Data Diterima:**  
  Controller menerima ID stok dan data stok baru dari `StockUpdateRequestDTO`.

- **Pemanggilan Service:**  
  Controller memanggil `stockService.updateStock(id, dto)` untuk memproses update stok.

### 2. Service Layer
- **Pencarian Stok:**  
  `StockServiceImpl.updateStock(id, dto)` mencari stok berdasarkan ID yang diberikan melalui `stockRepository.findById(id)`.

- **Pembaruan Data:**  
  Jika stok ditemukan, properti stok diperbarui dengan data baru dari DTO.

- **Simpan ke Database:**  
  Stok yang telah diperbarui disimpan kembali ke database menggunakan `stockRepository.save(existingStock)`.

### 3. Repository Layer
- **Pencarian Stok:**  
  `StockRepository.findById(id)` digunakan untuk mencari stok berdasarkan ID.

- **Simpan Stok:**  
  `StockRepository.save(existingStock)` menyimpan entitas stok yang telah diperbarui ke dalam database.

### 4. Controller Layer (Lanjutan)
- **Respon Berhasil:**  
  Jika stok berhasil di-update, controller mengembalikan data stok baru dengan HTTP status `200 OK`.

- **Respon Gagal:**  
  Jika stok tidak ditemukan, controller mengembalikan status `404 Not Found`.

---

## Kondisi dan Logika dalam Function

- **Kondisi if (existingStock == null):**  
  Memastikan bahwa stok dengan ID yang diberikan ada. Jika stok tidak ditemukan, update tidak dilakukan dan method mengembalikan `null` untuk menunjukkan bahwa stok tidak ditemukan.

- **Update Properti Stok:**  
  Setiap properti dari stok yang ditemukan akan di-update dengan nilai baru dari `StockUpdateRequestDTO`.

- **Simpan ke Database:**  
  Setelah semua properti stok diperbarui, stok disimpan kembali ke database menggunakan `stockRepository.save()`.

---

## Kesimpulan
1. **Controller:** Menerima request untuk update stok dan memvalidasi input.
2. **Service:** Menangani logika bisnis, termasuk pengecekan apakah stok ada, memperbarui data stok, dan menyimpan perubahan.
3. **Repository:** Bertanggung jawab untuk melakukan query dan menyimpan data stok yang telah diperbarui ke dalam database.
4. **Model:** Merepresentasikan data yang akan di-update dalam database.





