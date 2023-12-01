package com.lalabib.mhsid.domain.usecase

import com.lalabib.mhsid.data.remote.network.Result
import com.lalabib.mhsid.domain.model.DetailDataMahasiswa
import com.lalabib.mhsid.domain.model.Mahasiswa
import com.lalabib.mhsid.domain.repository.IMahasiswaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MahasiswaInteractor @Inject constructor(private val mahasiswaRepository: IMahasiswaRepository) :
    MahasiswaUseCase {

    override fun getMahasiswa(name: String): Flow<Result<List<Mahasiswa>>> =
        mahasiswaRepository.getMahasiswa(name)

    override fun getDetailMahasiswa(uniqueId: String): Flow<Result<DetailDataMahasiswa>> =
        mahasiswaRepository.getDetailMahasiswa(uniqueId)
}