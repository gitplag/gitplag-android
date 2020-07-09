package io.gitplag.android.ui.repository

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.gitplag.gitplag.android.R

class AnalysisListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val nameTextView: TextView = view.findViewById(R.id.analysis_list_item)
}