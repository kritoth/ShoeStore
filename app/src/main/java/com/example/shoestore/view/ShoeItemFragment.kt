package com.example.shoestore.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.shoestore.R
import com.example.shoestore.databinding.FragmentShoeItemBinding
import com.example.shoestore.models.Shoe
import com.example.shoestore.viewmodel.ShoeViewModel
import timber.log.Timber

class ShoeItemFragment : Fragment() {

    private lateinit var binding: FragmentShoeItemBinding
    //private lateinit var viewModel: ShoeViewModel
    // Activity level viewModel: https://stackoverflow.com/questions/59952673/how-to-get-an-instance-of-viewmodel-in-activity-in-2020-21
    //private val sharedViewModel: ShoeViewModel by activityViewModels()
    //NavGraph scoped viewModel: https://stackoverflow.com/questions/56505455/scoping-a-viewmodel-to-multiple-fragments-not-activity-using-the-navigation-co
    private val sharedViewModel: ShoeViewModel by navGraphViewModels(R.id.main_navigation)

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_item, container, false)
        Timber.plant(Timber.DebugTree())
        //viewModel = ViewModelProvider(this).get(ShoeViewModel::class.java)

        // Set the viewmodel for databinding - this allows the bound layout access to all of the
        // data in the VieWModel
        binding.shoeViewModel = sharedViewModel

        binding.btnSave.setOnClickListener {
            saveShoe()
            findNavController().navigate(ShoeItemFragmentDirections.actionShoeItemFragmentToShoeListFragment())
        }

        binding.btnCancel.setOnClickListener {
            findNavController().navigate(ShoeItemFragmentDirections.actionShoeItemFragmentToShoeListFragment())
        }

        return binding.root
    }

    private fun saveShoe() {
        // getting the texts entered from EditTexts
        val name: String = binding.fieldShoeName.text.toString()
        val sizeString = binding.fieldShoeSize.text.toString()
        val size: Double = if(sizeString.isEmpty()){0.0} else{ sizeString.toDouble()}
        val company: String = binding.fieldShoeCompany.text.toString()
        val description: String = binding.fieldShoeDescription.text.toString()
        // and save them into the List in ViewModel
        sharedViewModel.addShoe(Shoe(name, size, company, description))
        Timber.i("Shoe saved: $name, $size, $company, $description")
    }


}