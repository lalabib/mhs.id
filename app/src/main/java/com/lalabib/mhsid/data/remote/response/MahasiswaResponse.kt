package com.lalabib.mhsid.data.remote.response

import com.google.gson.annotations.SerializedName

data class MahasiswaResponse(
    @field:SerializedName("nama_mhs")
    val nama: String,

    @field:SerializedName("nim_mhs")
    val nim: String,

    @field:SerializedName("nama_pt")
    val univ: String,

    @field:SerializedName("prodi")
    val prodi: String,

    @field:SerializedName("uniqueid")
    val uniqueId: String
)
