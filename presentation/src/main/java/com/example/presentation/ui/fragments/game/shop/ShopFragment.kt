package com.example.presentation.ui.fragments.game.shop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.presentation.models.Product
import com.example.testgame.R
import com.example.testgame.databinding.FragmentShopBinding

class ShopFragment : Fragment() {

    private var _binding: FragmentShopBinding? = null
    private val binding: FragmentShopBinding get() = _binding!!

    private var adapter: ProductItemAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShopBinding.inflate(inflater, container, false)

        val products = arrayListOf(
            Product("Cat in jar", 20F, R.drawable.ic_cat_in_jar_shop_1)
        )
        adapter = ProductItemAdapter(products)
        binding.recyclerView.adapter = adapter

        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}