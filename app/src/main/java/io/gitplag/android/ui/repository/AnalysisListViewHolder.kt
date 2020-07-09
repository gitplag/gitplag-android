package io.gitplag.android.ui.repository

import androidx.recyclerview.widget.RecyclerView
import io.gitplag.android.model.Analysis
import io.gitplag.android.util.OnItemClickListener
import io.gitplag.gitplag.android.databinding.AnalysisListItemBinding

class AnalysisListViewHolder(private val binding: AnalysisListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(analysis: Analysis, onItemClickListener: OnItemClickListener<Analysis>) {
        binding.analysisTime.text = analysis.date
        binding.root.setOnClickListener {
            onItemClickListener.onItemClick(analysis)
        }
    }

}