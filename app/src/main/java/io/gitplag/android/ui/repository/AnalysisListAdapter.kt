package io.gitplag.android.ui.repository

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import io.gitplag.android.model.Analysis
import io.gitplag.android.util.diffItemCallback
import io.gitplag.gitplag.android.databinding.AnalysisListItemBinding

class AnalysisListAdapter(
    private val data: List<Analysis>,
    private val onItemClickListener: io.gitplag.android.util.OnItemClickListener<Analysis>
) : ListAdapter<Analysis, AnalysisListViewHolder>(diffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnalysisListViewHolder =
        AnalysisListViewHolder(
            AnalysisListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: AnalysisListViewHolder, position: Int) =
        holder.bind(data[position], onItemClickListener)
}
