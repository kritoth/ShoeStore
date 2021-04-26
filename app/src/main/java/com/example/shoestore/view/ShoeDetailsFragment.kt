package com.example.shoestore.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.shoestore.R
import com.example.shoestore.databinding.FragmentShoeDetailsBinding
import com.example.shoestore.models.Shoe
import com.example.shoestore.viewmodel.ShoeViewModel
import timber.log.Timber

class ShoeDetailsFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailsBinding

    // Fragment scoped viewModel initialized later in onCreateView
    //private lateinit var viewModel: ShoeViewModel
    // Activity level viewModel: https://stackoverflow.com/questions/59952673/how-to-get-an-instance-of-viewmodel-in-activity-in-2020-21
    private val sharedViewModel: ShoeViewModel by activityViewModels()
    // NavGraph scoped viewModel: https://stackoverflow.com/questions/56505455/scoping-a-viewmodel-to-multiple-fragments-not-activity-using-the-navigation-co
    //private val sharedViewModel: ShoeViewModel by navGraphViewModels(R.id.main_navigation)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_details, container, false)
        // Specify the current activity as the lifecycle owner.
        binding.setLifecycleOwner(this)

        Timber.plant(Timber.DebugTree())

        // Set the viewmodel for databinding - this allows the bound layout access to all of the
        // data in the VieWModel
        binding.shoeViewModel = sharedViewModel
        binding.shoe = Shoe("", 0.0, "", "")

        // Sets up event listening to navigate when the saving/canceling is finished
        sharedViewModel.eventFinish.observe(viewLifecycleOwner, Observer { isFinished ->
            if (isFinished) {
                binding.invalidateAll()
                val action =
                    ShoeDetailsFragmentDirections.actionShoeItemFragmentToShoeListFragment()
                findNavController().navigate(action)
                sharedViewModel.onFinished()
            }
        })

        return binding.root
    }

}