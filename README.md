# onboardingTest
Tabel customer merupakan master data customer.

Tabel Product merupakan list master product.

Tabel Payment merupakan payment proof, yang hanya disubmit dengan bentuk JSON, bukan upload foto/file.

Flow apps :
1. /api/user/order : merupakan controller untuk isi data customer dan product yang ingin dibeli
2. /api/user/payment : merupakan controller untuk melampirkan payment proof berupa JSON sederhana
3. /api/admin/order-details : merupakan controller untuk admin agar bisa melihat list order yang sudah dibuat tapi belum diproses
4. /api/admin/order-details/{id} : merupakan controller untuk admin agar bisa melihat 1 spesifik order
5. /api/admin/validate : merupakan controller untuk melakukan validasi data customer dan payment proof (di case ini jumlah pembayaran), dan akan otomatis generate shipping id jika sudah valid.

tipe order status : 
0 = created, belum modified
1 = accepted/confirmed
99 = cancelled

