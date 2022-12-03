package com.izelhatipoglu.babyapp.home

import android.content.Context
import android.os.Bundle
import android.system.Os.open
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.izelhatipoglu.babyapp.R
import com.izelhatipoglu.babyapp.base.BaseFragment
import com.izelhatipoglu.babyapp.databinding.FragmentHomeBinding
import com.izelhatipoglu.babyapp.home.viewModel.HomeViewModel
import kotlinx.android.synthetic.*
import java.nio.channels.AsynchronousFileChannel.open
import java.nio.channels.DatagramChannel.open
import java.nio.channels.SocketChannel.open

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {


    override fun getViewModel() = HomeViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentHomeBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getData()
        observeData()

    }

    private fun observeData(){
        viewModel.homeData.observe(viewLifecycleOwner){ homeData ->
            binding.tvInfoUp.text = homeData.doctorName
            binding.doctorNotes.text = homeData.doctorNotes

        }
    }
}
