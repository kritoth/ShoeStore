package com.example.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.shoestore.databinding.ActivityMainBinding
import com.example.shoestore.viewmodel.ShoeViewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var viewModel: ShoeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(ShoeViewModel::class.java)

        //There are multiple top-level destinations, from where there is no back navigation allowed, so no back button needed
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.loginFragment,
                R.id.onboardWelcomeFragment,
                R.id.onboardInstructionFragment)
        )
        // the cumbersome way to find the NavController, see: https://stackoverflow.com/questions/58703451/fragmentcontainerview-as-navhostfragment
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        val toolBar = binding.toolbar
        setupWithNavController(toolBar, navController, appBarConfiguration)
    }
}