package com.lalabib.mhsid.utils

import com.lalabib.mhsid.data.remote.response.DetailDataMahasiswaResponse
import com.lalabib.mhsid.data.remote.response.DetailKuliahMahasiswaResponse
import com.lalabib.mhsid.data.remote.response.DetailMahasiswaResponse
import com.lalabib.mhsid.data.remote.response.DetailSmtMahasiswaResponse
import com.lalabib.mhsid.data.remote.response.MahasiswaResponse
import com.lalabib.mhsid.domain.model.DetailDataMahasiswa
import com.lalabib.mhsid.domain.model.DetailKuliahMahasiswa
import com.lalabib.mhsid.domain.model.DetailMahasiswa
import com.lalabib.mhsid.domain.model.DetailSmtMahasiswa
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

    fun responseDetailDataMhsToDomain(response: DetailDataMahasiswaResponse): DetailDataMahasiswa {
        return DetailDataMahasiswa(
            dataMahasiswa = responseDetailMhsToDomain(response.dataMahasiswa),
            dataSmtMahasiswa = response.dataSmtMahasiswa.map { responseDetailSmtMhsToDomain(it) },
            dataKuliahMahasiswa = response.dataKuliahMahasiswa.map {
                responseDetailKuliahMhsToDomain(
                    it
                )
            }
        )
    }

    private fun responseDetailMhsToDomain(response: DetailMahasiswaResponse) =
        DetailMahasiswa(
            response.nama,
            response.nim,
            response.jenisKelamin,
            response.univ,
            response.prodi,
            response.jenjang,
            response.smtAwal,
            response.statusAwal,
            response.statusSaatIni
        )

    private fun responseDetailSmtMhsToDomain(response: DetailSmtMahasiswaResponse) =
        DetailSmtMahasiswa(
            response.idSemester,
            response.sks,
            response.statusMahasiswa
        )


    private fun responseDetailKuliahMhsToDomain(response: DetailKuliahMahasiswaResponse) =
        DetailKuliahMahasiswa(
            response.kodeMatkul,
            response.namaMatkul,
            response.sks,
            response.idSemester
        )
}