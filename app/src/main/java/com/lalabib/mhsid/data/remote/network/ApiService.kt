package com.lalabib.mhsid.data.remote.network

import com.lalabib.mhsid.data.remote.response.MahasiswaListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/api_datamahasiswa.php")
    suspend fun getMahasiswa(
        @Query("apikey") apiKey: String,
        @Query("menu") menu: Int,
        @Query("nama") name: String
    ): MahasiswaListResponse
}