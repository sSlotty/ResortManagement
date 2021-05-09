package com.example.resortmanagement

import android.app.Application
import com.example.resortmanagement.MainViewModel
import com.example.resortmanagement.repository.Repository
import com.example.resortmanagement.repository.RepositoryImpl
import com.example.resortmanagement.repository.service.apiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MainApplication: Application() {

    private val appModule = module {
        single<Repository>(createdAtStart = true) { RepositoryImpl(get()) } //createdAtStart
        viewModel { MainViewModel(get()) }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(apiModule, appModule)
        }
    }
}