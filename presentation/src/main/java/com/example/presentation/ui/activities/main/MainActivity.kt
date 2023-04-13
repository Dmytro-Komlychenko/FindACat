package com.example.presentation.ui.activities.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.presentation.app.App
import com.example.presentation.ui.activities.main.config.ConfigViewModel
import com.example.presentation.ui.activities.main.config.ConfigViewModelFactory
import com.example.presentation.ui.activities.main.database.WebViewViewModel
import com.example.presentation.ui.activities.main.database.WebViewViewModelFactory
import com.example.findacat.R
import com.example.findacat.databinding.ActivityMainBinding
import com.example.presentation.ui.fragments.game.GameViewModel
import com.example.presentation.ui.fragments.game.GameViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var webViewViewModelFactory: WebViewViewModelFactory
    private lateinit var webViewViewModel: WebViewViewModel

    @Inject
    lateinit var configViewModelFactory: ConfigViewModelFactory
    private lateinit var configViewModel: ConfigViewModel

    @Inject
    lateinit var gameViewModelFactory: GameViewModelFactory
    private lateinit var gameViewModel: GameViewModel

    private val navController: NavController by lazy {
        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.fragmentContainerView.id) as NavHostFragment
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (applicationContext as App).appComponent.inject(this@MainActivity)
        webViewViewModel =
            ViewModelProvider(this, webViewViewModelFactory)[WebViewViewModel::class.java]
        configViewModel =
            ViewModelProvider(this, configViewModelFactory)[ConfigViewModel::class.java]

        configViewModel.appConfig.observe(this) {
            if (it.gamePass) {
                navigateToGameFragment()
            } else {
                navigateToWebViewFragment()
            }
        }
    }

    /**
     * This method is used to start a game
     */
    private fun navigateToGameFragment() {
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == R.id.splashFragment) {
                gameViewModel =
                    ViewModelProvider(this, gameViewModelFactory)[GameViewModel::class.java]
                navController.navigate(R.id.action_splashFragment_to_gameMenuFragment)
            }
        }
    }

    /**
     * This method is used to start a WebView
     */
    private fun navigateToWebViewFragment() {
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == R.id.splashFragment) {
                navController.navigate(
                    R.id.action_splashFragment_to_webViewFragment,
                    bundleOf(WEB_LINK_KEY to configViewModel.appConfig.value?.webLink)
                )
            }
        }
    }

    /**
     * This method doesn't allow to return if the WebViewFragment is open
     */
    override fun onBackPressed() {
        if (navController.currentDestination?.id == R.id.webViewFragment) {
            webViewViewModel.onBackPressed.value = true
        } else onBackPressedDispatcher.onBackPressed()
    }

    companion object {
        const val WEB_LINK_KEY = "WEB_LINK_KEY"
    }
}