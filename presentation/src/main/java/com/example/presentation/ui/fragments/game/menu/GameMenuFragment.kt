package com.example.presentation.ui.fragments.game.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.testgame.R
import com.example.testgame.databinding.FragmentGameMenuBinding


class GameMenuFragment : Fragment() {

    private var _binding: FragmentGameMenuBinding? = null
    private val binding: FragmentGameMenuBinding get() = _binding!!

    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        navController = Navigation.findNavController(view)
        binding.btnPlay.setOnClickListener {
            navController.navigate(
                R.id.action_gameMenuFragment_to_catchCatFragment
            )
        }

        super.onViewCreated(view, savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}