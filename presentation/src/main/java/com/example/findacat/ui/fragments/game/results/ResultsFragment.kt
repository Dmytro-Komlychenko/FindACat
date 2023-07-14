package com.example.findacat.ui.fragments.game.results

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.findacat.app.App
import com.example.findacat.databinding.FragmentResultsBinding
import com.example.findacat.ui.fragments.game.GameViewModel
import com.example.findacat.ui.fragments.game.GameViewModelFactory
import javax.inject.Inject

class ResultsFragment : Fragment() {

    private var _binding: FragmentResultsBinding? = null
    private val binding: FragmentResultsBinding get() = _binding!!

    private var adapter: ResultItemAdapter? = null

    @Inject
    lateinit var gameViewModelFactory: GameViewModelFactory
    private lateinit var gameViewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultsBinding.inflate(inflater, container, false)

        (activity?.applicationContext as App).appComponent.inject(this@ResultsFragment)
        gameViewModel =
            ViewModelProvider(this, gameViewModelFactory)[GameViewModel::class.java]

        gameViewModel.results.observe(viewLifecycleOwner) {
            val results = gameViewModel.results.value ?: arrayListOf()
            adapter =
                ResultItemAdapter(results, gameViewModel.inventory.value)
            binding.recyclerView.adapter = adapter

            if (results.isNotEmpty())
                binding.tvMaxNumber.text = results.maxOf { res -> res.countFoundCats }.toString()
        }
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}