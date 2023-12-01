package com.lalabib.mhsid.domain.model

data class DetailDataMahasiswa(
    val dataMahasiswa: DetailMahasiswa,
    val dataSmtMahasiswa: List<DetailSmtMahasiswa>,
    val dataKuliahMahasiswa: List<DetailKuliahMahasiswa>
)

data class DetailMahasiswa(
    val nama: String,
    val jenisKelamin: String,
    val nim: String,
    val univ: String,
    val prodi: String,
    val jenjang: String,
    val smtAwal: String,
    val statusAwal: String,
    val statusSaatIni: String
)

data class DetailSmtMahasiswa(
    val idSemester: String? = null,
    val sks: String? = null,
    val statusMahasiswa: String? = null,
)

data class DetailKuliahMahasiswa(
    val kodeMatkul: String? = null,
    val namaMatkul: String? = null,
    val sks: String? = null,
    val idSemester: String? = null,
)
