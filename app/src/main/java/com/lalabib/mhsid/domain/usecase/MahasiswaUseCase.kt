package com.lalabib.mhsid.domain.usecase

import com.lalabib.mhsid.data.remote.network.Result
import com.lalabib.mhsid.domain.model.DetailDataMahasiswa
import com.lalabib.mhsid.domain.model.Mahasiswa
import kotlinx.coroutines.flow.Flow

interface MahasiswaUseCase {

    fun getMahasiswa(name: String): Flow<Result<List<Mahasiswa>>>

    fun getDetailMahasiswa(uniqueId: String): Flow<Result<DetailDataMahasiswa>>
}