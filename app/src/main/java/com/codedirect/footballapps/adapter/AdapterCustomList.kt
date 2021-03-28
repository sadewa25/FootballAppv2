package com.codedirect.footballapps.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.codedirect.footballapps.R
import com.codedirect.footballapps.model.ItemCustomList

class AdapterCustomList(val data: ArrayList<ItemCustomList>): BaseAdapter() {

    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(position: Int): Any {
        return data.get(position)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View? {
        var convertView = view
        if (convertView == null)
            convertView = LayoutInflater.from(parent?.context).inflate(R.layout.item_list_custom, parent, false)

        //ambil dlu data objeknya
        val item = getItem(position) as ItemCustomList
        // menghubungkan item widget layout dan adapter
        val tvItemName = convertView?.findViewById<TextView>(R.id.tv_item_custom)
        val tvItemPrice = convertView?.findViewById<TextView>(R.id.tv_price_custom)
        // set the value/ beri nilai pada objeknyua
        tvItemName?.text = item.nama
        tvItemPrice?.text = item.price
        return convertView
    }
}