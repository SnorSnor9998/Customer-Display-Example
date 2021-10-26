package com.snor.customerdisplayexample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.snor.customerdisplayexample.databinding.RvSampleBinding

class RvAdapter : RecyclerView.Adapter<RvAdapter.ViewHolder>() {

    private var list = emptyList<String>()

    class ViewHolder(val binding : RvSampleBinding): RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RvSampleBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = list[position]
        holder.binding.txt.text = current
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(list : List<String>){
        this.list = list
        notifyDataSetChanged()
    }

}