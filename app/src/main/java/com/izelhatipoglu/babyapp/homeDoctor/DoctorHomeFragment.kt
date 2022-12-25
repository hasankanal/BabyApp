package com.izelhatipoglu.babyapp.homeDoctor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.izelhatipoglu.babyapp.R
import com.izelhatipoglu.babyapp.base.BaseFragment
import com.izelhatipoglu.babyapp.databinding.FragmentDoctorHomeBinding
import com.izelhatipoglu.babyapp.homeDoctor.adapter.MyAdapter
import com.izelhatipoglu.babyapp.homeDoctor.viewModel.DoctorHomeViewModel


class DoctorHomeFragment : BaseFragment<DoctorHomeViewModel, FragmentDoctorHomeBinding>() {

    var fabvisible = false

    private val auth = FirebaseAuth.getInstance()
    private lateinit var myAdapter: MyAdapter

    override fun getViewModel()= DoctorHomeViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    )= FragmentDoctorHomeBinding.inflate(inflater,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        floatingButton()
        observeData()
        viewModel.getData()
        myAdapter = MyAdapter(arrayListOf(), onItemClicked = object : MyAdapter.IsClicked {
            override fun clicked(name: String) {
                val action = DoctorHomeFragmentDirections.actionDoctorHomeFragmentToPregnantFragment(name)
                Navigation.findNavController(requireView()).navigate(action)
            }


        })
        binding.recylerView.layoutManager = LinearLayoutManager(context)
        binding.recylerView.adapter = myAdapter
    }

     fun floatingButton(){


        fabvisible = false

         binding.floating.setOnClickListener {
             if (!fabvisible) {

                 binding.floatingAdd.show()
                 binding.floatingOut.show()

                 binding.floatingAdd.visibility = View.VISIBLE
                 binding.floatingOut.visibility = View.VISIBLE
                 binding.floating.setImageDrawable(resources.getDrawable(R.drawable.vector_home))
                 fabvisible = true

             } else {
                 binding.floatingAdd.hide()
                 binding.floatingOut.hide()

                 binding.floatingAdd.visibility = View.GONE
                 binding.floatingOut.visibility = View.GONE
                 binding.floating.setImageDrawable(resources.getDrawable(R.drawable.vector_home))
                 fabvisible = false
             }
         }

         binding.floatingAdd.setOnClickListener {
             val action = DoctorHomeFragmentDirections.actionDoctorHomeFragmentToRegisterFragment()
             Navigation.findNavController(requireView()).navigate(action)
         }

         binding.floatingOut.setOnClickListener {
             auth.signOut()
             val action = DoctorHomeFragmentDirections.actionDoctorHomeFragmentToLoginFragment()
             Navigation.findNavController(requireView()).navigate(action)
         }
    }

    private fun observeData(){
        viewModel.doctorDataList.observe(viewLifecycleOwner){ momList ->
            if (momList.isNotEmpty()){
                myAdapter.updateList(momList)
            }

        }
    }
}