package com.izelhatipoglu.babyapp.homeDoctor.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.izelhatipoglu.babyapp.base.BaseViewModel
import com.izelhatipoglu.babyapp.model.DoctorModel

class DoctorHomeViewModel(application: Application) : BaseViewModel(application){

    private val auth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    val doctorDataList = MutableLiveData<ArrayList<DoctorModel>>()
    private val momList = ArrayList<DoctorModel>()

    fun getData(){

        db.collection("Home").addSnapshotListener { value, error ->
            if (error != null){

            }else{
                if (value != null){
                 //   println("doctor data")
                    for (document in value.documents){
                     //   println("doctor data for içinde")
                        val userName = auth.currentUser?.email
                        println("current username : $userName")
                        val doctorName = document.get("doctorName") as? String
                        println("doctorname :  $doctorName")
                        if(userName.toString() == doctorName.toString()){
                            val pregnantName =document.get("name") as? String
                            println("name :: $pregnantName")
                            momList.add(DoctorModel(pregnantName!!))
                            println("first mom ${doctorDataList.value?.get(0)}")
                        }
                    }
                    println("size ${momList.size}")
                    doctorDataList.postValue(momList)
                }
            }
        }
    }
}