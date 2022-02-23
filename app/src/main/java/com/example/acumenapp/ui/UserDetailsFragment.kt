package com.example.acumenapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.acumenapp.R
import com.example.acumenapp.ui.viewmodels.UserViewModel


class UserDetailsFragment : Fragment() {

    lateinit var viewmodel:UserViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)
    }

}