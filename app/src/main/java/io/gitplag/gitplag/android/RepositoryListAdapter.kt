package io.gitplag.gitplag.android

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class RepositoryListAdapter(
    private val context: Context,
    private val data: List<Repository>
) : BaseAdapter() {

    override fun getCount() = data.size

    private val inflater by lazy {
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getItem(position: Int): Any {
        return data[position].name
    }

    override fun getItemId(position: Int): Long {
        return data[position].id.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val vi: View? = convertView ?: inflater.inflate(R.layout.repository_list_item, null)
        val textView = vi?.findViewById(R.id.repository_list_item) as TextView
        textView.text = data[position].name
        return vi
    }
}
