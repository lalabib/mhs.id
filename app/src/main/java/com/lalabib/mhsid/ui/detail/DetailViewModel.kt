package com.lalabib.mhsid.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.lalabib.mhsid.domain.usecase.MahasiswaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val mahasiswaUseCase: MahasiswaUseCase): ViewModel() {

    fun getDetailMahasiswa(uniqueId: String) = mahasiswaUseCase.getDetailMahasiswa(uniqueId).asLiveData()
}