package com.example.commit.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.commit.ListItem.PostItem
import com.example.commit.R

class PostAdapter:BaseAdapter() {
    private var postList = ArrayList<PostItem>()

    override fun getCount(): Int {
        return postList.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItem(position: Int): Any {
        return postList.get(position)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        val context:Context? = parent?.context

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (view == null) {
            val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.item_department, parent, false)
        }

        var textView=view?.findViewById(R.id.text_department) as TextView

        var item=postList[position]

        textView.setText(item.title)

        return view
    }

    fun addItem(text: String,enable: Boolean){
        val item= PostItem()

        postList.add(item)
    }

    fun clear(){
        postList.clear()
    }
}