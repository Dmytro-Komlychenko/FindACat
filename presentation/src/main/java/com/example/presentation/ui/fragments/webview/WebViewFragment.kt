package com.example.presentation.ui.fragments.webview

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.presentation.ui.activities.main.MainActivity
import com.example.testgame.R
import com.example.testgame.databinding.FragmentWebViewBinding
import com.example.presentation.ui.activities.main.database.WebViewViewModel
import com.google.android.material.snackbar.Snackbar


class WebViewFragment : Fragment() {

    private var _binding: FragmentWebViewBinding? = null
    private val binding: FragmentWebViewBinding get() = _binding!!

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
        _binding = FragmentWebViewBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)

        webView = CustomWebView(
            binding.webView,
            requireContext(),
            uploadingImageResult,

        ) {
            webViewViewModel.logLink(it)
        }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val url = arguments?.getString(MainActivity.WEB_LINK_KEY)!!
        webView.loadUrl(url)
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