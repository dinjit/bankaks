package com.example.bankaks.model


import com.google.gson.annotations.SerializedName

data class Error(
    @SerializedName("code")
    var code: Int?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("name")
    var name: String?
)