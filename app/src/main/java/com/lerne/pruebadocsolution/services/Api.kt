package com.lerne.pruebadocsolution.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api {

    companion object {
        fun getRetrofit(): Retrofit {
            return Retrofit
                .Builder()
                .baseUrl("https://techhub.docsolutions.com/OnBoardingPre/WebApi/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

}