package com.sf.healthylifestyle.view.basket

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sf.healthylifestyle.data.dbo.entity.DishEntity
import com.sf.healthylifestyle.data.dto.product.response.ProductResponse
import com.sf.healthylifestyle.databinding.ItemBasketBinding

class BasketAdapter(
) : RecyclerView.Adapter<BasketAdapter.InnerBasketViewHolder>() {
    private var basket: MutableList<DishEntity> = mutableListOf()

    inner class InnerBasketViewHolder(binding: ItemBasketBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var basketRoot = binding.basketRoot
        var imgDish = binding.imgDish
        var tvDescription = binding.tvDescription
        var tvCost = binding.tvCost
        var tvQuantity = binding.tvQuantity
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerBasketViewHolder {
        val binding = ItemBasketBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return InnerBasketViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InnerBasketViewHolder, position: Int) {
    }

    override fun getItemCount(): Int = basket.size

    fun getItem(position: Int): DishEntity = basket[position]

    @SuppressLint("NotifyDataSetChanged")
    fun setData(basket: List<DishEntity>) {
        this.basket = basket.toMutableList()
        notifyDataSetChanged()
    }
}