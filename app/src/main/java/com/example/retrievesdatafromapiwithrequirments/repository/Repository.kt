package com.example.retrievesdatafromapiwithrequirments.repository

import com.example.retrievesdatafromapiwithrequirments.network.Api

class Repository(private var apiService:Api) {

    suspend fun getData() = apiService.getMyData()
}