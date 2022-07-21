package com.example.roomapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomapp.R
import com.example.roomapp.api.AstroidMade
import kotlinx.android.synthetic.main.astroid_list.view.*
//, private val listener: AstroMadeAdapter.OnItemClickListener
class AstroMadeAdapter (val list:List<AstroidMade>, private val listener: AstroMadeAdapter.OnItemClickListener):
        RecyclerView.Adapter<AstroMadeAdapter.AstroMadeViewHolder2>(){
        private var list2:List<AstroidMade> = mutableListOf()
        inner class AstroMadeViewHolder2(itemView: View): RecyclerView.ViewHolder(itemView),
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


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AstroMadeViewHolder2 {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.astroid_list,parent,false)
        return AstroMadeViewHolder2(view)
    }

    override fun onBindViewHolder(holder: AstroMadeViewHolder2, position: Int) {
        holder.itemView.apply {
            astroid_name.text =list[position].astronomical.toString()
            astroid_date.text =list[position].id.toString()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setAstroid(astroids:List<AstroidMade>){
        this.list2 = astroids
        notifyDataSetChanged()

    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

}