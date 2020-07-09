package io.gitplag.android.ui.analysis

import androidx.recyclerview.widget.RecyclerView
import io.gitplag.android.model.AnalysisPair
import io.gitplag.gitplag.android.databinding.AnalysisPairListItemBinding

class AnalysisPairListViewHolder(private val binding: AnalysisPairListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(pair: AnalysisPair, onItemClickListener: (i: AnalysisPair) -> Unit) {
        binding.analysisPairName.text = "${pair.student1}  ${pair.student2}: ${pair.percentage}"
        binding.root.setOnClickListener {
            onItemClickListener(pair)
        }
    }

}