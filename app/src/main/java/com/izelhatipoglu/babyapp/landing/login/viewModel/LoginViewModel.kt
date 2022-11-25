package com.izelhatipoglu.babyapp.landing.login.viewModel

import android.app.Application
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.izelhatipoglu.babyapp.base.BaseViewModel
import com.izelhatipoglu.babyapp.databinding.FragmentLoginBinding

class LoginViewModel(application: Application) : BaseViewModel(application) {

    private val auth = FirebaseAuth.getInstance()


    fun login(mail: String, password: String) {
        if (mail.isNotEmpty() && password.isNotEmpty()) {
            auth.signInWithEmailAndPassword(mail, password)
                .addOnCompleteListener(object : OnCompleteListener<AuthResult> {
                    //Kullanıcı başarılı giriş yaptığında
                    override fun onComplete(p0: Task<AuthResult>) {
                        if (p0.isSuccessful) {
                            println("Basarili sekilde giris yapıldı")
                            //      FirebaseAuth.getInstance().signOut()
                        } else {
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