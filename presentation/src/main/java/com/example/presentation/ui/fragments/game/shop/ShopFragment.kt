package com.example.presentation.ui.fragments.game.shop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.domain.models.ProductType
import com.example.presentation.models.Product
import com.example.presentation.ui.fragments.game.GameViewModel
import com.example.findacat.R
import com.example.findacat.databinding.FragmentShopBinding

class ShopFragment : Fragment() {

    private var _binding: FragmentShopBinding? = null
    private val binding: FragmentShopBinding get() = _binding!!

    private var adapter: ProductItemAdapter? = null

    private val gameViewModel: GameViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShopBinding.inflate(inflater, container, false)

        val products = arrayListOf(
            Product("Cat in jar", 20F, R.drawable.ic_cat_in_jar_shop_1, ProductType.Cat, 1)
        )

        gameViewModel.userProfile.inventory.observe(viewLifecycleOwner) {
            it.forEach {product ->
                if (products.contains(product))
                    products.remove(product)
            }

            adapter = ProductItemAdapter(products, gameViewModel.userProfile.money) {
                gameViewModel.buyProduct(it)
                gameViewModel.userProfile.money -= it.price
                gameViewModel.updateMoney()
            }
            binding.recyclerView.adapter = adapter
            binding.tvMoney.text = gameViewModel.userProfile.money.toString()
        }

        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}