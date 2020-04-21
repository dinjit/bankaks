package com.example.bankaks.model


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("currency")
    var currency: String?,
    @SerializedName("firstName")
    var firstName: String?,
    @SerializedName("lastName")
    var lastName: String?,
    @SerializedName("salesAmount")
    var salesAmount: String?
)