package com.sold

import android.app.Application
import com.sold.di.dataModule
import com.sold.di.userModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SoldApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SoldApplication)
            modules(listOf(dataModule, userModule))
        }
    }
}
