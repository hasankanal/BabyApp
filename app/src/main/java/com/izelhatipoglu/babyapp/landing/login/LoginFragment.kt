package com.izelhatipoglu.babyapp.landing.login


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.auth.FirebaseAuth
import com.izelhatipoglu.babyapp.R
import com.izelhatipoglu.babyapp.base.BaseFragment
import com.izelhatipoglu.babyapp.databinding.FragmentLoginBinding
import com.izelhatipoglu.babyapp.home.HomeFragment
import com.izelhatipoglu.babyapp.landing.login.viewModel.LoginViewModel


class LoginFragment : BaseFragment<LoginViewModel, FragmentLoginBinding>() {

    override fun getViewModel() = LoginViewModel::class.java

    private val auth =  FirebaseAuth.getInstance()


    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    )= FragmentLoginBinding.inflate(inflater,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        handleClick()



    }

    private fun initUI(){

        val currentUser = auth.currentUser
        if(currentUser!= null){
            val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
            NavHostFragment.findNavController(this).navigate(action)
        }
    }

    private fun handleClick(){
        binding.buttonLogin.setOnClickListener {
            val mail = binding.mail.text.toString()
            val password = binding.password.text.toString()
            viewModel.login(mail, password)
            println("fff")
            viewModel.loginData.observe(viewLifecycleOwner){ loginData->
                println("44444")
                if (loginData){
                    println("içerde")
                    val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                    Navigation.findNavController(requireView()).navigate(action)
                }
            }
        }
    }



}