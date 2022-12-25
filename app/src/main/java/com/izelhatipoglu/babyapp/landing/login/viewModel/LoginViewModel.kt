package com.izelhatipoglu.babyapp.landing.login.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.izelhatipoglu.babyapp.base.BaseViewModel
import com.izelhatipoglu.babyapp.model.Home

class LoginViewModel(application: Application) : BaseViewModel(application) {

    private val auth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    val loginData = MutableLiveData<Boolean>()
    val homeData = MutableLiveData<Home>()


    fun login(mail: String, password: String) {


        if (mail.isNotEmpty() && password.isNotEmpty()) {
            auth.signInWithEmailAndPassword(mail, password)
                .addOnCompleteListener(object : OnCompleteListener<AuthResult> {
                    override fun onComplete(p0: Task<AuthResult>) {
                        if (p0.isSuccessful) {
                            println(auth.currentUser?.email)
                            loginData.postValue(true)
                        } else {
                            loginData.postValue(false)
                        }
                    }
                }
                )
                .addOnFailureListener {

                    println("hata $it")
                }
        } else {


        }

    }

    fun getData(){

        db.collection("Home").addSnapshotListener { value, error ->

            if(error != null){

            }else{
                if(value != null){
                    for (document in value.documents){
                        val userName = document.get("userName") as? String
                        val user = auth.currentUser?.email
                        if(user.toString() == userName){
                            val type = document.get("type") as? String
                            homeData.value = Home(
                                userName = userName,
                                type = type,
                            )
                            break

                        }
                    }
                }
            }
        }
    }

}