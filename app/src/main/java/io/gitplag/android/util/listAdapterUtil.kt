package io.gitplag.android.util

import androidx.recyclerview.widget.DiffUtil
import io.gitplag.android.model.Identifiable

fun <T : Identifiable> diffItemCallback() = object : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
        oldItem.equals(newItem)

}

interface OnItemClickListener<T : Identifiable> {
    fun onItemClick(item: T)
}