package io.gitplag.android.ui.repositories

import androidx.recyclerview.widget.RecyclerView
import io.gitplag.android.model.Repository
import io.gitplag.gitplag.android.databinding.RepositoryListItemBinding

class RepositoryListViewHolder(private val binding: RepositoryListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(repository: Repository, onItemClickListener: (i: Repository) -> Unit) {
        binding.repositoryName.text = repository.name
        binding.root.setOnClickListener {
            onItemClickListener(repository)
        }
    }

}