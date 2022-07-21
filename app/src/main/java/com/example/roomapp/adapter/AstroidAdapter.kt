package com.example.astroidnasa.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomapp.R
import com.example.roomapp.model.Astroid
import kotlinx.android.synthetic.main.astroid_list.view.*

class AstroidAdapter ( val list:List<Astroid>):RecyclerView.Adapter<AstroidAdapter.AstroidViewHolder>() {

    inner class AstroidViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AstroidViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.astroid_list,parent,false)
        return AstroidViewHolder(view)



    }

    override fun onBindViewHolder(holder: AstroidViewHolder, position: Int) {

        holder.itemView.apply {
            astroid_date.text =list[position].name
        }


    }

    override fun getItemCount(): Int {
        return list.size
    }


}