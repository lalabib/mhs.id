package com.lalabib.mhsid.data.remote.network

import com.lalabib.mhsid.data.remote.response.MahasiswaListResponse
import com.lalabib.mhsid.BuildConfig.apiKey
import com.lalabib.mhsid.utils.SharedObject.menuHome
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/api/api_datamahasiswa.php?apikey=$apiKey&menu=$menuHome")
    suspend fun getMahasiswa(@Query("nama") name: String): MahasiswaListResponse
}