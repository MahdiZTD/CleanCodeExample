package com.mahdi.ztd.infrastructure.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Mahdi_ZareTahghighDoost(ZTD)
 *  on 11/27/2020 .
 *  Email: MahdiZTD@gmail.com
 */
data class GeneralApiResponse(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("Message")
    val message: String
)