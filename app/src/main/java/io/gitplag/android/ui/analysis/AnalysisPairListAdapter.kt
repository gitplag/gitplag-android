package io.gitplag.android.ui.analysis

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import io.gitplag.android.model.AnalysisPair
import io.gitplag.android.util.OnItemClickListener
import io.gitplag.android.util.diffItemCallback
import io.gitplag.gitplag.android.R

class AnalysisPairListAdapter(
    private val data: List<AnalysisPair>,
    private val onItemClickListener: OnItemClickListener<AnalysisPair>? = null
) : ListAdapter<AnalysisPair, AnalysisPairListViewHolder>(diffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnalysisPairListViewHolder =
        AnalysisPairListViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.analysis_list_item, parent, false)
        )

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: AnalysisPairListViewHolder, position: Int) {
        val pair = data[position]
        holder.nameTextView.text = "${pair.student1} - ${pair.student2}: ${pair.percentage}"
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(pair)
        }
    }
}
