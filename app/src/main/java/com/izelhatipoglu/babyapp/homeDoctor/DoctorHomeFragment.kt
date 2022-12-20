package com.izelhatipoglu.babyapp.homeDoctor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.izelhatipoglu.babyapp.R
import com.izelhatipoglu.babyapp.base.BaseFragment
import com.izelhatipoglu.babyapp.databinding.FragmentDoctorHomeBinding
import com.izelhatipoglu.babyapp.databinding.FragmentHomeBinding
import com.izelhatipoglu.babyapp.homeDoctor.adapter.MyAdapter
import com.izelhatipoglu.babyapp.homeDoctor.viewModel.DoctorHomeViewModel
import com.izelhatipoglu.babyapp.model.DoctorModel

class DoctorHomeFragment : BaseFragment<DoctorHomeViewModel, FragmentDoctorHomeBinding>() {

    private lateinit var myAdapter: MyAdapter
    private lateinit var homeArrayList: ArrayList<DoctorModel>
    private lateinit var recylerView: RecyclerView

    override fun getViewModel()= DoctorHomeViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    )= FragmentDoctorHomeBinding.inflate(inflater,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getData()
        binding.recylerView.layoutManager = LinearLayoutManager(context)
        myAdapter = MyAdapter(viewModel.doctorDataList.value!!)
        binding.recylerView.adapter = myAdapter
    }

    private fun dataInitialize(){

    }

}