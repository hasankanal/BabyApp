package com.izelhatipoglu.babyapp.landing.register.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.izelhatipoglu.babyapp.base.BaseViewModel
import com.izelhatipoglu.babyapp.landing.register.RegisterFragment
import com.izelhatipoglu.babyapp.landing.register.RegisterFragmentDirections

class RegisterViewModel(application: Application) : BaseViewModel(application) {

    var isRegister = MutableLiveData<Boolean>()
    private var auth = FirebaseAuth.getInstance()

    val isLoading = MutableLiveData<Boolean>()
    val loginData = MutableLiveData<Boolean>()

    fun register(mail: String, password: String) {

        if (mail.isNotEmpty() && password.isNotEmpty()) {
            isLoading.postValue(true)
            auth.createUserWithEmailAndPassword(mail,password).addOnCompleteListener {
                if(it.isSuccessful){
                    isLoading.postValue(true)
                    isRegister.postValue(true)
                    loginData.postValue(true)
                }else{
                    isLoading.postValue(false)
                    loginData.postValue(false)
                    println(it.exception?.localizedMessage)
                }
            }
        }
    }
}