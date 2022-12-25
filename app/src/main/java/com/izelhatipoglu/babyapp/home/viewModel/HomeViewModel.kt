package com.izelhatipoglu.babyapp.home.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.izelhatipoglu.babyapp.base.BaseViewModel
import com.izelhatipoglu.babyapp.model.Home
import com.izelhatipoglu.babyapp.model.Month

class HomeViewModel(application: Application) : BaseViewModel(application) {

    private val auth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    val homeData = MutableLiveData<Home>()
    val monthData = MutableLiveData<Month>()

    fun getData() {

        db.collection("Home")
            .addSnapshotListener { value, error ->

                if (error != null) {
                } else {

                    if (value != null) {

                        for (document in value.documents) {
                            val userName = document.get("userName") as? String
                            println("username:::: $userName")
                            val user = auth.currentUser?.email

                            if (user.toString() == userName) {
                                val month = document.get("date") as? String
                                val doctorAppointment = document.get("doctorAppointment") as? String
                                val doctorNotes = document.get("doctorNotes") as? String
                                val doctorName = document.get("doctorName") as? String
                                val type = document.get("type") as? String
                                val downloadURL = document.get("downloadUrl") as? String
                                 println("usernameFromFirebase:: $userName")
                                homeData.value = Home(
                                    month,
                                    doctorAppointment,
                                    doctorName,
                                    doctorNotes,
                                    downloadURL,
                                    userName,
                                    type,
                                )
                                break
                            }
                        }
                    }

                }
            }
    }

    fun getMonth(month: String) {
        db.collection("Months").addSnapshotListener { value, error ->
            if (error != null) {
            } else {
                if (value != null) {

                    for(document in value.documents){
                        val dbMonth = document.get("Number") as? String

                        if(dbMonth == month){
                            val advice = document.get("Advice") as? String
                            val state = document.get("State") as? String

                            monthData.value = Month(
                                dbMonth,
                                state,
                                advice
                            )
                            break
                        }

                    }

                }

            }
        }

    }
}
