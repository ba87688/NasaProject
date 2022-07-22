package com.example.roomapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomapp.R
import com.example.roomapp.api.Astroid
import kotlinx.android.synthetic.main.astroid_list.view.*

class AstroidsClassAdapter(val list:List<Astroid>, private val listener: AstroMadeAdapter.OnItemClickListener):
    RecyclerView.Adapter<AstroidsClassAdapter.AstroMadeViewHolder3>() {


    private var list2:List<Astroid> = mutableListOf()



    inner class AstroMadeViewHolder3(itemView: View): RecyclerView.ViewHolder(itemView),
        View.OnClickListener{
        init {

            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position:Int = adapterPosition
            if(position!= RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AstroMadeViewHolder3 {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.astroid_list,parent,false)
        return AstroMadeViewHolder3(view)    }

    override fun onBindViewHolder(holder: AstroMadeViewHolder3, position: Int) {
        Log.i("TAG", "onBindViewHolder: in adapater ${list.get(position)}")
        holder.itemView.apply {
            tv_close_approach_date.text = list[position].closeApproachDate
            astroid_date.text = list[position].codename
        }
    }
    override fun getItemCount(): Int {
        return list2.size
    }

}