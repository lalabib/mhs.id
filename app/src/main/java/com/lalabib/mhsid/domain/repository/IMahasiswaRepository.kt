package com.lalabib.mhsid.domain.repository

import com.lalabib.mhsid.data.remote.network.Result
import com.lalabib.mhsid.domain.model.Mahasiswa
import kotlinx.coroutines.flow.Flow

interface IMahasiswaRepository {
    fun getMahasiswa(name: String): Flow<Result<List<Mahasiswa>>>
}