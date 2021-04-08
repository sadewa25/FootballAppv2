package com.codedirect.footballapps.client

import com.codedirect.footballapps.BuildConfig
import com.codedirect.footballapps.client.model.EmployeeItems
import com.codedirect.footballapps.client.model.ResponseJSON
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface APIClient {
    @POST("login/${BuildConfig.KEY}")
    fun login(
        @Body item: EmployeeItems
    ): Call<ResponseJSON>
}