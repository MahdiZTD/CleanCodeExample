package com.mahdi.ztd.infrastructure.domain.model.requests


import com.google.gson.annotations.SerializedName

data class VerifyOtpRequestModel(
    @SerializedName("CellPhone")
    val cellPhone: String,
    @SerializedName("Lang")
    val lang: String,
    @SerializedName("MoreDesc")
    val moreDesc: String,
    @SerializedName("RandomCode")
    val randomCode: String,
    @SerializedName("UserType")
    val userType: Int
)