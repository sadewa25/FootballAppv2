package com.codedirect.footballapps.retrofit

import com.codedirect.footballapps.BuildConfig
import com.codedirect.footballapps.client.APIClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitBase {

    fun create(): APIClient? {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(APIClient::class.java)
    }

}