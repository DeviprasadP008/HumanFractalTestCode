package com.humanfractal.testapplication.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.humanfractal.testapplication.data.repository.BaseRepository
import com.humanfractal.testapplication.data.repository.ScheduleRepository
import com.humanfractal.testapplication.ui.home.HomeViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(
    private val repository: BaseRepository
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository as ScheduleRepository) as T
            else -> throw IllegalArgumentException("ViewModelClass Not Found")
        }
    }

}