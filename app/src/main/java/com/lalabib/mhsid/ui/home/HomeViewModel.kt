package com.lalabib.mhsid.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.lalabib.mhsid.domain.usecase.MahasiswaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val mahasiswaUseCase: MahasiswaUseCase) : ViewModel() {

    fun getMahasiswa(name: String) = mahasiswaUseCase.getMahasiswa(name).asLiveData()
}