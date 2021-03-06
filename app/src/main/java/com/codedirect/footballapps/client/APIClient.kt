package com.codedirect.footballapps.client

import com.codedirect.footballapps.BuildConfig
import com.codedirect.footballapps.client.model.EmployeeItems
import com.codedirect.footballapps.client.model.ResponseJSON
import retrofit2.Call
import retrofit2.http.*

interface APIClient {
    @POST("login/${BuildConfig.KEY}")
    fun login(
        @Body item: EmployeeItems
    ): Call<ResponseJSON>

    @POST("employee/${BuildConfig.KEY}")
    fun employee(
        @Body item: EmployeeItems
    ): Call<ResponseJSON>

    @GET("employee/${BuildConfig.KEY}")
    fun getEmployee(): Call<ResponseJSON>

    @DELETE("employee/{id}${BuildConfig.KEY}")
    fun deleteEmployee(
        @Path("id") id: String
    ): Call<ResponseJSON>

}