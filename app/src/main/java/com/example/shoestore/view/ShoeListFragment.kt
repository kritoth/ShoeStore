package com.example.shoestore.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.shoestore.R
import com.example.shoestore.databinding.FragmentShoeListBinding

class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)
        return binding.root
    }


}