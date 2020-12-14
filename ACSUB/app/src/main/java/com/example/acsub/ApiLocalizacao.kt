package com.example.acsub

import android.telecom.Call
import retrofit2.http.GET

interface ApiLocalizacao {
    @GET("/distritos/{id}")
    fun getDistritos(): Call<List<Local>>
}