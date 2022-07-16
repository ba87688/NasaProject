package com.example.astroidnasa.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.astroidnasa.retrofitmodels.X20150907
import com.example.roomapp.R
import kotlinx.android.synthetic.main.astroid_list.view.*


class AstroidAdapter2 ( val list:List<X20150907>,
    private val listener:OnItemClickListener):
    RecyclerView.Adapter<AstroidAdapter2.AstroidViewHolder2>(){

    inner class AstroidViewHolder2(itemView: View): RecyclerView.ViewHolder(itemView),
    View.OnClickListener{
        init {

            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position:Int = adapterPosition
            if(position!=RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AstroidAdapter2.AstroidViewHolder2 {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.astroid_list,parent,false)
        return AstroidViewHolder2(view)
    }

    override fun onBindViewHolder(holder: AstroidAdapter2.AstroidViewHolder2, position: Int) {
        holder.itemView.apply {
            astroid_name.text =list[position].absolute_magnitude_h.toString()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }




    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }
}
//
//class AstroidListener(val clickListner: (astroidId:Long)->Unit){
//    fun onClick(astroid: X20150907) = clickListner(astroid.id)
//}