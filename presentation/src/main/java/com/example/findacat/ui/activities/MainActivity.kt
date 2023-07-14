package com.example.findacat.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.findacat.R
import com.example.findacat.app.App
import com.example.findacat.databinding.ActivityMainBinding
import com.example.findacat.ui.fragments.web.WebViewModel
import com.example.findacat.ui.fragments.web.WebViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val navController: NavController by lazy {
        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.containerView.id) as NavHostFragment
        navHostFragment.navController
    }

    @Inject
    lateinit var webViewViewModelFactory: WebViewModelFactory
    private lateinit var webViewViewModel: WebViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        (applicationContext as App).appComponent.inject(this@MainActivity)
        webViewViewModel =
            ViewModelProvider(this, webViewViewModelFactory)[WebViewModel::class.java]
    }


    override fun onBackPressed() {
        when (navController.currentDestination?.id) {
            R.id.gameMenuFragment -> exit()
            R.id.webFragment -> webViewViewModel.onBackPressed.value = true
            else -> onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun exit() {
        val pid = android.os.Process.myPid()
        finishAffinity()
        android.os.Process.killProcess(pid)
    }
}