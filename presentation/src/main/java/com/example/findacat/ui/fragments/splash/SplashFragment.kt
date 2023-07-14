package com.example.findacat.ui.fragments.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.findacat.R
import com.example.findacat.app.App
import com.example.findacat.databinding.FragmentSplashBinding
import com.example.findacat.models.AppActionE
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding: FragmentSplashBinding get() = _binding!!

    @Inject
    lateinit var splashViewModelFactory: SplashViewModelFactory
    private lateinit var splashViewModel: SplashViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)

        (activity?.applicationContext as App).appComponent.inject(this@SplashFragment)
        splashViewModel =
            ViewModelProvider(this, splashViewModelFactory)[SplashViewModel::class.java]

        splashViewModel.isFirstLaunch.observe(viewLifecycleOwner) {
            splashViewModel.initAppAction()
        }

        splashViewModel.appAction.observe(viewLifecycleOwner) {

            when (it.value) {
                AppActionE.GAME -> {
                    navigateToGameFragment()
                }
                AppActionE.WEB -> {
                    navigateToWebFragment()
                }
            }
        }

        return binding.root
    }

    private fun navigateToGameFragment() {
        activity?.findNavController(R.id.containerView)?.navigate(
            R.id.action_splashFragment_to_gameMenuFragment,
        )
    }

    private fun navigateToWebFragment() {
        activity?.findNavController(R.id.containerView)?.navigate(
            R.id.action_splashFragment_to_webFragment,
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}