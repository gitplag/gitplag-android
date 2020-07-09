package io.gitplag.android.util.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.gitplag.android.model.Repository
import io.gitplag.android.util.OnItemClickListener
import io.gitplag.android.util.diffItemCallback
import io.gitplag.gitplag.android.R

class RepositoryListAdapter(
    private val data: List<Repository>,
    private val onItemClickListener: OnItemClickListener<Repository>
) : ListAdapter<Repository, RepositoryListAdapter.RepositoryListViewHolder>(diffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryListViewHolder =
        RepositoryListViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.repository_list_item, parent, false)
        )

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RepositoryListViewHolder, position: Int) {
        val repository = data[position]
        holder.nameTextView.text = repository.name
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(repository)
        }
    }

    class RepositoryListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.repository_list_item)
    }
}
