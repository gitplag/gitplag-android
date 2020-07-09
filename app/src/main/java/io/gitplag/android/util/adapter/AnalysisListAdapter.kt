package io.gitplag.android.util.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.gitplag.android.model.Analysis
import io.gitplag.android.util.diffItemCallback
import io.gitplag.gitplag.android.R

class AnalysisListAdapter(
    private val data: List<Analysis>,
    private val onItemClickListener: io.gitplag.android.util.OnItemClickListener<Analysis>
) : ListAdapter<Analysis, AnalysisListAdapter.AnalysisListViewHolder>(diffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnalysisListViewHolder =
        AnalysisListViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.analysis_list_item, parent, false)
        )

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: AnalysisListViewHolder, position: Int) {
        val analysis = data[position]
        holder.nameTextView.text = analysis.date
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(analysis)
        }
    }

    class AnalysisListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.analysis_list_item)
    }
}
