package com.mahdi.ztd.infrastructure.domain.model.responses


import com.google.gson.annotations.SerializedName

data class ProfileResponseModel(
    @SerializedName("accountName")
    val accountName: String?,
    @SerializedName("cellPhone")
    val cellPhone: String?,
    @SerializedName("dWTDoctorId")
    val dWTDoctorId: Int?,
    @SerializedName("dWTDoctorOfficeId")
    val dWTDoctorOfficeId: Int?,
    @SerializedName("eMail")
    val eMail: String?,
    @SerializedName("eNTCityId")
    val eNTCityId: Int?,
    @SerializedName("eNTUserAccountId")
    val eNTUserAccountId: Int?,
    @SerializedName("firstName")
    val firstName: String?,
    @SerializedName("gender")
    val gender: Boolean?,
    @SerializedName("isActiveSendFile")
    val isActiveSendFile: Boolean?,
    @SerializedName("isActiveSendText")
    val isActiveSendText: Boolean?,
    @SerializedName("isLoginApp")
    val isLoginApp: Boolean?,
    @SerializedName("isLoginSite")
    val isLoginSite: Boolean?,
    @SerializedName("isPatient")
    val isPatient: Boolean?,
    @SerializedName("LastActivity")
    val lastActivity: String?,
    @SerializedName("lastName")
    val lastName: String?,
    @SerializedName("moreDesc")
    val moreDesc: String?,
    @SerializedName("password")
    val password: String?,
    @SerializedName("sSN")
    val sSN: String?,
    @SerializedName("userImage")
    val userImage: String?,
    @SerializedName("userType")
    val userType: String?
)