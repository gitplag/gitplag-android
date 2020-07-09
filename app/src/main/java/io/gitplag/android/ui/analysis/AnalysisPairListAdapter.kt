package io.gitplag.android.ui.analysis

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import io.gitplag.android.model.AnalysisPair
import io.gitplag.android.util.OnItemClickListener
import io.gitplag.android.util.diffItemCallback
import io.gitplag.gitplag.android.databinding.AnalysisPairListItemBinding

class AnalysisPairListAdapter(
    private val data: List<AnalysisPair>,
    private val onItemClickListener: OnItemClickListener<AnalysisPair>
) : ListAdapter<AnalysisPair, AnalysisPairListViewHolder>(diffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnalysisPairListViewHolder =
        AnalysisPairListViewHolder(
            AnalysisPairListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: AnalysisPairListViewHolder, position: Int) =
        holder.bind(data[position], onItemClickListener)
}
