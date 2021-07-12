package com.macneko.customsinglechoiceitemdialog.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class CustomAdapter(
  private val items: List<String>,
  private val disableIndex: Int
) : BaseAdapter() {
  override fun getCount() = items.size

  override fun getItem(position: Int) = items[position]

  override fun getItemId(position: Int): Long = 0

  override fun isEnabled(position: Int) = position != disableIndex

  override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
    val view: View
    val holder: ViewHolder
    if (convertView == null) {
      view = LayoutInflater.from(parent?.context)
        .inflate(android.R.layout.simple_list_item_single_choice, parent, false)
      holder = ViewHolder()
      holder.textView = view.findViewById<View>(android.R.id.text1) as TextView
      view.tag = holder
    } else {
      view = convertView
      holder = view.tag as ViewHolder
    }
    setViewItems(position, holder)
    return view
  }

  private fun setViewItems(position: Int, holder: ViewHolder) {
    holder.textView?.apply {
      text = getItem(position)
      isEnabled = isEnabled(position)
    }
  }

  private class ViewHolder {
    var textView: TextView? = null
  }
}
