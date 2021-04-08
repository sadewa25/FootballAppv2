package com.codedirect.footballapps.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codedirect.footballapps.R
import com.codedirect.footballapps.model.ItemCustomList

class AdapterCustomRecyclerView(
    private val dataSet: ArrayList<ItemCustomList>,
    private val listener: (ItemCustomList?) -> Unit
) :
    RecyclerView.Adapter<AdapterCustomRecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler_custom, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(dataSet.get(position), listener)
    }

    override fun getItemCount(): Int = dataSet.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvRecyclerName: TextView = view.findViewById(R.id.tv_recycler_name)

        fun bindData(model: ItemCustomList, listener: (ItemCustomList?) -> Unit) {
            tvRecyclerName.text = model.nama
            itemView.setOnClickListener {
                listener(model)
            }
        }
    }
}