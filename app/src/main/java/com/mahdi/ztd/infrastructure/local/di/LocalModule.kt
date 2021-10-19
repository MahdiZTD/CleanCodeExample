package com.mahdi.ztd.infrastructure.local.di

import com.mahdi.ztd.infrastructure.data.datasource.local.LocalAuthenticationDataSource
import com.mahdi.ztd.infrastructure.local.datasourceimpl.LocalAuthenticationDataSourceImpl
import com.mahdi.ztd.infrastructure.local.prefhelper.PreferenceHelper
import com.mahdi.ztd.infrastructure.local.prefhelper.PreferenceHelperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent


/**
 * Created by Mahdi ZTD
 *  on 2/18/2021 .
 *  Email: mz@imagineOn.de
 */

@Module
@InstallIn(ApplicationComponent::class)
abstract class LocalModule {

	@Binds
	abstract fun bindPreferencesHelper(preferenceHelperImpl: PreferenceHelperImpl): PreferenceHelper

	@Binds
	abstract fun bindLocalAuthenticationDataSource(prefAuthenticationDataSourceImpl: LocalAuthenticationDataSourceImpl): LocalAuthenticationDataSource

}