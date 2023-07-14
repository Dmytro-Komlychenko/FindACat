package com.example.findacat.ui.fragments.game.results

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.findacat.models.Product
import com.example.findacat.models.Result
import com.example.findacat.R
import com.example.findacat.databinding.ResultItemBinding

class ResultItemAdapter(
    private val results: ArrayList<Result>,
    private val products: ArrayList<Product>?
) : RecyclerView.Adapter<ResultItemAdapter.ViewHolder>() {

    private lateinit var holder: ViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        holder = ViewHolder(
            ResultItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = results[position]
        holder.bind(result)
    }

    override fun getItemCount(): Int = results.size


    inner class ViewHolder(private val binding: ResultItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(result: Result) = with(binding) {
            tvTryNuber.text = result.tryNumber.toString()
            tvCounterFoundCats.text = result.countFoundCats.toString()

            var catInBoxImage = when (result.countFoundCats) {
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
            products?.find { prod -> prod.position == result.countFoundCats }?.let {
                catInBoxImage = it.imageUrl
            }
            Glide.with(binding.root).load(catInBoxImage).into(binding.ivLastFoundedCat)
        }
    }
}