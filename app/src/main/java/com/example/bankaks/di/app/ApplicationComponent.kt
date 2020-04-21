package com.example.bankaks.di.app

import com.example.bankaks.di.controller.ControllerComponent
import com.example.bankaks.di.controller.ControllerModule
import com.example.bankaks.di.network.NetworkModule
import com.example.bankaks.di.viewModel.ViewModelModule
import dagger.Component

@ApplicationScope
@Component(
    modules = [ApplicationModule::class, NetworkModule::class, ViewModelModule::class]
)
interface ApplicationComponent {

    fun controllerComponent(controllerModule: ControllerModule): ControllerComponent
}