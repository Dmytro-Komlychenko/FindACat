package com.example.testgame.ui.activities.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.testgame.R
import com.example.testgame.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val navController: NavController by lazy {
        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.fragmentContainerView.id) as NavHostFragment
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            delay(2000)
            navigateToGameFragment()
        }
    }


    private fun navigateToGameFragment() {
        findNavController(binding.fragmentContainerView.id).navigate(
            R.id.action_splashFragment_to_gameFragment
        )
    }

    private fun navigateToUploadImageFragment() {
        findNavController(binding.fragmentContainerView.id).navigate(
            R.id.action_splashFragment_to_uploadImageFragment
        )
    }

    override fun onBackPressed() {
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == R.id.uploadImageFragment) {
                Snackbar.make(binding.root, "Impossible to go back", Snackbar.LENGTH_LONG).show()
                return@addOnDestinationChangedListener
            }
            else super.onBackPressed()
        }
    }
}