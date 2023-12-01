package com.lalabib.mhsid.data.remote

import android.util.Log
import com.lalabib.mhsid.data.remote.network.ApiService
import com.lalabib.mhsid.data.remote.network.Result
import com.lalabib.mhsid.domain.model.DetailDataMahasiswa
import com.lalabib.mhsid.domain.model.Mahasiswa
import com.lalabib.mhsid.domain.repository.IMahasiswaRepository
import com.lalabib.mhsid.utils.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MahasiswaRepository @Inject constructor(
    private val apiService: ApiService,
) : IMahasiswaRepository {
    override fun getMahasiswa(name: String): Flow<Result<List<Mahasiswa>>> {
        return flow {
            try {
                val response = apiService.getMahasiswa(name)
                val dataArray = DataMapper.responseMahasiswaToDomain(response.data)
                if (dataArray.isNotEmpty()) {
                    emit(Result.Success(dataArray))
                } else {
                    emit(Result.Loading)
                }
            } catch (e: Exception) {
                emit(Result.Error(e.message.toString()))
                Log.e("TAG", e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun getDetailMahasiswa(uniqueId: String): Flow<Result<DetailDataMahasiswa>> {
        return flow {
            try {
                val response = apiService.getDetailMahasiswa(uniqueId)
                val dataArray = DataMapper.responseDetailDataMhsToDomain(response)
                emit(Result.Success(dataArray))
            } catch (e: Exception) {
                emit(Result.Error(e.message.toString()))
                Log.e("TAG", e.message.toString())
            }
        }
    }
}
