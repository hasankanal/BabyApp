package com.izelhatipoglu.babyapp.base

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.izelhatipoglu.babyapp.home.viewModel.HomeViewModel
import com.izelhatipoglu.babyapp.landing.login.viewModel.LoginViewModel

class ViewModelFactory() : ViewModelProvider.NewInstanceFactory(){

    @SuppressLint("UseRequireInsteadOfGet")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(application = Application()) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(application = Application()) as T
            else -> throw IllegalArgumentException("ViewModelClass Not Found")
        }
    }
}