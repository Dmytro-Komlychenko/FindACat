package com.example.testgame.ui.fragments.uploadimage

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.testgame.CustomWebView
import com.example.testgame.R
import com.example.testgame.databinding.FragmentUploadImageBinding
import com.example.testgame.ui.activities.main.WebViewViewModel
import com.google.android.material.snackbar.Snackbar


class UploadImageFragment : Fragment() {

    private var _binding: FragmentUploadImageBinding? = null
    private val binding: FragmentUploadImageBinding get() = _binding!!

    private val webViewViewModel: WebViewViewModel by activityViewModels()
    private lateinit var webView: CustomWebView


    private val uploadingImageResult: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            webView.observeResult(result)
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUploadImageBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)

        webView = CustomWebView(
            binding.webView,
            "https://tinypng.com/",
            requireContext(),
            uploadingImageResult
        )

        webViewViewModel.onBackPressed.observe(viewLifecycleOwner) {
            if (webViewViewModel.onBackPressed.value == false) return@observe

            if (binding.webView.canGoBack()) {
                binding.webView.goBack()
            } else {
                Snackbar.make(binding.root, "Impossible to go back", Snackbar.LENGTH_LONG)
                    .show()
            }
            webViewViewModel.onBackPressed.value = false
        }
        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_exit, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.exit_app -> {
                activity?.finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}