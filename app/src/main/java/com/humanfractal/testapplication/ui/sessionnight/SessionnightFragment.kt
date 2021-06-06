package com.humanfractal.testapplication.ui.sessionnight

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
import kotlinx.android.synthetic.main.fragment_sessionnight.*

class SessionnightFragment(var schedules: List<Task>) : Fragment() {

    val temperorynightlist: MutableList<Task> = arrayListOf()
    var nightsessionList: List<ScheduleX> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        println("Data in Night Session are " + schedules)
        return inflater.inflate(R.layout.fragment_sessionnight,container,false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        temperorynightlist.clear()
        for(item in schedules) {
            val list: List<ScheduleX> = item.scheduleList
            nightsessionList = list.filter { s -> s.session.equals("NIGHT") }
            if(!nightsessionList.isEmpty()){
                temperorynightlist.add(item)
            }
        }
        rv_sessionnight.adapter = activity?.applicationContext?.let { BaseUIAdapter(temperorynightlist, it) }
        rv_sessionnight.layoutManager= LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.VERTICAL,false)
    }

}