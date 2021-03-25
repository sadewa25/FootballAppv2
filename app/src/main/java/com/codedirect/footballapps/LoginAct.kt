package com.codedirect.footballapps

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.act_login.*

class LoginAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_login)

        //menggunakan cara yang ke 2
        btn_login.setOnClickListener {
            if (ed_login_email.text.toString() == "admin" && ed_login_password.text.toString() == "admin") {
                startActivity(Intent(this, MainActivity::class.java))
                // mendestroy/ menghilangkan aktifitas saat ini
                finish()
            } else
                Toast.makeText(applicationContext, "Username/ Password Salah", Toast.LENGTH_SHORT)
                    .show()
        }
    }

    fun toRegister(view: View) {
        // Return yg dapat diketahui oleh pengguna
        // Toast.makeText(applicationContext, "Hello Click", Toast.LENGTH_SHORT).show()
        // Hampir sama dengan Console.log
        Log.i("Informasinya: ", "Berhasil di klik")
        //Pemindahan Halaman, didalam Intent adalah this, kemudian kelas tujuan
        startActivity(Intent(this, RegisterAct::class.java))
    }
}