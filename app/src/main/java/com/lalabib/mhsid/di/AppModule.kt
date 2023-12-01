package com.lalabib.mhsid.di

import com.lalabib.mhsid.data.remote.MahasiswaRepository
import com.lalabib.mhsid.domain.usecase.MahasiswaInteractor
import com.lalabib.mhsid.domain.usecase.MahasiswaUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMahasiswaUseCase(mahasiswaRepository: MahasiswaRepository): MahasiswaUseCase =
        MahasiswaInteractor(mahasiswaRepository)
}