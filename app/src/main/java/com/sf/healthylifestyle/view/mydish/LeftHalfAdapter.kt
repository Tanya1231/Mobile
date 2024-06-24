package com.sf.healthylifestyle.view.mydish

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sf.healthylifestyle.data.dto.product.response.ProductResponse
import com.sf.healthylifestyle.databinding.ItemLeftDishesBinding

class LeftHalfAdapter(
) : RecyclerView.Adapter<LeftHalfAdapter.InnerLeftHalfViewHolder>() {
    private var leftHalf: MutableList<ProductResponse> = mutableListOf()

    inner class InnerLeftHalfViewHolder(binding: ItemLeftDishesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var leftHalfRoot = binding.leftHalfRoot
        var imgLeftHalf = binding.imgLeftHalf
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerLeftHalfViewHolder {
        val binding = ItemLeftDishesBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return InnerLeftHalfViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InnerLeftHalfViewHolder, position: Int) {

        Glide.with(holder.leftHalfRoot)
            .load(leftHalf[position].image_extra)
            .centerCrop()
            .into(holder.imgLeftHalf)
    }

    override fun getItemCount(): Int = leftHalf.size

    fun getItem(position: Int?): ProductResponse? {
        return if (position == null) null
        else leftHalf[position]
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(leftHalf: List<ProductResponse>) {
        this.leftHalf = leftHalf.toMutableList()
        notifyDataSetChanged()
    }
}