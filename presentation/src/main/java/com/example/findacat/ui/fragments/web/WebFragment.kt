package com.example.findacat.ui.fragments.web

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import com.example.findacat.databinding.FragmentWebBinding

class WebFragment : Fragment() {

    private var _binding: FragmentWebBinding? = null
    private val binding: FragmentWebBinding get() = _binding!!

    private lateinit var webView: CustomWebView

    private val webViewModel: WebViewModel by activityViewModels()

    private val uploadingImageResult: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            webView.observeResult(result)
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWebBinding.inflate(inflater, container, false)

        webView = CustomWebView(
            requireContext(),
            binding.webView,
            uploadingImageResult,
        )
        webView.loadUrl(WEB_LINK_KEY)

        webViewModel.onBackPressed.observe(viewLifecycleOwner) {
            if (webViewModel.onBackPressed.value == false) return@observe

            if (binding.webView.canGoBack()) {
                binding.webView.goBack()
            } else {
                exit()
            }
            webViewModel.onBackPressed.value = false
        }

        return binding.root
    }

    private fun exit() {
        val pid = android.os.Process.myPid()
        activity?.finishAffinity()
        android.os.Process.killProcess(pid)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val WEB_LINK_KEY = "https://www.google.com"
    }
}