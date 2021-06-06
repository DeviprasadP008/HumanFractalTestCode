package com.humanfractal.testapplication.data.repository

import com.humanfractal.testapplication.data.network.ScheduleApi

class ScheduleRepository(
    private val api: ScheduleApi
) : BaseRepository() {

    suspend fun getSchedule() = safeApiCall {
        api.getSchedule()
    }

}