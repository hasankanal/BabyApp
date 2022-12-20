package com.izelhatipoglu.babyapp.homeDoctor.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.izelhatipoglu.babyapp.base.BaseViewModel
import com.izelhatipoglu.babyapp.model.DoctorModel

class DoctorHomeViewModel(application: Application) : BaseViewModel(application){

    private val auth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    val doctorDataList = MutableLiveData<ArrayList<DoctorModel>>()

    fun getData(){

        db.collection("Home").addSnapshotListener { value, error ->
            if (error != null){

            }else{
                if (value != null){
                    for (document in value.documents){
                        val userName = auth.currentUser?.email
                        val doctorName = document.get("doctorName") as? String
                        if(userName.toString() == doctorName.toString()){
                            val pregnantName =document.get("name") as? String
                            doctorDataList.value?.add(DoctorModel(pregnantName))
                        }
                    }
                }
            }
        }
    }
}