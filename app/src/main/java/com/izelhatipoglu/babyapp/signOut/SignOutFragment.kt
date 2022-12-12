package com.izelhatipoglu.babyapp.signOut

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.izelhatipoglu.babyapp.R
import com.izelhatipoglu.babyapp.base.BaseFragment
import com.izelhatipoglu.babyapp.databinding.FragmentSignOutBinding
import com.izelhatipoglu.babyapp.signOut.viewModel.SignOutViewModel

class SignOutFragment : BaseFragment<SignOutViewModel , FragmentSignOutBinding>() {

    override fun getViewModel() = SignOutViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentSignOutBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}