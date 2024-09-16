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

