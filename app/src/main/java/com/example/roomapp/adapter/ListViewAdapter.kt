package com.example.roomapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomapp.R
import com.example.roomapp.data.User
import com.example.roomapp.databinding.CustomRowBinding
import kotlinx.android.synthetic.main.custom_row.view.*
import kotlinx.android.synthetic.main.fragment_adding.view.*

class ListViewAdapter :RecyclerView.Adapter<ListViewAdapter.CustomListViewViewHolder>() {

    //create an empty list
    private var userList = emptyList<User>()


    class CustomListViewViewHolder(val binding: CustomRowBinding): RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomListViewViewHolder {


        val binding = CustomRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return CustomListViewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomListViewViewHolder, position: Int) {
        val current = userList[position]
        holder.binding.apply {
            textFirstName.text = current.firstName.toString()
            textLastName.text = current.lastName.toString()
            textAge.text = current.age.toString()
        }

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(user:List<User>){
        this.userList = user
        notifyDataSetChanged()
    }
}