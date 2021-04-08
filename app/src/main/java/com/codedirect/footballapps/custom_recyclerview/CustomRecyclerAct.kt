package com.codedirect.footballapps.custom_recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.codedirect.footballapps.R
import com.codedirect.footballapps.adapter.AdapterCustomRecyclerView
import com.codedirect.footballapps.model.ItemCustomList
import kotlinx.android.synthetic.main.act_custom_recycler.*

class CustomRecyclerAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_custom_recycler)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val data = arrayListOf(
            ItemCustomList("Karpet", "Rp. 10.000", R.drawable.ic_circle),
            //apabila tidak urut dapat menggunakan seperti dibawah
            ItemCustomList(price = "Rp. 12.000", nama = "Nivea Men", image = R.drawable.ic_flood),
            ItemCustomList("Biore", image = R.drawable.ic_circle),
            ItemCustomList("Biore", image = R.drawable.ic_circle),
            ItemCustomList("Biore", image = R.drawable.ic_circle),
            ItemCustomList("Biore", image = R.drawable.ic_circle),
        )

        val adapter = AdapterCustomRecyclerView(data){
            Log.i("Datanya: ", it.toString())
        }
        //tambahan memilih tipe layout
        //linear layout
        rv_custom.layoutManager = LinearLayoutManager(this)
        //grid
        // rv_custom.layoutManager = GridLayoutManager(this, 2)
        rv_custom.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}