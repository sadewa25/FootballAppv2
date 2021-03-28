package com.codedirect.footballapps

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //public variable to change the data
    private var startPoint = 0

    //variabel dinamis untuk spinner
    private var value = 1

    //variabel dinamis untuk arraynya
    private var dataArray = arrayListOf<String>()

    // untuk penundaan inisialisasi vairabel
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Mengarah ke layout yang dituju/ digunakan
        setContentView(R.layout.activity_main)

        //mengubah text id yang ada ke tulisan yg diinginkan
        /*tv_main_title.text = "Hello Sadewa!"
        tv_main_title.textSize = 30.0f //float ada tambahan f dibelakang nilai*/

        //Aksi ketika terjadi perubahan pada spinner
        setSpinnerChanged()
        //aksi untuk listview
        setListView()
    }

    private fun setListView() {
        //datanya
        // val data = arrayListOf("Arsenal", "Persebaya", "Persib")
        //wadahnya atau adapternya atau tempat desain yg diharapkan
        adapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, dataArray)
        //hubungkan listview dg wadahnya
        list_main_simple.adapter = adapter

        //aksi pada tiap item
        list_main_simple.setOnItemClickListener { adapterView, view, i, l ->
            Toast.makeText(applicationContext, dataArray[i], Toast.LENGTH_SHORT).show()
        }
    }

    private fun setSpinnerChanged() {
        spin_main.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, v: View?, position: Int, id: Long) {
                when (position) {
                    0 -> value = 1
                    1 -> value = 2
                    2 -> value = 3
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }

    fun adding(v: View) {
        startPoint = startPoint.plus(value)
        tv_main_value.text = startPoint.toString()

        dataArray.add(startPoint.toString())
        //refresh data perubahan
        adapter.notifyDataSetChanged()
    }

    fun minus(v: View) {
        startPoint = startPoint.minus(value)
        tv_main_value.text = startPoint.toString()

        if (!dataArray.isNullOrEmpty())
            dataArray.removeAt(dataArray.size - 1)
        adapter.notifyDataSetChanged()
    }

    fun reset(v: View) {
        startPoint = 0
        tv_main_value.text = startPoint.toString()

        dataArray.clear()
        adapter.notifyDataSetChanged()
    }
}