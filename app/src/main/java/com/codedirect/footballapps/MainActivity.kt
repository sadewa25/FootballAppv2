package com.codedirect.footballapps

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Mengarah ke layout yang dituju/ digunakan
        setContentView(R.layout.activity_main)

        //mengubah text id yang ada ke tulisan yg diinginkan
        /*tv_main_title.text = "Hello Sadewa!"
        tv_main_title.textSize = 30.0f //float ada tambahan f dibelakang nilai*/
    }
}