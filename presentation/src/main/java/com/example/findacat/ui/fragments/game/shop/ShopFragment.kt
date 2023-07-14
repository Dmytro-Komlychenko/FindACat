package com.example.findacat.ui.fragments.game.shop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.domain.models.ProductType
import com.example.findacat.models.Product
import com.example.findacat.R
import com.example.findacat.app.App
import com.example.findacat.databinding.FragmentShopBinding
import com.example.findacat.ui.fragments.game.GameViewModel
import com.example.findacat.ui.fragments.game.GameViewModelFactory
import javax.inject.Inject

class ShopFragment : Fragment() {

    private var _binding: FragmentShopBinding? = null
    private val binding: FragmentShopBinding get() = _binding!!

    private var adapter: ProductItemAdapter? = null

    @Inject
    lateinit var gameViewModelFactory: GameViewModelFactory
    private lateinit var gameViewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShopBinding.inflate(inflater, container, false)

        (activity?.applicationContext as App).appComponent.inject(this@ShopFragment)
        gameViewModel =
            ViewModelProvider(this, gameViewModelFactory)[GameViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gameViewModel.inventory.observe(viewLifecycleOwner) {
            val products = arrayListOf(
                Product("Cat in jar", 20F, R.drawable.ic_cat_in_jar_shop_1, ProductType.Cat, 1)
            )

            it.forEach { product ->
                if (products.contains(product))
                    products.remove(product)
            }

            adapter = ProductItemAdapter(products, gameViewModel.money) { product ->
                gameViewModel.buyProduct(product)
                gameViewModel.money -= product.price
                gameViewModel.updateMoney()
            }
            binding.recyclerView.adapter = adapter
            binding.tvMoney.text = gameViewModel.money.toString()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}