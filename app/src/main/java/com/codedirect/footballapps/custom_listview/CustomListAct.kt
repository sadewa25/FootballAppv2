package com.codedirect.footballapps.custom_listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            ItemCustomList("Karpet", "Rp. 10.000"),
            //apabila tidak urut dapat menggunakan seperti dibawah
            ItemCustomList(price = "Rp. 12.000", nama = "Nivea Men"),
            ItemCustomList("Biore"),
        )
        val adapter = AdapterCustomList(data)
        list_custom.adapter = adapter
    }
}