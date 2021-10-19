package com.mahdi.ztd.infrastructure.domain.model.requests


import com.google.gson.annotations.SerializedName

data class RegisterRequestModel(
    @SerializedName("accountName")
    val accountName: String,
    @SerializedName("cellPhone")
    val cellPhone: String,
    @SerializedName("eNTCityId")
    val eNTCityId: Int,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("gender")
    val gender: Int,
    @SerializedName("Lang")
    val lang: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("moreDesc")
    val moreDesc: String,
    @SerializedName("password")
    val password: String
)