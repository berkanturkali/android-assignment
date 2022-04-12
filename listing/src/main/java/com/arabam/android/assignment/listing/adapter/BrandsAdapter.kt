package com.arabam.android.assignment.listing.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arabam.android.assignment.listing.databinding.BrandItemBinding
import com.arabam.android.assignment.listing.model.category.CategoryItem
import com.arabam.android.assignment.remote.model.ItemClickListener
import javax.inject.Inject

class CategoriesAdapter @Inject constructor() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var listener: ItemClickListener<List<CategoryItem>>

    private lateinit var categories: Map<String?, List<CategoryItem>>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CategoryViewHolder(
            BrandItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CategoryViewHolder).bind((categories.keys.toTypedArray()[position]!!))
    }

    inner class CategoryViewHolder(private val binding: BrandItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = categories.values.toTypedArray()[position]
                    listener.onClick(item)
                }
            }
        }

        fun bind(category: String) {
            binding.title = category
        }
    }

    fun setCategories(categories: Map<String?, List<CategoryItem>>) {
        this.categories = categories
        notifyDataSetChanged()
    }

    fun setListener(listener: ItemClickListener<List<CategoryItem>>) {
        this.listener = listener
    }

    override fun getItemCount(): Int = if (::categories.isInitialized) categories.size else 0
}
