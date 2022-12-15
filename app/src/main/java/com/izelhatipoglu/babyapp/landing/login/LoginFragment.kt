package com.izelhatipoglu.babyapp.landing.login



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.izelhatipoglu.babyapp.base.BaseFragment
import com.izelhatipoglu.babyapp.databinding.FragmentLoginBinding
import com.izelhatipoglu.babyapp.landing.login.viewModel.LoginViewModel
import com.izelhatipoglu.babyapp.model.Home


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
        observeData()

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
        }
    }

    private fun observeData(){
        viewModel.loginData.observe(viewLifecycleOwner){ loginData->
            println("44444")
            if (loginData){
                viewModel.getData()
                println("içerde")
                val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                Navigation.findNavController(requireView()).navigate(action)
            }
        }

        viewModel.homeData.observe(viewLifecycleOwner){ homeData ->
            //giriş yaptı mı yapmadı mı
            if(homeData.type != null){
                println("Sorgu observe ${homeData.type}")
            }
        }
    }



}