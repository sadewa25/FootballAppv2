package com.codedirect.footballapps.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codedirect.footballapps.R
import com.codedirect.footballapps.client.model.EmployeeItems

class AdapterCustomRecyclerView(
    private var dataSet: ArrayList<EmployeeItems>,
    private val listener: (EmployeeItems?) -> Unit
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

        fun bindData(model: EmployeeItems, listener: (EmployeeItems?) -> Unit) {
            tvRecyclerName.text = model.nameUser
            tvRecyclerEmail.text = model.emailUser
            itemView.setOnClickListener {
                listener(model)
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