package com.izelhatipoglu.babyapp.landing.register

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import com.izelhatipoglu.babyapp.base.BaseFragment
import com.izelhatipoglu.babyapp.databinding.FragmentRegisterBinding
import com.izelhatipoglu.babyapp.landing.register.viewModel.RegisterViewModel


class RegisterFragment : BaseFragment<RegisterViewModel, FragmentRegisterBinding>() {


    private lateinit var sharedPreference : SharedPreferences
    private var doctorEmail = ""
    private var doctorPassword = ""

    override fun getViewModel() = RegisterViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentRegisterBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreference = requireActivity().getSharedPreferences("com.izelhatipoglu.babyapp", Context.MODE_PRIVATE)
        doctorEmail = sharedPreference.getString("doctorEmail","").toString()
        doctorPassword = sharedPreference.getString("doctorPassword","").toString()

        println("doctorEmail :: $doctorEmail")
        println("doctorPassword:: $doctorPassword")

        handleClick()
        observeData()
    }

    private fun handleClick(){
        binding.buttonLogin.setOnClickListener {
            val mail = binding.mail.text.toString()
            val password = binding.password.text.toString()
            viewModel.register(mail, password)
        }
    }

    private fun observeData(){
        viewModel.isRegister.observe(viewLifecycleOwner){ isRegister ->
            if(isRegister){
                val action = RegisterFragmentDirections.actionRegisterFragmentToInformationRegisterFragment()
                NavHostFragment.findNavController(this).navigate(action)
            }

        }
        viewModel.isLoading.observe(viewLifecycleOwner){ isLoading ->
            if (isLoading){
                binding.clLogin.isEnabled = false
                binding.progressBar.visibility = View.VISIBLE
            }else{
                binding.clLogin.isEnabled = true
                binding.progressBar.visibility = View.GONE
            }

        }
        viewModel.loginData.observe(viewLifecycleOwner) { loginData ->
            if (loginData) {
                Toast.makeText(context,"Registered in the system", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context,"Failed to register in the system!", Toast.LENGTH_SHORT).show()
            }
        }
    }


}