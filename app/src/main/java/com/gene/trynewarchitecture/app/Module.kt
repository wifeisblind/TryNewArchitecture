package com.gene.trynewarchitecture.app

import android.content.Context
import com.gene.trynewarchitecture.viewmodel.ExceptionViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {

    viewModel { ExceptionViewModel() }
}