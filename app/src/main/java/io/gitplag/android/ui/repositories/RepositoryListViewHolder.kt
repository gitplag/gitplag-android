package io.gitplag.android.ui.repositories

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.gitplag.gitplag.android.R

class RepositoryListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val nameTextView: TextView = view.findViewById(R.id.repository_list_item)
}