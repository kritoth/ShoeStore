package com.example.shoestore.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.navigation.ui.NavigationUI
import com.example.shoestore.R
import com.example.shoestore.databinding.FragmentShoeListBinding
import com.example.shoestore.models.Shoe
import com.example.shoestore.viewmodel.ShoeViewModel
import timber.log.Timber
import java.lang.IllegalArgumentException

class ShoeListFragment : Fragment() {

    private val BODY: String = "body"
    private val HEADLINE: String = "headline"

    private lateinit var binding: FragmentShoeListBinding

    //private lateinit var viewModel: ShoeViewModel
    // Activity level viewModel: https://stackoverflow.com/questions/59952673/how-to-get-an-instance-of-viewmodel-in-activity-in-2020-21
    //private val sharedViewModel: ShoeViewModel by activityViewModels()
    //NavGraph scoped viewModel: https://stackoverflow.com/questions/56505455/scoping-a-viewmodel-to-multiple-fragments-not-activity-using-the-navigation-co
    private val sharedViewModel: ShoeViewModel by navGraphViewModels(R.id.main_navigation)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Timber.plant(Timber.DebugTree())
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)

        setHasOptionsMenu(true)

        //viewModel = ViewModelProvider(this).get(ShoeViewModel::class.java)

        binding.fabAddShoe.setOnClickListener { findNavController()
            .navigate(ShoeListFragmentDirections
                .actionShoeListFragmentToShoeItemFragment())}

        sharedViewModel.shoes.observe(viewLifecycleOwner, Observer { shoes ->
            if(!shoes.isNullOrEmpty()){
                Timber.i("Adding shoes to the layout")
                addItemView(shoes)
            } else {
                Timber.i("List of Shoes are empty, adding placeholder view")
                showEmptyList()
            }
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController()) || super.onOptionsItemSelected(item)
    }

    /** This adds a Shoe as an itemView, using LinearLayout, child to the LinearLayout parent */
    private fun addItemView(shoes: MutableList<Shoe>) {
        val parentLayout: LinearLayout = binding.listOfShoes

        for(item in shoes) {
            // Itemlayout
            val shoeItemLayout = LinearLayout(activity)
            shoeItemLayout.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            shoeItemLayout.orientation = LinearLayout.VERTICAL

            //Name TextView
            val nameTV: TextView = textView(HEADLINE)
            nameTV.text = if(item.name.isEmpty()) getString(R.string.error_no_name) else item.name
            //Company TextView
            val companyTV: TextView = textView(BODY)
            companyTV.text = if(item.company.isEmpty()) getString(R.string.error_no_company) else item.company
            //Size TextView
            val sizeTV: TextView = textView(BODY)
            sizeTV.text = if(item.size==0.0) getString(R.string.error_no_size) else item.size.toString()
            //Description TextView
            val descriptionTV: TextView = textView(BODY)
            descriptionTV.text = if(item.description.isEmpty()) getString(R.string.error_no_description) else item.description
            //Divider view
            val dividerView: View = dividerView()

            //add Views to itemLayout
            shoeItemLayout.addView(nameTV)
            shoeItemLayout.addView(companyTV)
            shoeItemLayout.addView(sizeTV)
            shoeItemLayout.addView(descriptionTV)
            shoeItemLayout.addView(dividerView)

            //add itemLayout to parentLayout
            parentLayout.addView(shoeItemLayout)
        }
    }

    /** This shows a message that the list is empty */
    private fun showEmptyList() {
        val parentLayout: LinearLayout = binding.listOfShoes
        //Nothing to show TextView
        val errorTV: TextView = textView(BODY)
        errorTV.text = resources.getString(R.string.error_no_shoes)

        //add TV to item
        parentLayout.addView(errorTV)
    }

    /** Creates a styled TextView. */
    private fun textView(style: String): TextView {
        val textView = when(style){
            HEADLINE ->  headLineTextView();
            BODY -> bodyTextView()
            else -> throw IllegalArgumentException("No valid style added")
        }
        val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        params.bottomMargin = resources.getDimension(R.dimen.gutter_horizontal_basic).toInt()
        params.marginStart = resources.getDimension(R.dimen.margin_vertical_basic).toInt()
        params.marginEnd = resources.getDimension(R.dimen.margin_vertical_basic).toInt()
        params.gravity = Gravity.CENTER_HORIZONTAL //layout_gravity
        textView.layoutParams = params
        textView.gravity = Gravity.CENTER_HORIZONTAL // gravity

        return textView
    }

    /** Returns a styled TextView. Style= body */
    private fun bodyTextView(): TextView {
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            TextView(activity, null, 0, R.style.Body)
        } else {
            TextView(activity, null, R.attr.textAppearanceBody1)
        }
    }

    /** Returns a styled TextView. Style= headline */
    private fun headLineTextView(): TextView {
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            TextView(activity, null, 0, R.style.Subtitle1)
        } else {
            TextView(activity, null, R.attr.textAppearanceSubtitle1)
        }
    }

    /** Simple line to divide item layouts */
    private fun dividerView(): View {
        val dividerView = View(activity)
        val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 1)
        params.bottomMargin = resources.getDimension(R.dimen.gutter_horizontal_basic).toInt()
        params.marginStart = resources.getDimension(R.dimen.margin_vertical_basic).toInt()
        params.marginEnd = resources.getDimension(R.dimen.margin_vertical_basic).toInt()
        dividerView.layoutParams = params
        dividerView.setBackgroundResource(R.color.cyan_dark)
        return dividerView
    }
}

