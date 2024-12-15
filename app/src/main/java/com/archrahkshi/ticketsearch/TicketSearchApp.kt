package com.archrahkshi.ticketsearch

import android.app.Application
import com.archrahkshi.ticketsearch.core.network.di.networkModule
import com.archrahkshi.ticketsearch.di.appModule
import org.koin.core.context.startKoin

class TicketSearchApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule, networkModule)
        }
    }
}
