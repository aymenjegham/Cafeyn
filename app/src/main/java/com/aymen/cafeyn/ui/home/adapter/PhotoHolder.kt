package com.aymen.cafeyn.ui.home.adapter

import android.R
import android.content.Context
import android.icu.number.NumberFormatter.with
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.altagem.cafeyn.databinding.ItemPhotoBinding
import com.aymen.cafeyn.data.model.PhotoItem
import com.squareup.picasso.Picasso


class PhotoHolder private constructor(
    private val binding: ItemPhotoBinding,
    private val enableAction: (Int) -> Unit,
    private val context: Context
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: PhotoItem) {
        binding.photo = item

        Picasso.get().load(item.thumbnailUrl).into(binding.ivIcon)

        binding.item.setOnClickListener {
            enableAction(adapterPosition)
        }


    }

    companion object {
        fun create(
            parent: ViewGroup,
            enableAction: (Int) -> Unit,
            context: Context
        ) =
            LayoutInflater.from(parent.context)
                .let { ItemPhotoBinding.inflate(it, parent, false) }
                .let { PhotoHolder(it, enableAction, context) }
    }
}