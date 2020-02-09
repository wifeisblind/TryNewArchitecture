package com.gene.trynewarchitecture.app

import android.content.Context
import com.gene.trynewarchitecture.api.NetworkService
import com.gene.trynewarchitecture.repository.ExceptionListRepository
import com.gene.trynewarchitecture.repository.TasksRepository
import com.gene.trynewarchitecture.viewmodel.ExceptionViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single { buildNetworkService() }

    factory { TasksRepository(get()) }

    factory { ExceptionListRepository(get()) }

    viewModel { ExceptionViewModel(get(),get()) }
}

fun buildNetworkService(): NetworkService =
    Retrofit.Builder()
        .baseUrl("https://545815dc-d129-4e45-9c5c-ac3fd128b1a2.mock.pstmn.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NetworkService::class.java)
