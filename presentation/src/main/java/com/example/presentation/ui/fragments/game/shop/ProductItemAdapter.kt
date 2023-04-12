package com.example.presentation.ui.fragments.game.shop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.presentation.models.Product
import com.example.testgame.databinding.ProductItemBinding

class ProductItemAdapter(
    private val products: ArrayList<Product>
) : RecyclerView.Adapter<ProductItemAdapter.ViewHolder>() {

    private lateinit var holder: ViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        holder = ViewHolder(
            ProductItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int = products.size


    inner class ViewHolder(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) = with(binding) {
            tvProductName.text = product.name
            tvCost.text = product.cost.toString()
            Glide.with(binding.root).load(product.imageResource).into(binding.ivProductImage)
        }
    }
}