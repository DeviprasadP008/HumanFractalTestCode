package com.humanfractal.testapplication.ui.sessionevening

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.humanfractal.testapplication.R
import com.humanfractal.testapplication.data.responses.ScheduleX
import com.humanfractal.testapplication.data.responses.Task
import com.humanfractal.testapplication.ui.base.BaseUIAdapter
import kotlinx.android.synthetic.main.fragment_sessionevening.*
import kotlinx.android.synthetic.main.fragment_sessionmorning.*

class SessioneveningFragment(var schedules: List<Task>) : Fragment() {

    val temperoryeveinglist: MutableList<Task> = arrayListOf()
    var eveningsessionList: List<ScheduleX> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        println("Data in Evening Session are " + schedules)
        return inflater.inflate(R.layout.fragment_sessionevening,container,false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        temperoryeveinglist.clear()
        for(item in schedules) {
            val list: List<ScheduleX> = item.scheduleList
            eveningsessionList = list.filter { s -> s.session.equals("EVENING") }
            if(!eveningsessionList.isEmpty()){
                temperoryeveinglist.add(item)
            }
        }
        rv_sessionevening.adapter = activity?.applicationContext?.let { BaseUIAdapter(temperoryeveinglist, it) }
        rv_sessionevening.layoutManager= LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.VERTICAL,false)
    }

}