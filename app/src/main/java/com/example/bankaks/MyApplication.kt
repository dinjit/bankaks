package com.example.bankaks

import android.app.Application
import com.example.bankaks.di.app.ApplicationComponent
import com.example.bankaks.di.app.ApplicationModule
import com.example.bankaks.di.app.DaggerApplicationComponent

class MyApplication: Application() {

    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .applicationModule(
                ApplicationModule(
                    this
                )
            )
            .build()
    }

}