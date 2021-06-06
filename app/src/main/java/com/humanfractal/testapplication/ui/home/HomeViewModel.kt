package com.humanfractal.testapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.humanfractal.testapplication.data.network.Resource
import com.humanfractal.testapplication.data.repository.ScheduleRepository
import com.humanfractal.testapplication.data.responses.Schedule
import com.humanfractal.testapplication.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: ScheduleRepository
) : BaseViewModel(repository) {

    private val _schedule: MutableLiveData<Resource<Schedule>> = MutableLiveData()
    val schedule: LiveData<Resource<Schedule>>
        get() = _schedule

    fun getSchedule() = viewModelScope.launch {
        _schedule.value = Resource.Loading
        _schedule.value = repository.getSchedule()
    }

}