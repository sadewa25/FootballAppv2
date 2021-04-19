package com.codedirect.footballapps

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.codedirect.footballapps.client.model.EmployeeItems
import com.codedirect.footballapps.client.model.ResponseJSON
import com.codedirect.footballapps.retrofit.RetrofitBase
import kotlinx.android.synthetic.main.act_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_register)
    }

    fun regEmployee(view: View) {
        val ret = RetrofitBase().create()
        ret?.employee(
            EmployeeItems(
                nameUser = ed_reg_username.text.toString(),
                emailUser = ed_reg_email.text.toString(),
                passwordUser = ed_reg_password.text.toString()
            )
        )?.enqueue(object : Callback<ResponseJSON> {
            override fun onResponse(call: Call<ResponseJSON>, response: Response<ResponseJSON>) {
                Toast.makeText(this@RegisterAct, response.body()?.status, Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<ResponseJSON>, t: Throwable) {}
        })
    }
}