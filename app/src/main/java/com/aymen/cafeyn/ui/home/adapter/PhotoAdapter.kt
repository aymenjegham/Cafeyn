package com.aymen.cafeyn.ui.home.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aymen.cafeyn.data.model.PhotoItem
import com.squareup.picasso.Picasso

class PhotoAdapter(
    private val enableAction: (Int) -> Unit,
    private val context: Context,
    private val picasso: Picasso
) : RecyclerView.Adapter<PhotoHolder>() {

    private val photoList = mutableListOf<PhotoItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PhotoHolder.create(parent, enableAction,context,picasso)


    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        holder.bind(photoList[position])
    }

    override fun getItemCount() =
        photoList.size


    fun submitList(newTypes: List<PhotoItem>) {
        DiffCallback(photoList, newTypes)
            .let {
                DiffUtil.calculateDiff(it)
            }
            .also {
                photoList.clear()
                photoList.addAll(newTypes)
                it.dispatchUpdatesTo(this)
            }

    }

    private class DiffCallback(
        private val old: List<PhotoItem>,
        private val new: List<PhotoItem>

    ) : DiffUtil.Callback() {

        override fun getOldListSize() = old.size

        override fun getNewListSize() = new.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            (old[oldItemPosition] == new[newItemPosition])

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            old[oldItemPosition].id == new[newItemPosition].id
    }

}