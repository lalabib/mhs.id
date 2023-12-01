package com.lalabib.mhsid.data.remote.response

import com.google.gson.annotations.SerializedName

data class DetailMahasiswaResponse(
    @field:SerializedName("nama_mhs")
    val nama: String,

    @field:SerializedName("kelamin")
    val jenisKelamin: String,

    @field:SerializedName("nim")
    val nim: String,

    @field:SerializedName("nama_universitas")
    val univ: String,

    @field:SerializedName("prodi")
    val prodi: String,

    @field:SerializedName("jenjang")
    val jenjang: String,

    @field:SerializedName("semester_awal")
    val smtAwal: String,

    @field:SerializedName("status_mhs_awal")
    val statusAwal: String,

    @field:SerializedName("status_mhs_terbaru")
    val statusSaatIni: String
)

data class DetailSmtMahasiswaResponse (
    @field:SerializedName("id_semester")
    val idSemester: String? = null,

    @field:SerializedName("totalsks_smt")
    val sks: String? = null,

    @field:SerializedName("status_mhs_smt")
    val statusMahasiswa: String? = null,
)

data class DetailKuliahMahasiswaResponse (
    @field:SerializedName("kode_matakuliah")
    val kodeMatkul: String? = null,

    @field:SerializedName("nama_matakuliah")
    val namaMatkul: String? = null,

    @field:SerializedName("sks_matakuliah")
    val sks: String? = null,

    @field:SerializedName("id_semester")
    val idSemester: String? = null,
)
