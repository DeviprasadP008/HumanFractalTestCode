package com.humanfractal.testapplication.ui.sessionafternoon

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
import kotlinx.android.synthetic.main.fragment_sessionafternoon.*
import kotlinx.android.synthetic.main.fragment_sessionmorning.*

class SessionafternoonFragment(var schedules: List<Task>) : Fragment() {

    val temperoryafternoonlist: MutableList<Task> = arrayListOf()
    var afternoonsessionList: List<ScheduleX> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        println("Data in Afternoon Session are " + schedules)
        return inflater.inflate(R.layout.fragment_sessionafternoon,container,false)
    }


    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        temperoryafternoonlist.clear()
        for(item in schedules) {
            val list: List<ScheduleX> = item.scheduleList
            afternoonsessionList = list.filter { s -> s.session.equals("AFTERNOON") }
            if(!afternoonsessionList.isEmpty()){
                temperoryafternoonlist.add(item)
            }
        }
        rv_sessionafternoon.adapter = activity?.applicationContext?.let { BaseUIAdapter(temperoryafternoonlist, it) }
        rv_sessionafternoon.layoutManager= LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.VERTICAL,false)
    }

}