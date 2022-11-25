package com.izelhatipoglu.babyapp.home.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.izelhatipoglu.babyapp.base.BaseViewModel
import com.izelhatipoglu.babyapp.model.Home

class HomeViewModel(application: Application) : BaseViewModel(application){


    private val db : FirebaseFirestore = FirebaseFirestore.getInstance()

    val homeData = MutableLiveData<Home>()


    fun getData() {

       db.collection("Home").addSnapshotListener { value, error ->

           if(error != null){
               println("Hata var getdata")
           }else{
               println("burada --3 ")
               if(value != null){
                   println("burada --5 ")

                    println("burada --2 ")
                       for (document in value.documents){
                           println("burada")
                           println(document.data?.get("userName"))
                           //casting
                           val userName = document.get("userName") as? String
                           val month = document.get("date") as? Int
                           val doctorAppointment = document.get("doctorAppointment") as? String
                           val photo = document.get("photo") as? String
                           val doctorNotes = document.get("doctorNotes") as? String
                           val doctorName = document.get("doctorName") as? String
                            println("usernameFromFirebase:: $userName")
                           homeData.value = Home(month,doctorAppointment,doctorName,doctorNotes,photo,userName)
                       }

               }

           }
       }
    }

    fun getDataFromFirebase(){
        db.collection("deneme")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    println("${document.id} => ${document.data["isim"]}")
                  //  usersList.add(document.data["username"].toString())
                }
            }
            .addOnFailureListener { exception ->
                println("Error getting documents. $exception")
            }

    }
}