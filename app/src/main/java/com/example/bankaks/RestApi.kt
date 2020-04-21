package com.example.bankaks

import com.example.bankaks.model.Creds
import com.example.bankaks.model.LoginResponse
import com.example.bankaks.model.Recharge
import com.example.bankaks.model.RechargeCoupon
import retrofit2.http.Body
import retrofit2.http.POST


interface RestApi {

    @POST("login")
    suspend fun login(@Body creds: Creds): LoginResponse

    @POST("recharge-coupon")
    suspend fun recharge(@Body recharge: Recharge): RechargeCoupon

}