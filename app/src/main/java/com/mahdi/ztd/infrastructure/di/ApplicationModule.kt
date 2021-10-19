package com.mahdi.ztd.infrastructure.di

import android.app.Application
import android.content.Context
import com.mahdi.ztd.infrastructure.remote.retrofithelper.RetrofitHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by Mahdi_ZareTahghighDoost(ZTD)
 *  on 11/26/2020 .
 *  Email: MahdiZTD@gmail.com
 */

@Module
@InstallIn(ApplicationComponent::class)
object ApplicationModule {

    @Provides
    fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    fun provideRetrofit(
        application: Application
    ): Retrofit =
        RetrofitHelper(application/*, localAuthenticationDataSource*/).getRetrofit()

}