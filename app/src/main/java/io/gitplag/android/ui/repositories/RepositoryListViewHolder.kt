package io.gitplag.android.ui.repositories

import androidx.recyclerview.widget.RecyclerView
import io.gitplag.android.model.Repository
import io.gitplag.android.util.OnItemClickListener
import io.gitplag.gitplag.android.databinding.RepositoryListItemBinding

class RepositoryListViewHolder(private val binding: RepositoryListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(repository: Repository, onItemClickListener: OnItemClickListener<Repository>) {
        binding.repositoryName.text = repository.name
        binding.root.setOnClickListener {
            onItemClickListener.onItemClick(repository)
        }
    }

}