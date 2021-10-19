package com.mahdi.ztd.infrastructure.domain.model


import com.google.gson.annotations.SerializedName

data class DummyResponse(
    @SerializedName("Value1")
    val value1: String,
    @SerializedName("Value2")
    val value2: Any,
    @SerializedName("Value3")
    val value3: Any
)