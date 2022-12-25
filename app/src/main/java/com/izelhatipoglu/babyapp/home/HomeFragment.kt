package com.izelhatipoglu.babyapp.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.auth.FirebaseAuth
import com.izelhatipoglu.babyapp.base.BaseFragment
import com.izelhatipoglu.babyapp.databinding.FragmentHomeBinding
import com.izelhatipoglu.babyapp.home.viewModel.HomeViewModel
import com.squareup.picasso.Picasso

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    private var month = "0"
    private val auth = FirebaseAuth.getInstance()

    override fun getViewModel() = HomeViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentHomeBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getData()
        observeData()
        handleClick()

    }

    private fun handleClick(){
        binding.floatingout.setOnClickListener {
            auth.signOut()
            val action = HomeFragmentDirections.actionHomeFragmentToLoginFragment()
            NavHostFragment.findNavController(this).navigate(action)
        }
    }

    private fun observeData(){
        viewModel.homeData.observe(viewLifecycleOwner){ homeData ->
            binding.doctorNotes.text = homeData.doctorNotes
            binding.doctorDate.text= homeData.doctorAppointment
            month = homeData.month ?: "0"
            if (homeData.photo != "" && homeData.photo?.isNotEmpty() == true){
                Picasso.get().load(homeData.photo).into(binding.firebaseView)
            }else{
                binding.firebaseView.visibility = View.GONE
            }
            binding.firebaseView.setImageURI(homeData.photo?.toUri())
            viewModel.getMonth(month)
        }

        viewModel.monthData.observe(viewLifecycleOwner){ monthData ->
            println("info::: ${monthData.state}")
            println("advice:: ${monthData.advice}")
            binding.tvInfoUp.text = monthData.state
            binding.tvMonthDownInfo.text = monthData.advice
        }
    }
}
