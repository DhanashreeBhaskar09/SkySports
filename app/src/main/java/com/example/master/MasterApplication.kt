package com.example.master

import android.app.Application
import com.example.master.di.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin

class MasterApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setupApplication()
    }

    // Setup
    private fun setupApplication() {
        if (GlobalContext.getOrNull() == null) {
            startKoin {
                androidLogger()
                androidContext(this@MasterApplication)
                modules(listOf(viewModelModules))
            }
        }
    }
}
