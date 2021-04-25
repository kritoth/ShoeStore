package com.example.shoestore.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.shoestore.R
import com.example.shoestore.databinding.FragmentShoeListBinding
import com.example.shoestore.models.Shoe
import com.example.shoestore.viewmodel.ShoeViewModel
import timber.log.Timber

class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding

    //private lateinit var viewModel: ShoeViewModel
    // Activity level viewModel: https://stackoverflow.com/questions/59952673/how-to-get-an-instance-of-viewmodel-in-activity-in-2020-21
    private val viewModel: ShoeViewModel by viewModels(
        ownerProducer = { requireParentFragment() }
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Timber.plant(Timber.DebugTree())
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)

        //viewModel = ViewModelProvider(this).get(ShoeViewModel::class.java)

        binding.fabAddShoe.setOnClickListener { findNavController()
            .navigate(ShoeListFragmentDirections
                .actionShoeListFragmentToShoeItemFragment())}

        viewModel.shoes.observe(viewLifecycleOwner, Observer { shoes ->
            if(!shoes.isNullOrEmpty()){
                addItemView(shoes)
                val shoe: Shoe = shoes[0]
                Timber.i("ShoeList observed, [0]th Shoe is: $shoe")
            } else {
                Timber.i("List of Shoes are empty, adding placeholder view")
                showEmptyList()
            }
        })

        return binding.root
    }

    /** This adds a Shoe as a LinearLayout child to the LinearLayout parent */
    private fun addItemView(shoes: MutableList<Shoe>) {

    }

    /** This shows a message that the list is empty */
    private fun showEmptyList() {
        TODO("Not yet implemented")
    }


}