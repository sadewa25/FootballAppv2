package com.codedirect.footballapps.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codedirect.footballapps.R
import com.codedirect.footballapps.client.model.EmployeeItems
import com.squareup.picasso.Picasso

class AdapterCustomRecyclerView(
    private var dataSet: ArrayList<EmployeeItems>,
    private val listener: (EmployeeItems?, Int) -> Unit
) :
    RecyclerView.Adapter<AdapterCustomRecyclerView.ViewHolder>(), Filterable {

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
        val tvRecyclerEmail: TextView = view.findViewById(R.id.tv_recycler_email)
        val ivRecycler: ImageView = view.findViewById(R.id.iv_item_recyler)
        val ivIcon: ImageView = view.findViewById(R.id.iv_item_recycler_icon)

        fun bindData(model: EmployeeItems, listener: (EmployeeItems?, Int) -> Unit) {
            tvRecyclerName.text = model.nameUser
            tvRecyclerEmail.text = model.emailUser

            Picasso.get()
                .load("https://cdn0-production-images-kly.akamaized.net/gkuKdurxPqRw8u5CgAddYosm7Zk=/640x640/smart/filters:quality(75):strip_icc():format(jpeg)/kly-media-production/medias/2721201/original/034457300_1549364965-20190205_Persebaya_Surabaya.jpg")
                .placeholder(R.mipmap.ic_launcher)
                .into(ivIcon);

            itemView.setOnClickListener {
                listener(model, 0)
            }
            ivRecycler.setOnClickListener {
                listener(model, 1)
            }
        }
    }

    //dataBackup array
    val dataFiltered = dataSet

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                //pengembalian nilai awal array
                if (charString.isEmpty())
                    dataSet = dataFiltered
                else {
                    val filteredList = arrayListOf<EmployeeItems>()
                    if (dataFiltered != null) {
                        for (row in dataFiltered) {
                            //kriteria nama user, dimana huruf besar kecil akan diabaikan
                            if (row.nameUser?.toLowerCase()?.contains(charString.toLowerCase())
                                    .toString().toBoolean()
                            )
                                filteredList.add(row)
                        }
                    }
                    dataSet = filteredList
                }

                val filterResults = FilterResults()
                filterResults.values = dataSet
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                dataSet = filterResults.values as ArrayList<EmployeeItems>
                notifyDataSetChanged()
            }
        }
    }
}