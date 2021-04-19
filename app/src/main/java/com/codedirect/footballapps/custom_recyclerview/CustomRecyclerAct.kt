package com.codedirect.footballapps.custom_recyclerview

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.afollestad.materialdialogs.MaterialDialog
import com.codedirect.footballapps.LoginAct
import com.codedirect.footballapps.R
import com.codedirect.footballapps.adapter.AdapterCustomRecyclerView
import com.codedirect.footballapps.client.model.EmployeeItems
import com.codedirect.footballapps.client.model.ResponseJSON
import com.codedirect.footballapps.retrofit.RetrofitBase
import com.codedirect.footballapps.session.SessionManager
import kotlinx.android.synthetic.main.act_custom_recycler.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CustomRecyclerAct : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var adapter: AdapterCustomRecyclerView
    private val sessionManager by lazy {
        SessionManager(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_custom_recycler)

        setupRecyclerView()
        setupOnRefreshLayout()
        setupEdittextOnChanged()
    }

    private fun setupEdittextOnChanged() {
        ed_custom_search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                adapter.filter.filter(p0.toString())
            }
        })
    }

    private fun setupOnRefreshLayout() {
        swipe_refresh.setOnRefreshListener(this)
    }

    private fun setupRecyclerView() {
        loading.visibility = View.VISIBLE
        //Calling the API
        val call = RetrofitBase().create()
        call?.getEmployee()?.enqueue(object : Callback<ResponseJSON> {
            override fun onResponse(call: Call<ResponseJSON>, response: Response<ResponseJSON>) {
                adapter =
                    AdapterCustomRecyclerView(response.body()?.employee as ArrayList<EmployeeItems>) {
                            employeeItems: EmployeeItems?, i: Int ->
                        when(i) {
                            0 -> onDeleteEmployee(employeeItems)
                            1 -> onPreview()
                        }
                    }
                //tambahan memilih tipe layout
                //linear layout
                rv_custom.layoutManager = LinearLayoutManager(applicationContext)
                //grid
                // rv_custom.layoutManager = GridLayoutManager(this, 2)
                rv_custom.adapter = adapter
                adapter.notifyDataSetChanged()
                loading.visibility = View.INVISIBLE
            }

            override fun onFailure(call: Call<ResponseJSON>, t: Throwable) {}
        })
    }

    private fun onPreview() {
        Toast.makeText(applicationContext, "Preview", Toast.LENGTH_SHORT).show()
    }

    private fun onDeleteEmployee(it: EmployeeItems?) {
        MaterialDialog(this).show {
            title(R.string.information)
            message(R.string.confirmation_delete)
            positiveButton(R.string.agree) { dialog ->
                val call = RetrofitBase().create()
                call?.deleteEmployee(it?.idUser.toString())
                    ?.enqueue(object : Callback<ResponseJSON> {
                        override fun onResponse(
                            call: Call<ResponseJSON>,
                            response: Response<ResponseJSON>
                        ) {
                            //untuk load kembali datanya
                            setupRecyclerView()
                        }

                        override fun onFailure(call: Call<ResponseJSON>, t: Throwable) {}
                    })
                dialog.dismiss()
            }
            negativeButton(R.string.disagree) { dialog -> dialog.dismiss() }
        }
    }

    override fun onRefresh() {
        setupRecyclerView()
        //untuk menghilangkan load more
        swipe_refresh.isRefreshing = false
    }

    fun onLogout(view: View) {
        sessionManager.setLogin(false)
        startActivity(Intent(this, LoginAct::class.java))
    }
}