package com.arabam.android.assigment.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arabam.android.assigment.data.ImageClickListener
import com.arabam.android.assigment.databinding.ViewPagerItemBinding
import com.arabam.android.assigment.utils.resize
import javax.inject.Inject

class AdvertImagesViewPagerAdapter @Inject constructor(
) : RecyclerView.Adapter<AdvertImagesViewPagerAdapter.ViewHolder>() {

    private lateinit var listener: ImageClickListener

    private var images = emptyList<String>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): AdvertImagesViewPagerAdapter.ViewHolder {
        return ViewHolder(ViewPagerItemBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: AdvertImagesViewPagerAdapter.ViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount(): Int = images.size

    inner class ViewHolder(private val binding: ViewPagerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = absoluteAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val url = images[position]
                    listener.onImageClick(binding.advertImage, url)
                }
            }
        }

        fun bind(image: String) {
            binding.apply {
                url = image.resize("800x600")
            }
        }
    }

    fun setList(images: List<String>) {
        this.images = images
        notifyDataSetChanged()
    }

    fun setListener(listener: ImageClickListener) {
        this.listener = listener
    }
}