package com.izelhatipoglu.babyapp.landing.login


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.izelhatipoglu.babyapp.base.BaseFragment
import com.izelhatipoglu.babyapp.databinding.FragmentLoginBinding
import com.izelhatipoglu.babyapp.landing.login.viewModel.LoginViewModel


class LoginFragment : BaseFragment<LoginViewModel, FragmentLoginBinding>() {


    override fun getViewModel() = LoginViewModel::class.java
    private val auth = FirebaseAuth.getInstance()


    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    )= FragmentLoginBinding.inflate(inflater,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        handleClick()
        observeData()
    }

    private fun initUI(){

    }

    private fun handleClick(){
        binding.buttonLogin.setOnClickListener {
            val mail = binding.mail.text.toString()
            val password = binding.password.text.toString()
            viewModel.login(mail, password)


        }
    }

    private fun observeData(){
        viewModel.loginData.observe(viewLifecycleOwner){ loginData->
            if (loginData){
                println("kullanıcı: ${auth.currentUser}")
                val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                Navigation.findNavController(requireView()).navigate(action)

            }
        }
    }

}