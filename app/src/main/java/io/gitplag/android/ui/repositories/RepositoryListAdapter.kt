package io.gitplag.android.ui.repositories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import io.gitplag.android.model.Repository
import io.gitplag.android.util.OnItemClickListener
import io.gitplag.android.util.diffItemCallback
import io.gitplag.gitplag.android.databinding.RepositoryListItemBinding

class RepositoryListAdapter(
    private val data: List<Repository>,
    private val onItemClickListener: OnItemClickListener<Repository>
) : ListAdapter<Repository, RepositoryListViewHolder>(diffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryListViewHolder =
        RepositoryListViewHolder(
            RepositoryListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RepositoryListViewHolder, position: Int) =
        holder.bind(data[position], onItemClickListener)
}
