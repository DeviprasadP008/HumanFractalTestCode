package com.humanfractal.testapplication.ui.sessionmorning

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
import kotlinx.android.synthetic.main.fragment_sessionmorning.*

class SessionmorningFragment(var schedules: List<Task>) : Fragment() {

    val temperorymorninglist: MutableList<Task> = arrayListOf()
    var morningsessionList: List<ScheduleX> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        println("Data in Morning Session are " + schedules)
        return inflater.inflate(R.layout.fragment_sessionmorning,container,false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        temperorymorninglist.clear()
        for(item in schedules) {
            val list: List<ScheduleX> = item.scheduleList
            morningsessionList = list.filter { s -> s.session.equals("MORNING") }
            if(!morningsessionList.isEmpty()){
                temperorymorninglist.add(item)
            }
        }
        rv_sessionmorning.adapter = activity?.applicationContext?.let { BaseUIAdapter(temperorymorninglist, it) }
        rv_sessionmorning.layoutManager= LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.VERTICAL,false)
    }



}
