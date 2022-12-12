package com.izelhatipoglu.babyapp.galery


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.izelhatipoglu.babyapp.base.BaseFragment
import com.izelhatipoglu.babyapp.databinding.FragmentGalleryBinding
import com.izelhatipoglu.babyapp.galery.viewModel.GalleryViewModel


class GalleryFragment : BaseFragment<GalleryViewModel, FragmentGalleryBinding>() {

    override fun getViewModel() = GalleryViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentGalleryBinding.inflate(inflater, container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}