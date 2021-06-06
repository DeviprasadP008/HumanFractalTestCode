package com.humanfractal.testapplication.data.network

import androidx.lifecycle.LiveData
import com.humanfractal.testapplication.data.responses.Schedule
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ScheduleApi {

    @GET("schedule")
    suspend fun getSchedule(): Schedule

}
