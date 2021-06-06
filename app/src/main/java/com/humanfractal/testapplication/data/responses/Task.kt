package com.humanfractal.testapplication.data.responses

data class Task(
    val drug: Drug,
    val duration: Int,
    val frequency: Int,
    val scheduleList: List<ScheduleX>,
    val taskCd: String,
    val type: String,
    val video: Video
)