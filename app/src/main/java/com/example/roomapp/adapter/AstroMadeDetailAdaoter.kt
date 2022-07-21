package com.example.roomapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomapp.R
import com.example.roomapp.api.AstroidMade

class AstroMadeDetailAdaoter(val astroid:AstroidMade):RecyclerView.Adapter<AstroMadeDetailAdaoter.AstroMadeDetailViewHolder>() {


    inner class AstroMadeDetailViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AstroMadeDetailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.astroid_list,parent,false)
        return AstroMadeDetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: AstroMadeDetailViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 1
    }
}