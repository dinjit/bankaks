package com.example.bankaks

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.bankaks.di.controller.ControllerComponent
import com.example.bankaks.di.controller.ControllerModule
import javax.inject.Inject

abstract class BaseActivity(private val resID: Int) : AppCompatActivity(resID) {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    val controllerComponent: ControllerComponent by lazy {
        (application as MyApplication).applicationComponent
            .controllerComponent(ControllerModule(this))
    }

}