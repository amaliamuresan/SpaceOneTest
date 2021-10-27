package com.example.spaceonetest.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.spaceonetest.databinding.ProductItemBinding
import com.example.spaceonetest.models.Product

class ProductAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Product, ProductAdapter.ProductViewHolder>(DiffCallBack) {
    class ProductViewHolder(private var binding: ProductItemBinding) :
        ViewHolder(binding.root) {
        fun bind(item: Product) {
            binding.product = item
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val clickListener: (product: Product) -> Unit) {
        fun onClick(product: Product) = clickListener(product)
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ProductItemBinding.inflate(layoutInflater, parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        holder.bind(product)

        holder.itemView.setOnClickListener {
            onClickListener.onClick(product)
        }
    }
}