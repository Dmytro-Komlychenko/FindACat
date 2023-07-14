package com.example.findacat.ui.fragments.game.shop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.findacat.models.Product
import com.example.findacat.databinding.ProductItemBinding
import com.google.android.material.snackbar.Snackbar

typealias BuyProductCallback = (Product) -> Unit

class ProductItemAdapter(
    private val products: ArrayList<Product>,
    private val money: Float,
    private val buyProductCallback: BuyProductCallback,
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

    private fun removeItem(product: Product) {
        val diffUtil = MyDiffUtil(products, products - product)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        products.remove(products.find { prod -> prod.name == product.name })
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) = with(binding) {
            tvProductName.text = product.name
            tvCost.text = product.price.toString()
            Glide.with(binding.root).load(product.imageUrl).into(binding.ivProductImage)

            binding.btnBuy.setOnClickListener {
                if (money < product.price) {
                    Snackbar.make(binding.root, "Not enough money", Snackbar.LENGTH_LONG)
                        .show()
                    return@setOnClickListener
                }
                buyProductCallback.invoke(product)
                removeItem(product)
            }
        }
    }
}

class MyDiffUtil(
    private val oldList: List<Product>, private val newList: List<Product>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name == newList[newItemPosition].name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList == newList
    }
}
