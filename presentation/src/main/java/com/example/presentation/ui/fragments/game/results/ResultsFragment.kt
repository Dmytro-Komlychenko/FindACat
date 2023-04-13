package com.example.presentation.ui.fragments.game.results

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.findacat.databinding.FragmentResultsBinding
import com.example.presentation.ui.fragments.game.GameViewModel

class ResultsFragment : Fragment() {

    private var _binding: FragmentResultsBinding? = null
    private val binding: FragmentResultsBinding get() = _binding!!

    private var adapter: ResultItemAdapter? = null

    private val gameViewModel: GameViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultsBinding.inflate(inflater, container, false)

        gameViewModel.userProfile.inventory.observe(viewLifecycleOwner) {
            val results = gameViewModel.userProfile.results.value ?: arrayListOf()
            adapter =
                ResultItemAdapter(results, it)
            binding.recyclerView.adapter = adapter

            if (it.isNotEmpty())
                binding.tvMaxNumber.text = results.maxOf { res -> res.countFoundCats }.toString()
        }
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}