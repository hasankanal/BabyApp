package com.izelhatipoglu.babyapp.landing.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.auth.FirebaseAuth
import com.izelhatipoglu.babyapp.R
import com.izelhatipoglu.babyapp.base.BaseFragment
import com.izelhatipoglu.babyapp.databinding.FragmentRegisterBinding
import com.izelhatipoglu.babyapp.landing.register.viewModel.RegisterViewModel


class RegisterFragment : BaseFragment<RegisterViewModel, FragmentRegisterBinding>() {



    override fun getViewModel() = RegisterViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentRegisterBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
    }
}