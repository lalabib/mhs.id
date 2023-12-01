package com.lalabib.mhsid.di

import com.lalabib.mhsid.data.remote.MahasiswaRepository
import com.lalabib.mhsid.domain.repository.IMahasiswaRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(mahasiswaRepository: MahasiswaRepository) : IMahasiswaRepository
}