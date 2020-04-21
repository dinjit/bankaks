package com.example.bankaks.model


import com.google.gson.annotations.SerializedName

data class RechargeCoupon(
    @SerializedName("id")
    var id: String?,
    @SerializedName("rechargeCoupon")
    var rechargeCoupon: String?,
    @SerializedName("transactionDate")
    var transactionDate: String?
)