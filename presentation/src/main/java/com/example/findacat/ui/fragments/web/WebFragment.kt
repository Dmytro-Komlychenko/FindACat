package com.example.findacat.ui.fragments.web

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.findacat.databinding.FragmentWebBinding

class WebFragment : Fragment() {

    private var _binding: FragmentWebBinding? = null
    private val binding: FragmentWebBinding get() = _binding!!

    private lateinit var webView: CustomWebView

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


        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val WEB_LINK_KEY = "https://www.google.com"
    }
}