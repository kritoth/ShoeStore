package com.example.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.shoestore.databinding.FragmentShoeItemBinding

class ShoeItemFragment : Fragment() {

    private lateinit var binding: FragmentShoeItemBinding

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_item, container, false)

        binding.btnSave.setOnClickListener { findNavController()
            .navigate(ShoeItemFragmentDirections
                .actionShoeItemFragmentToShoeListFragment())}

        binding.btnCancel.setOnClickListener { findNavController()
            .navigate(ShoeItemFragmentDirections
                .actionShoeItemFragmentToShoeListFragment())}

        return binding.root
    }


}