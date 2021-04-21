package com.example.shoestore.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.shoestore.R
import com.example.shoestore.databinding.FragmentOnboardWelcomeBinding


class OnboardWelcomeFragment : Fragment() {

    private lateinit var binding: FragmentOnboardWelcomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_onboard_welcome, container, false)

        binding.btnNext.setOnClickListener { findNavController()
            .navigate(OnboardWelcomeFragmentDirections
                .actionOnboardWelcomeFragmentToOnboardInstructionFragment3())
        }

        return binding.root
    }

}