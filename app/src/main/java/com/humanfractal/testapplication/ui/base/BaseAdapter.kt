package com.humanfractal.testapplication.ui.base

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.humanfractal.testapplication.data.responses.Task
import com.humanfractal.testapplication.ui.sessionafternoon.SessionafternoonFragment
import com.humanfractal.testapplication.ui.sessionevening.SessioneveningFragment
import com.humanfractal.testapplication.ui.sessionmorning.SessionmorningFragment
import com.humanfractal.testapplication.ui.sessionnight.SessionnightFragment

class BaseAdapter(var context: Context, fm: FragmentManager, var totalTabs: Int, var schedules: List<Task>) : FragmentPagerAdapter(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> { return SessionmorningFragment(schedules) }
            1 -> { return SessionafternoonFragment(schedules) }
            2 -> { return SessioneveningFragment(schedules) }
            3 -> { return SessionnightFragment(schedules) }
            else -> return SessionmorningFragment(schedules)
        }
    }

    override fun getCount(): Int {
        return totalTabs
    }

}