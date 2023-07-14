package com.example.findacat.ui.fragments.game.play

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.findacat.R
import com.example.findacat.app.App
import com.example.findacat.databinding.FragmentCatchCatBinding
import com.example.findacat.ui.fragments.game.GameViewModel
import com.example.findacat.ui.fragments.game.GameViewModelFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

class CatchCatFragment : Fragment() {

    private var _binding: FragmentCatchCatBinding? = null
    private val binding: FragmentCatchCatBinding get() = _binding!!

    @Inject
    lateinit var gameViewModelFactory: GameViewModelFactory
    private lateinit var gameViewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCatchCatBinding.inflate(inflater, container, false)

        (activity?.applicationContext as App).appComponent.inject(this@CatchCatFragment)
        gameViewModel =
            ViewModelProvider(this, gameViewModelFactory)[GameViewModel::class.java]

        binding.tvCounterFoundCats.text = gameViewModel.counterCatsFound.toString()
        start()

        binding.btnBackToMenu.setOnClickListener {
            finishGame()
        }
        binding.btnCloseGame.setOnClickListener {
            activity?.finish()
        }
        return binding.root
    }

    private fun start() {
        Glide.with(this).load(R.drawable.ic_closed_box).into(binding.ivBox)

        binding.ivBox.setOnClickListener {
            binding.ivBox.setOnClickListener {}

            val result = Random.nextBoolean()
            if (result) {
                ++gameViewModel.counterCatsFound

                var catInBoxImage: Int? = null

                gameViewModel.inventory.value?.find { prod -> prod.position == gameViewModel.counterCatsFound }
                    ?.let {
                        catInBoxImage = it.imageUrl
                    }

                if (catInBoxImage == null) {
                    catInBoxImage =
                        when (gameViewModel.counterCatsFound) {
                            0 -> R.drawable.ic_empty_box
                            1 -> R.drawable.ic_cat_in_box_1
                            2 -> R.drawable.ic_cat_in_box_2
                            3 -> R.drawable.ic_cat_in_box_3
                            4 -> R.drawable.ic_cat_in_box_4
                            5 -> R.drawable.ic_cat_in_box_5
                            6 -> R.drawable.ic_cat_in_box_6
                            7 -> R.drawable.ic_cat_in_box_7
                            8 -> R.drawable.ic_cat_in_box_8
                            9 -> R.drawable.ic_cat_in_box_9
                            10 -> R.drawable.ic_cat_in_box_10
                            else -> R.drawable.ic_cat_in_super_box
                        }
                }
                Glide.with(this).load(catInBoxImage).into(binding.ivBox)
                binding.btnBackToMenu.visibility = View.GONE
                binding.btnCloseGame.visibility = View.GONE

                if (gameViewModel.counterCatsFound == 10) {
                    AlertDialog.Builder(context)
                        .setTitle("Win!")
                        .setMessage("Congrats!!! You found all cats!!!\n You can continue play and you will open a super cat.")
                        .setIcon(R.drawable.ic_win_logo)
                        .setPositiveButton("Continue") { dialog, id ->
                            dialog.cancel()
                        }.setNegativeButton("Finish") { dialog, id ->
                            finishGame()
                        }.create().show()
                }
                lifecycleScope.launch {
                    binding.tvCounterFoundCats.text = gameViewModel.counterCatsFound.toString()
                    delay(2000)
                    start()
                }
                gameViewModel.money += 0.1F
                gameViewModel.updateMoney()
            } else {
                Glide.with(this).load(R.drawable.ic_empty_box).into(binding.ivBox)
                binding.btnBackToMenu.visibility = View.VISIBLE
                binding.btnCloseGame.visibility = View.VISIBLE
                gameViewModel.saveResult()

                lifecycleScope.launch {
                    binding.tvCounterFoundCats.text = getString(R.string.you_lost)
                    delay(2000)
                    binding.tvCounterFoundCats.text = gameViewModel.counterCatsFound.toString()
                    start()
                }
            }
        }
    }

    private fun finishGame() {
        gameViewModel.counterCatsFound = 0
        activity?.onBackPressedDispatcher?.onBackPressed()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}