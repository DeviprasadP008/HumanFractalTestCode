package com.humanfractal.testapplication.ui.base

import androidx.lifecycle.ViewModel
import com.humanfractal.testapplication.data.repository.BaseRepository

abstract class BaseViewModel(
    private val repository: BaseRepository
): ViewModel() {

}