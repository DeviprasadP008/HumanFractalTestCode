package com.humanfractal.testapplication.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {

    companion object {
        private const val BASE_URL = "https://38rhabtq01.execute-api.ap-south-1.amazonaws.com/dev/"
    }

    fun<ScheduleApi> buildApi(
        api: Class<ScheduleApi>,
        authToken: String? = null
    ) : ScheduleApi? {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }
}