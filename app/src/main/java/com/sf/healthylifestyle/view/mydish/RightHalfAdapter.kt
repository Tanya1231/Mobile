package com.sf.healthylifestyle.view.mydish

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sf.healthylifestyle.data.dto.product.response.ProductResponse
import com.sf.healthylifestyle.databinding.ItemRightDishesBinding

class RightHalfAdapter(
) : RecyclerView.Adapter<RightHalfAdapter.InnerRightHalfViewHolder>() {
    private var rightHalf: MutableList<ProductResponse> = mutableListOf()

    inner class InnerRightHalfViewHolder(binding: ItemRightDishesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var rightHalfRoot = binding.rightHalfRoot
        var imgRightHalf = binding.imgRightHalf
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerRightHalfViewHolder {
        val binding = ItemRightDishesBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return InnerRightHalfViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InnerRightHalfViewHolder, position: Int) {

        Glide.with(holder.rightHalfRoot)
            .load(rightHalf[position].image_extra)
            .centerCrop()
            .into(holder.imgRightHalf)
    }

    override fun getItemCount(): Int = rightHalf.size

    fun getItem(position: Int): ProductResponse = rightHalf[position]

    @SuppressLint("NotifyDataSetChanged")
    fun setData(rightHalf: List<ProductResponse>) {
        this.rightHalf = rightHalf.toMutableList()
        notifyDataSetChanged()
    }
}