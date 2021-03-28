package com.codedirect.footballapps

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //public variable to change the data
    private var startPoint = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Mengarah ke layout yang dituju/ digunakan
        setContentView(R.layout.activity_main)

        //mengubah text id yang ada ke tulisan yg diinginkan
        /*tv_main_title.text = "Hello Sadewa!"
        tv_main_title.textSize = 30.0f //float ada tambahan f dibelakang nilai*/
    }

    fun adding(v: View) {
        startPoint = startPoint.plus(1)
        tv_main_value.text = startPoint.toString()
    }
    fun minus(v: View) {
        startPoint = startPoint.minus(1)
        tv_main_value.text = startPoint.toString()
    }
    fun reset(v: View) {
        startPoint = 0
        tv_main_value.text = startPoint.toString()
    }
}