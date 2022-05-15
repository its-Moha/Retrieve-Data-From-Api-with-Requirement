package com.example.retrievesdatafromapiwithrequirments.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrievesdatafromapiwithrequirments.model.DataItem
import com.example.retrievesdatafromapiwithrequirments.network.RetrofitInstance
import com.example.retrievesdatafromapiwithrequirments.repository.Repository
import com.example.retrievesdatafromapiwithrequirments.screenState.ScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class DataViewModel(private val repository: Repository
        = Repository(RetrofitInstance.api)): ViewModel() {

    private val getDataList = MutableLiveData<ScreenState<List<DataItem>>>()

    val getDataLiveData: MutableLiveData<ScreenState<List<DataItem>>>
        get() = getDataList

   fun getData(){

        //loading Data
        getDataList.postValue(ScreenState.Loading(null))



        viewModelScope.launch(Dispatchers.IO) {

            try {
                var client = repository.getData()
                val postList =  client
                // Filter out any items where "name" is blank or null.

                // Filter out any items where "name" is blank or null.
                val tempList: MutableList<DataItem> = ArrayList()
                for (data in postList) {
                    if (null!= data.name && !data.name.isEmpty() ) {

                        //sort by name
                        tempList.sortWith(Comparator { o1, o2 ->
                            // splitting name with respect to white space and setting the integer part in a local variable to compare
                            val n1: Int = o1.name!!.split(" ").get(1).toInt()
                            val n2: Int = o2.name!!.split(" ").get(1).toInt()
                            n1.compareTo(n2)
                        })
                        //sort by ListId
                        tempList.sortWith(Comparator { mainData, t1 ->
                            mainData.listId.compareTo(t1.listId)
                        })

                        //sort by id
                        // Collections.sort(tempList, (mainData, t1) -> Integer.parseInt(mainData.getId())-Integer.parseInt(t1.getId()));
                        tempList.add(data)

                        getDataList.postValue(ScreenState.Success(tempList))
                    }else{
                        //the code is not  working with out this empty else body :) :)
                    }}
            }catch (e:Exception){
                getDataList.postValue(ScreenState.Error(e.message.toString()))
            }

        }
    }
}

//for (data in postList) {
//           if (null != data.getName() && !data.getName().isEmpty()) {
//
//
//               //sort by name
//               Collections.sort(tempList) { o1, o2 ->
//                   // splitting name with respect to white space and setting the integer part in a local variable to compare
//                   val n1: Int = o1.getName().split(" ").get(1).toInt()
//                   val n2: Int = o2.getName().split(" ").get(1).toInt()
//                   n1.compareTo(n2)
//               }
//               //sort by ListId
//               Collections.sort(tempList) { mainData, t1 ->
//                   mainData.getListId().compareTo(t1.getListId())
//               }
//
//
//               //sort by id
//               // Collections.sort(tempList, (mainData, t1) -> Integer.parseInt(mainData.getId())-Integer.parseInt(t1.getId()));
//               tempList.add(data)
//           }
//       }




// try {
//                var client = repository.getData()
//              for (data in client){
//                  null != data.name && data.name.isEmpty()
//                  getDataList.postValue(ScreenState.Success(data))
//              }