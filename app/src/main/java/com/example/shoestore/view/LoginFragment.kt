package com.example.shoestore.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.shoestore.R
import com.example.shoestore.databinding.FragmentLoginBinding
import com.example.shoestore.viewmodel.ShoeViewModel

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    // Activity level viewModel: https://stackoverflow.com/questions/59952673/how-to-get-an-instance-of-viewmodel-in-activity-in-2020-21
    private val sharedViewModel: ShoeViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        binding.btnLogin.setOnClickListener { findNavController()
                .navigate(LoginFragmentDirections
                        .actionLoginFragmentToOnboardWelcomeFragment())
        }
        binding.btnSignIn.setOnClickListener { findNavController()
                .navigate(LoginFragmentDirections.actionLoginFragmentToOnboardWelcomeFragment())}

        return binding.root
    }

}