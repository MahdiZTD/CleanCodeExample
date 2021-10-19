package com.mahdi.ztd.infrastructure.data.di

import com.mahdi.ztd.infrastructure.data.repositoryimpl.AuthenticationRepositoryImpl
import com.mahdi.ztd.infrastructure.domain.repository.AuthenticationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

/**
 * Created by Mahdi_ZareTahghighDoost(ZTD)
 *  on 11/26/2020 .
 *  Email: MahdiZTD@gmail.com
 */

@Module
@InstallIn(ApplicationComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindAuthenticationRepository(authenticationRepositoryImpl: AuthenticationRepositoryImpl): AuthenticationRepository


}