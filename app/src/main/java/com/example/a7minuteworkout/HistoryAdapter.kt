package com.example.a7minuteworkout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minuteworkout.databinding.ItemRowBinding

class HistoryAdapter(private val items:ArrayList<String>):RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {


    class ViewHolder(binding:ItemRowBinding):RecyclerView.ViewHolder(binding.root){
        val llHistoryItemMain=binding.llMain
        val tvItem=binding.tvDate
        val tvPosition=binding.tvId
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val date:String=items.get(position)
        holder.tvPosition.text=(position+1).toString()
        holder.tvItem.text=date

        if(position%2==0){
            holder.llHistoryItemMain.setBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.lightGrey))
        }else{
            holder.llHistoryItemMain.setBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.white))
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}