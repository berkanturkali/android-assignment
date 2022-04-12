package com.arabam.android.assignment.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.arabam.android.assignment.details.ImageClickListener
import com.arabam.android.assignment.details.databinding.ViewPagerItemBinding
import com.arabam.android.assignment.domain.utils.resize
import javax.inject.Inject

class AdvertImagesViewPagerAdapter @Inject constructor(
    private val circularProgressDrawable: CircularProgressDrawable,
) : RecyclerView.Adapter<AdvertImagesViewPagerAdapter.ViewHolder>() {

    private lateinit var listener: ImageClickListener

    private var images = emptyList<String>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): AdvertImagesViewPagerAdapter.ViewHolder {
        return ViewHolder(
            ViewPagerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AdvertImagesViewPagerAdapter.ViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount(): Int = images.size

    inner class ViewHolder(private val binding: ViewPagerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onImageClick(images, position, binding.container)
                }
            }
        }

        fun bind(image: String) {
            binding.apply {
                url = image.resize()
                progressDrawable = circularProgressDrawable
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
