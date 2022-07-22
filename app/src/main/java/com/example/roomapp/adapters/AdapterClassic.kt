package com.example.roomapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.astroidnasa.retrofitmodels.X20150907
import com.example.roomapp.R
import com.example.roomapp.api.Astroid
import kotlinx.android.synthetic.main.astroid_list.view.*

class AdapterClassic (val list:List<Astroid>,private val listener: AdapterClassic.OnItemClickListener):
        RecyclerView.Adapter<AdapterClassic.AstroMadeViewHolder2>(){

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


    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): AdapterClassic.AstroMadeViewHolder2 {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.astroid_list,parent,false)
            return AstroMadeViewHolder2(view)
        }

        override fun onBindViewHolder(holder: AdapterClassic.AstroMadeViewHolder2, position: Int) {
            holder.itemView.apply {
                astroid_date.text =list.get(position).codename
                tv_close_approach_date.text = list.get(position).closeApproachDate

                if (list.get(position).isPotentiallyHazardous==true){
                    im_smile_or_nosmile.setImageResource(R.drawable.ic_status_potentially_hazardous)
                }
                else{
                    im_smile_or_nosmile.setImageResource(R.drawable.ic_status_normal)


                }
            }
        }

        override fun getItemCount(): Int {
            return list.size
        }



    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }
    }
