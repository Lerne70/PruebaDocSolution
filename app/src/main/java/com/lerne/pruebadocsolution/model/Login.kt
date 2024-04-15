package com.lerne.pruebadocsolution.model

import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName("Body") val Body:BodyLogin
)

data class BodyLogin(
    @SerializedName("Username") val Username: String,
    @SerializedName("Password") val Password: String
)
