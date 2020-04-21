package com.example.bankaks.di.controller

import com.example.bankaks.MainActivity
import com.example.bankaks.login.LoginFragment
import com.example.bankaks.rechargeCoupon.RechargeCouponFragment
import com.example.bankaks.userDetail.UserDetailFragment
import dagger.Subcomponent

@Subcomponent(
    modules = [ControllerModule::class]
)
interface ControllerComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(loginFragment: LoginFragment)
    fun inject(userDetailFragment: UserDetailFragment)
    fun inject(rechargeCouponFragment: RechargeCouponFragment)
}