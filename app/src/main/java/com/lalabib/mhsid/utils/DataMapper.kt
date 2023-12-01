package com.lalabib.mhsid.utils

import com.lalabib.mhsid.data.remote.response.MahasiswaResponse
import com.lalabib.mhsid.domain.model.Mahasiswa

object DataMapper {

    fun responseMahasiswaToDomain(response: List<MahasiswaResponse>): List<Mahasiswa> {
        return response.map {
            Mahasiswa(
                it.nama,
                it.nim,
                it.univ,
                it.prodi,
                it.uniqueId
            )
        }
    }
}