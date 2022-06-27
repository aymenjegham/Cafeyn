package com.aymen.cafeyn.ui.home.adapter

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aymen.cafeyn.data.model.PhotoItem
import com.aymen.cafeyn.databinding.ItemPhotoBinding
import com.squareup.picasso.Picasso


class PhotoHolder private constructor(
    private val binding: ItemPhotoBinding,
    private val enableAction: (Int) -> Unit,
    private val context: Context,
    private val picasso: Picasso
) : RecyclerView.ViewHolder(binding.root) {



    fun bind(item: PhotoItem) {
        binding.photo = item
        binding.picasso = picasso


        binding.item.setOnClickListener {
            enableAction(adapterPosition)
        }


    }

    companion object {
        fun create(
            parent: ViewGroup,
            enableAction: (Int) -> Unit,
            context: Context,
            picasso: Picasso
        ) =
            LayoutInflater.from(parent.context)
                .let { ItemPhotoBinding.inflate(it, parent, false) }
                .let { PhotoHolder(it, enableAction, context,picasso) }
    }
}