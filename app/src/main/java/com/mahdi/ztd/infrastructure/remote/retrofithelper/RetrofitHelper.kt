package com.mahdi.ztd.infrastructure.remote.retrofithelper

import android.app.Application
import com.readystatesoftware.chuck.ChuckInterceptor
import com.mahdi.ztd.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Mahdi_ZareTahghighDoost(ZTD)
 *  on 11/26/2020 .
 *  Email: MahdiZTD@gmail.com
 */

class RetrofitHelper @Inject constructor(
    private val application: Application
) {

    private val defaultOkHttpRequestTimeoutSeconds = 50L
    private val defaultOkHttpResponseTimeoutSeconds = 50L
    private val defaultOkHttpTimeoutSeconds = 50L

    fun getRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY


        val httpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(ChuckInterceptor(application))
            .readTimeout(defaultOkHttpRequestTimeoutSeconds, TimeUnit.SECONDS)
            .writeTimeout(defaultOkHttpResponseTimeoutSeconds, TimeUnit.SECONDS)
            .connectTimeout(defaultOkHttpTimeoutSeconds, TimeUnit.SECONDS)
            .build()

        val baseUrl =
            if (BuildConfig.DEBUG) NetworkConstants.BASE_URL_DEBUG else NetworkConstants.BASE_URL_PRODUCTION

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}