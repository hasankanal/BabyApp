package com.izelhatipoglu.babyapp.landing.login.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.izelhatipoglu.babyapp.MainActivity
import com.izelhatipoglu.babyapp.R
import com.izelhatipoglu.babyapp.base.BaseViewModel
import com.izelhatipoglu.babyapp.databinding.FragmentLoginBinding
import com.izelhatipoglu.babyapp.landing.login.LoginFragment
import com.izelhatipoglu.babyapp.model.Home

class LoginViewModel(application: Application) : BaseViewModel(application) {

    private val auth = FirebaseAuth.getInstance()

    val loginData = MutableLiveData<Boolean>()

    fun login(mail: String, password: String)  {


        if (mail.isNotEmpty() && password.isNotEmpty()) {
            auth.signInWithEmailAndPassword(mail, password)
                .addOnCompleteListener(object : OnCompleteListener<AuthResult> {
                    //Kullanıcı başarılı giriş yaptığında
                    override fun onComplete(p0: Task<AuthResult>) {
                        if (p0.isSuccessful) {
                            println("Basarili sekilde giris yapıldı")

                            loginData.postValue(true)
                            //      FirebaseAuth.getInstance().signOut()
                        } else {
                            loginData.postValue(false)
                            println("Kullanici hatali girdi")
                        }
                    }
                }
                )
                .addOnFailureListener {
                    println("hata $it")
                }
        } else {
            println("Kullanici eksik girdi")

        }


    }
    


}