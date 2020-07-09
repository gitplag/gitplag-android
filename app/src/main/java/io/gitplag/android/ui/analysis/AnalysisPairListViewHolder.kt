package io.gitplag.android.ui.analysis

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.gitplag.gitplag.android.R

class AnalysisPairListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val nameTextView: TextView = view.findViewById(R.id.analysis_list_item)
}