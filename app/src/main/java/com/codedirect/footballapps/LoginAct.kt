package com.codedirect.footballapps

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.codedirect.footballapps.client.model.EmployeeItems
import com.codedirect.footballapps.client.model.ResponseJSON
import com.codedirect.footballapps.retrofit.RetrofitBase
import kotlinx.android.synthetic.main.act_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_login)

        //menggunakan cara yang ke 2
        btn_login.setOnClickListener {
            //to Access EndPoint URL
            if (!ed_login_email.text.isNullOrEmpty() && !ed_login_password.text.isNullOrEmpty())
                getLogin()
            else
                Toast.makeText(applicationContext, "Terdapat Data Kosong", Toast.LENGTH_SHORT)
                    .show()
        }
    }

    private fun getLogin() {
        loading.visibility = View.VISIBLE
        //inisialisasi retrofit
        val r = RetrofitBase().create()
        // call the endpoint
        r?.login(
            EmployeeItems(
                emailUser = ed_login_email.text.toString(),
                passwordUser = ed_login_password.text.toString()
            )
        )?.enqueue(object : Callback<ResponseJSON> {
            override fun onResponse(call: Call<ResponseJSON>, response: Response<ResponseJSON>) {
                if (response.body()?.employee.isNullOrEmpty())
                    Toast.makeText(
                        applicationContext,
                        "Username/Password Salah",
                        Toast.LENGTH_SHORT
                    ).show()
                else
                    navigateToMainUI()
                loading.visibility = View.INVISIBLE
            }

            override fun onFailure(call: Call<ResponseJSON>, t: Throwable) {
                /*Error Ketika, domain salah, atau permission kurang, atau regulasi http*/
                Log.i("Info Failure: ", t.message.toString())
                loading.visibility = View.INVISIBLE
            }
        })
    }

    private fun navigateToMainUI() {
        startActivity(Intent(this, MainActivity::class.java))
        // mendestroy/ menghilangkan aktifitas saat ini
        finish()
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