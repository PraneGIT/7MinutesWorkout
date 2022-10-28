package com.example.a7minuteworkout

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minuteworkout.databinding.ItemExerciseListBinding

class ExcerciseStatusAdapter(val item:ArrayList<ExcerciseModel>):RecyclerView.Adapter<ExcerciseStatusAdapter.ViewHolder>() {
    class ViewHolder(binding:ItemExerciseListBinding):RecyclerView.ViewHolder(binding.root){
        val tvItem=binding.tvItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemExerciseListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model:ExcerciseModel=item[position]
        holder.tvItem.text=model.getId().toString()
        when{
            model.getIsSelected() ->{
                holder.tvItem.background=
                    ContextCompat.getDrawable(holder.itemView.context,R.drawable.item_circular_thin_accent_border)
                holder.tvItem.setTextColor(Color.parseColor("#212121"))
            }
            model.getIsCompleted() ->{
                holder.tvItem.background=
                    ContextCompat.getDrawable(holder.itemView.context,R.drawable.item_circular_color_accent_background)
                holder.tvItem.setTextColor(Color.parseColor("#FFFFFF"))
            }
            else ->{
                holder.tvItem.background=
                    ContextCompat.getDrawable(holder.itemView.context,R.drawable.item_circular_color_gray_background)
                holder.tvItem.setTextColor(Color.parseColor("#212121"))
            }
        }
    }

    override fun getItemCount(): Int {
       return item.size
    }
}