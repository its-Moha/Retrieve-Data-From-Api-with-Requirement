package com.example.retrievesdatafromapiwithrequirments.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrievesdatafromapiwithrequirments.databinding.ListItemBinding
import com.example.retrievesdatafromapiwithrequirments.model.DataItem
import com.example.retrievesdatafromapiwithrequirments.screenState.ScreenState


class RecyclerAdapter(private val userList: List<DataItem>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var name = userList[position]
        holder.bind(name)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    inner class ViewHolder(var listItemBinding: ListItemBinding): RecyclerView.ViewHolder(listItemBinding.root) {

        fun bind(mydata: DataItem) {

            listItemBinding.listId.text = ("ListId: ${mydata.listId}")
            listItemBinding.name.text = ("Name: ${mydata.name}")
            listItemBinding.id.text = ("Id: ${mydata.id}")
        }
    }}