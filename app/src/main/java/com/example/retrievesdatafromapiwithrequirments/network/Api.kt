package com.example.retrievesdatafromapiwithrequirments.network

import com.example.retrievesdatafromapiwithrequirments.model.Data
import com.example.retrievesdatafromapiwithrequirments.model.DataItem

import retrofit2.http.GET

interface Api {

    @GET("hiring.json")
    suspend fun getMyData(): List<DataItem>
}