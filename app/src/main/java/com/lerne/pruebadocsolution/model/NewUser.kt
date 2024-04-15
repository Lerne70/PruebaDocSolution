package com.lerne.pruebadocsolution.model

import com.google.gson.annotations.SerializedName

data class NewUser(
    @SerializedName("Body") val Body:BodyNewUser
)

data class BodyNewUser(
    @SerializedName("Tenant") val Tenant:Any?,
    @SerializedName("Username") val Username:String,
    @SerializedName("Password") val Password:String,
    @SerializedName("Name") val Name:String,
    @SerializedName("FatherLastName") val FatherLastName:String,
    @SerializedName("MotherLastName") val MotherLastName:String,
    @SerializedName("Email") val Email:String,
    @SerializedName("PhoneNumber") val PhoneNumber:String,
    @SerializedName("Metadata") val Metadata:Any?,
    @SerializedName("Roles") val Roles:List<roles>,
)

data class roles(
    @SerializedName("Id") val Id:Int,
    @SerializedName("Name") val Name: String
)
