package com.example.testgame.ui.activities.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.testgame.R
import com.example.testgame.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var webViewViewModel: WebViewViewModel

    private val navController: NavController by lazy {
        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.fragmentContainerView.id) as NavHostFragment
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        webViewViewModel = ViewModelProvider(this)[WebViewViewModel::class.java]

        lifecycleScope.launch {
            delay(2000)
            navigateToUploadImageFragment()
        }
    }


    private fun navigateToGameFragment() {
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == R.id.splashFragment) {
                navController.navigate(R.id.action_splashFragment_to_gameFragment)
            }
        }
    }

    private fun navigateToUploadImageFragment() {
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == R.id.splashFragment) {
                navController.navigate(R.id.action_splashFragment_to_uploadImageFragment)
            }
        }
    }

    override fun onBackPressed() {
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == R.id.uploadImageFragment) {
                webViewViewModel.onBackPressed.value = true
                return@addOnDestinationChangedListener
            } else super.onBackPressed()
        }
    }
}