package com.example.bankaks

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.example.bankaks.di.controller.ControllerComponent
import com.example.bankaks.di.controller.ControllerModule
import javax.inject.Inject

abstract class BaseFragment: Fragment() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var navController: NavController


    val controllerComponent: ControllerComponent by lazy {
        (requireActivity().application as MyApplication).applicationComponent
            .controllerComponent(ControllerModule(requireActivity()))
    }
}