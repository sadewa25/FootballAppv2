package com.codedirect.footballapps.custom_listview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codedirect.footballapps.R
import com.codedirect.footballapps.adapter.AdapterCustomList
import com.codedirect.footballapps.model.ItemCustomList
import kotlinx.android.synthetic.main.act_custom_list.*

class CustomListAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_custom_list)

        setupCustomList()
    }

    private fun setupCustomList() {
        val data = arrayListOf(
            ItemCustomList("Karpet", "Rp. 10.000", R.drawable.ic_circle),
            //apabila tidak urut dapat menggunakan seperti dibawah
            ItemCustomList(price = "Rp. 12.000", nama = "Nivea Men", image = R.drawable.ic_flood),
            ItemCustomList("Biore", image = R.drawable.ic_circle),
        )
        val adapter = AdapterCustomList(data)
        list_custom.adapter = adapter
    }
}