package com.example.retrievesdatafromapiwithrequirments.model

import com.squareup.moshi.Json

data class DataItem(

    @Json(name = "listId")
    val listId: Int,

    @Json(name = "name")
    val name: String?,

    @Json(name = "id")
    val id: Int
)
//data class ListResponse(@Json(name = "results")
//                        val result :List<DataItem>)