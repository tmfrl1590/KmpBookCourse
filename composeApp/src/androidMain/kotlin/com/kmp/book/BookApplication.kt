package com.kmp.book

import android.app.Application
import com.kmp.book.di.initKoin
import org.koin.android.ext.koin.androidContext

class BookApplication: Application(){
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@BookApplication)
        }
    }
}