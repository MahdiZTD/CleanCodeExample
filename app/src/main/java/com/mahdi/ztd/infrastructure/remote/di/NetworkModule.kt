package com.mahdi.ztd.infrastructure.remote.di

import com.mahdi.ztd.infrastructure.data.datasource.remote.RemoteAuthenticationDataSource
import com.mahdi.ztd.infrastructure.remote.apihelper.authentication.AuthenticationApiHelper
import com.mahdi.ztd.infrastructure.remote.apihelper.authentication.AuthenticationApiHelperImpl
import com.mahdi.ztd.infrastructure.remote.datasourceimpl.authentication.RemoteAuthenticationDataSourceImpl
import com.mahdi.ztd.infrastructure.remote.retrofithelper.retrofitinterface.RetrofitAuthenticationInterface
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit


/**
 * Created by Mahdi ZTD
 *  on 2/14/2021 .
 *  Email: mz@imagineOn.de
 */

@Module
@InstallIn(ApplicationComponent::class)
abstract class NetworkInterfaceModule {
	@Binds
	abstract fun bindRemoteAuthenticationDataSource(remoteAuthenticationDataSourceImpl: RemoteAuthenticationDataSourceImpl): RemoteAuthenticationDataSource

	@Binds
	abstract fun bindAuthenticationApiHelper(authenticationApiHelperImpl: AuthenticationApiHelperImpl): AuthenticationApiHelper

}

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

	@Provides
	fun provideRetrofitAuthentication(retrofit: Retrofit): RetrofitAuthenticationInterface =
		retrofit.create(RetrofitAuthenticationInterface::class.java)
}