package com.lerne.pruebadocsolution.model

import com.google.gson.annotations.SerializedName

data class loginResponse(
    @SerializedName("Body") val Body:Body
)

data class Body(
    @SerializedName("Token") val Token:String
)
