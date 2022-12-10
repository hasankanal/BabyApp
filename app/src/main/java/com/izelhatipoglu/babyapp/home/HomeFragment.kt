package com.izelhatipoglu.babyapp.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.izelhatipoglu.babyapp.base.BaseFragment
import com.izelhatipoglu.babyapp.databinding.FragmentHomeBinding
import com.izelhatipoglu.babyapp.home.viewModel.HomeViewModel

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    private var month = 0

    override fun getViewModel() = HomeViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentHomeBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getData()
        observeData()
        viewModel.getMonth(month)
    }

    private fun observeData(){
        viewModel.homeData.observe(viewLifecycleOwner){ homeData ->
            binding.doctorNotes.text = homeData.doctorNotes
            binding.doctorDate.text= homeData.doctorAppointment
            month = homeData.month ?: 0

        }

        viewModel.monthData.observe(viewLifecycleOwner){ monthData ->
            binding.tvInfoUp.text = monthData.state
            binding.tvMonthDownInfo.text = monthData.advice
        }
    }
}
