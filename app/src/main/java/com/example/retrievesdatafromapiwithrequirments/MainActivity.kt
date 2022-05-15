package com.example.retrievesdatafromapiwithrequirments

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.retrievesdatafromapiwithrequirments.adapter.RecyclerAdapter
import com.example.retrievesdatafromapiwithrequirments.databinding.ActivityMainBinding
import com.example.retrievesdatafromapiwithrequirments.model.DataItem
import com.example.retrievesdatafromapiwithrequirments.screenState.ScreenState
import com.example.retrievesdatafromapiwithrequirments.viewModel.DataViewModel
import com.google.android.material.snackbar.Snackbar
import java.util.*

class MainActivity : AppCompatActivity() {


    lateinit var dataViewModel: ViewModel
    lateinit var adapter:RecyclerAdapter
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getList()
    }

    fun getList(){

        dataViewModel = ViewModelProvider(this).get(DataViewModel::class.java)

        (dataViewModel as DataViewModel).getData()

        (dataViewModel as DataViewModel).getDataLiveData.observe(this, Observer {
            initAdapter(it)
        })
    }

    private fun initAdapter(state: ScreenState<List<DataItem>>) {

        when (state) {
            is ScreenState.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            is ScreenState.Success -> {
                binding.progressBar.visibility = View.GONE
                if (state.data != null) {

                    adapter = RecyclerAdapter(state.data as List<DataItem>)
                    binding.recyclerView.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
            }
            is ScreenState.Error -> {
                binding.progressBar.visibility = View.GONE
                val view = binding.progressBar.rootView
                Snackbar.make(view,state.message!!,Snackbar.LENGTH_SHORT).show()

            }
        }

    }

}

/**

val postList: List<DataItem> = state.data as List<DataItem>
// Filter out any items where "name" is blank or null.
// Filter out any items where "name" is blank or null.
val tempList: MutableList<DataItem> = ArrayList()

for (data in postList) {
if (null != data.name && data.name.isEmpty()) {

//sort by name
tempList.sortWith { o1, o2 ->
// splitting name with respect to white space and setting the integer part in a local variable to compare
val n1 = o1.name.split(" ")[1].toInt()
val n2 = o2.name.split(" ")[1].toInt()
n1.compareTo(n2)
}
//sort by ListId
tempList.sortWith { mainData, t1 ->
mainData.listId.compareTo(t1.listId)
}


//sort by id
// Collections.sort(tempList, (mainData, t1) -> Integer.parseInt(mainData.getId())-Integer.parseInt(t1.getId()));
tempList.add(data)
}
}
 ***/