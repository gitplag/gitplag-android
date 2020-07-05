package io.gitplag.gitplag.android.util.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import io.gitplag.gitplag.android.R
import io.gitplag.gitplag.android.model.Analysis

class AnalysisListAdapter(
    private val context: Context,
    private val data: List<Analysis>
) : BaseAdapter() {

    override fun getCount() = data.size

    private val inflater by lazy {
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getItem(position: Int): Any {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return data[position].id
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val vi: View? = convertView ?: inflater.inflate(R.layout.analysis_list_item, null)
        val textView = vi?.findViewById(R.id.analysis_list_item) as TextView
        val analysis = data[position]
        textView.text = analysis.id.toString() + ": " + analysis.date
        return vi
    }
}
