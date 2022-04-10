package com.example.quizapp

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.databinding.ItemBinding

open class RecylerView(
    var list: List<ResultDataClass>,var context: Context
): RecyclerView.Adapter<RecylerView.ViewHolder>() {

    open class ViewHolder(val binding: ItemBinding)
        :RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding=ItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvQuestionno.text = "Question.No: ${position + 1}"
        if(list.get(position).result==1) holder.binding.tvResult.setBackgroundResource(R.drawable.right_circle)
        else if(list.get(position).result==-1)
            holder.binding.tvResult.setBackgroundResource(R.drawable.wrong_circle)
        else
            holder.binding.tvResult.setBackgroundResource(R.drawable.nooption_selected)
        if(position==list.size-1) {
            holder.binding.view.visibility = View.GONE

        }
    }
    override fun getItemCount(): Int {
        return list.size

    }
}