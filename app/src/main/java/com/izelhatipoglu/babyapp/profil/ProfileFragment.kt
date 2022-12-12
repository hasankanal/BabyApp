package com.izelhatipoglu.babyapp.profil


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.izelhatipoglu.babyapp.base.BaseFragment
import com.izelhatipoglu.babyapp.databinding.FragmentGalleryBinding
import com.izelhatipoglu.babyapp.profil.viewModel.ProfileViewModel

class ProfileFragment : BaseFragment<ProfileViewModel , FragmentGalleryBinding> (){
    override fun getViewModel() = ProfileViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentGalleryBinding.inflate(inflater, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}