package com.codedirect.footballapps

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //public variable to change the data
    private var startPoint = 0

    //variabel dinamis untuk spinner
    private var value = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Mengarah ke layout yang dituju/ digunakan
        setContentView(R.layout.activity_main)

        //mengubah text id yang ada ke tulisan yg diinginkan
        /*tv_main_title.text = "Hello Sadewa!"
        tv_main_title.textSize = 30.0f //float ada tambahan f dibelakang nilai*/

        //Aksi ketika terjadi perubahan pada spinner
        setSpinnerChanged()

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
    }

    fun minus(v: View) {
        startPoint = startPoint.minus(value)
        tv_main_value.text = startPoint.toString()
    }

    fun reset(v: View) {
        startPoint = 0
        tv_main_value.text = startPoint.toString()
    }
}