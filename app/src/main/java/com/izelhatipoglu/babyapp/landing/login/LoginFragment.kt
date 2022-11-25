package com.izelhatipoglu.babyapp.landing.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.izelhatipoglu.babyapp.MainActivity
import com.izelhatipoglu.babyapp.R
import com.izelhatipoglu.babyapp.base.BaseFragment
import com.izelhatipoglu.babyapp.databinding.FragmentLoginBinding
import com.izelhatipoglu.babyapp.landing.login.viewModel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*


class LoginFragment : BaseFragment<LoginViewModel, FragmentLoginBinding>() {

    override fun getViewModel() = LoginViewModel::class.java

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

    }

    private fun handleClick(){
        binding.buttonLogin.setOnClickListener {
            val mail = binding.mail.text.toString()
            val password = binding.password.text.toString()
            viewModel.login(mail, password)
        }

    }



}