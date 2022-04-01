package com.example.nbabettingapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nbabettingapp.R
import com.example.nbabettingapp.entities.Param

class ParamsPlayerItemAdapter(var context: Context?): ListAdapter<Param, ParamsPlayerItemAdapter.ViewHolder>(Callback()) {

    class Callback: DiffUtil.ItemCallback<Param>(){
        override fun areItemsTheSame(oldItem: Param, newItem: Param): Boolean {
            return oldItem.name == newItem.name
        }
        override fun areContentsTheSame(oldItem: Param, newItem: Param): Boolean {
            return oldItem == newItem
        }

    }
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private var nameParam: TextView = view.findViewById(R.id.name_param)
        private var valueParam: TextView = view.findViewById(R.id.value_param)

        fun bind(data: Param){
            valueParam.text = data.value
            nameParam.text = data.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list_params, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}