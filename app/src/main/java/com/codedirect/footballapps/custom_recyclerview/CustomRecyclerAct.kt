package com.codedirect.footballapps.custom_recyclerview

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.codedirect.footballapps.R
import com.codedirect.footballapps.adapter.AdapterCustomRecyclerView
import com.codedirect.footballapps.client.model.EmployeeItems
import com.codedirect.footballapps.client.model.ResponseJSON
import com.codedirect.footballapps.retrofit.RetrofitBase
import kotlinx.android.synthetic.main.act_custom_recycler.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CustomRecyclerAct : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_custom_recycler)

        setupRecyclerView()
        setupOnRefreshLayout()
    }

    private fun setupOnRefreshLayout() {
        swipe_refresh.setOnRefreshListener(this)
    }

    private fun setupRecyclerView() {
        /*val data = arrayListOf(
            ItemCustomList("Karpet", "Rp. 10.000", R.drawable.ic_circle),
            //apabila tidak urut dapat menggunakan seperti dibawah
            ItemCustomList(price = "Rp. 12.000", nama = "Nivea Men", image = R.drawable.ic_flood),
            ItemCustomList("Biore", image = R.drawable.ic_circle),
            ItemCustomList("Biore", image = R.drawable.ic_circle),
            ItemCustomList("Biore", image = R.drawable.ic_circle),
            ItemCustomList("Biore", image = R.drawable.ic_circle),
        )*/

        //Calling the API
        val call = RetrofitBase().create()
        call?.getEmployee()?.enqueue(object : Callback<ResponseJSON> {
            override fun onResponse(call: Call<ResponseJSON>, response: Response<ResponseJSON>) {
                val adapter =
                    AdapterCustomRecyclerView(response.body()?.employee as ArrayList<EmployeeItems>) {
                        Log.i("Datanya: ", it.toString())
                    }
                //tambahan memilih tipe layout
                //linear layout
                rv_custom.layoutManager = LinearLayoutManager(applicationContext)
                //grid
                // rv_custom.layoutManager = GridLayoutManager(this, 2)
                rv_custom.adapter = adapter
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<ResponseJSON>, t: Throwable) {}
        })
    }

    override fun onRefresh() {
        setupRecyclerView()
        //untuk menghilangkan load more
        swipe_refresh.isRefreshing = false
    }
}