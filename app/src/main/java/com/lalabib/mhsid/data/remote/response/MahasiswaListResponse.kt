package com.lalabib.mhsid.data.remote.response

import com.google.gson.annotations.SerializedName

data class MahasiswaListResponse(
    @field:SerializedName("data")
    val data: List<MahasiswaResponse>
)
