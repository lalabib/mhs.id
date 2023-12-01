package com.lalabib.mhsid.data.remote.response

import com.google.gson.annotations.SerializedName

data class DetailDataMahasiswaResponse (
    @field:SerializedName("data_mhs")
    val dataMahasiswa: DetailMahasiswaResponse,

    @field:SerializedName("datasmt_mhs")
    val dataSmtMahasiswa: List<DetailSmtMahasiswaResponse>,

    @field:SerializedName("datakuliah_mhs")
    val dataKuliahMahasiswa: List<DetailKuliahMahasiswaResponse>
)