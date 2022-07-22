package com.example.roomapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.astroidnasa.retrofitmodels.X20150907
import com.example.roomapp.R
import com.example.roomapp.api.Astroid
import kotlinx.android.synthetic.main.astroid_list.view.*

class AdapterClassic (val list:List<Astroid>):
        RecyclerView.Adapter<AdapterClassic.AstroidViewHolder2>(){

        inner class AstroidViewHolder2(itemView: View): RecyclerView.ViewHolder(itemView){
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): AdapterClassic.AstroidViewHolder2 {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.astroid_list,parent,false)
            return AstroidViewHolder2(view)
        }

        override fun onBindViewHolder(holder: AdapterClassic.AstroidViewHolder2, position: Int) {
            holder.itemView.apply {
                astroid_date.text =list.get(position).codename
                tv_close_approach_date.text = list.get(position).codename
            }
        }

        override fun getItemCount(): Int {
            return list.size
        }




        interface OnItemClickListener{
            fun onItemClick(position: Int)
        }
    }
