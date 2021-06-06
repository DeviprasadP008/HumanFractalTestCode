package com.humanfractal.testapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.humanfractal.testapplication.databinding.FragmentHomeBinding
import com.humanfractal.testapplication.data.repository.ScheduleRepository
import com.humanfractal.testapplication.ui.base.BaseFragment
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayout
import com.humanfractal.testapplication.R
import com.humanfractal.testapplication.data.network.Resource
import com.humanfractal.testapplication.data.network.ScheduleApi
import com.humanfractal.testapplication.data.responses.Task
import com.humanfractal.testapplication.ui.base.BaseAdapter
import com.humanfractal.testapplication.ui.visible


class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding, ScheduleRepository>(){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressbar.visible(false)
        viewModel.getSchedule()
        viewModel.schedule.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Success -> {
                    binding.progressbar.visible(false)
                    updateUI(it.value.tasks)
                }
                is Resource.Loading -> {
                    binding.progressbar.visible(true)
                }
            }
        })
    }

    private fun updateUI(schedules: List<Task>) {
        with(binding) {
            tlHeader.addTab(tlHeader.newTab().setText(R.string.session_morning))
            tlHeader.addTab(tlHeader.newTab().setText(R.string.session_afternoon))
            tlHeader.addTab(tlHeader.newTab().setText(R.string.session_evening))
            tlHeader.addTab(tlHeader.newTab().setText(R.string.session_night))
            tlHeader.tabGravity = TabLayout.GRAVITY_CENTER
            tlHeader.tabMode = TabLayout.MODE_SCROLLABLE
            println("Result from Server is : " +  schedules)
            val adapter = getFragmentManager()?.let { getActivity()?.let { it1 -> BaseAdapter(it1.applicationContext, it, binding.tlHeader.tabCount, schedules) } }
            vpScheduletasks.adapter = adapter
            vpScheduletasks.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tlHeader))
            tlHeader.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    vpScheduletasks.currentItem = tab.position
                }
                override fun onTabUnselected(tab: TabLayout.Tab) {

                }
                override fun onTabReselected(tab: TabLayout.Tab) {

                }
            })

        }
    }


    override fun getViewModel() = HomeViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): ScheduleRepository {
        val token = null;
        val api = remoteDataSource.buildApi(ScheduleApi::class.java, token)
        return ScheduleRepository(api!!)
    }

}
