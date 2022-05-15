package com.example.retrievesdatafromapiwithrequirments.screenState

import com.example.retrievesdatafromapiwithrequirments.model.DataItem

sealed class ScreenState<T>(val data: Any? = null, val message:String? = null)  {

    class Success<T>(data:T?): ScreenState<T> (data)

    class Loading<T> (data:T? = null ) : ScreenState<T>(data)

    class Error<T>  (message:String,data:T? = null ) : ScreenState<T> (data,message)
}
