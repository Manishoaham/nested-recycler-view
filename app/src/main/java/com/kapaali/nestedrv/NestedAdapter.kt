package com.kapaali.nestedrv

import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NestedAdapter(private val data:List<NestedData>) : RecyclerView.Adapter<NestedAdapter.NestedItem>() {

    private val TAG = "NestedAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NestedItem {
        return NestedItem(LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_nested_item,parent,false))
    }

    override fun onBindViewHolder(holder: NestedItem, position: Int) {
        val item = data[position]
        holder.setTitle(item.title)
        holder.setNestedData(data, item.nestedData)
        Log.e(TAG, "onBindViewHolder: position: $position")
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class NestedItem(view:View) : RecyclerView.ViewHolder(view) {

        private val tvTitle = view.findViewById<TextView>(R.id.tv_title)
        private val ivExpand = view.findViewById<ImageView>(R.id.iv_expand)
        private val rvNested = view.findViewById<RecyclerView>(R.id.rv_nested)

        fun setTitle(title: String){
            tvTitle.text = title
        }

        fun setNestedData(parentData:List<NestedData>, data:List<NestedData>) {
            if(parentData[adapterPosition].isExpanded) {
                setExpanded(parentData, data)
            } else {
                setCollapsed(parentData, data)
            }
        }

        private fun setExpanded(parentData:List<NestedData>, data:List<NestedData>) {
            parentData[adapterPosition].isExpanded = true
            rvNested.layoutManager = LinearLayoutManager(ivExpand.context)
            rvNested.adapter = NestedAdapter(data)
            rvNested.setOnTouchListener { v, event ->
                if (v?.id == rvNested.id) {
                    rvNested.parent.requestDisallowInterceptTouchEvent(
                        event?.actionMasked != MotionEvent.ACTION_UP)
                }
                rvNested.onTouchEvent(event)
            }
            ivExpand.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
            ivExpand.setOnClickListener {
                setCollapsed(parentData, data)
                setNestedData(parentData, data)
            }
        }

        private fun setCollapsed(parentData:List<NestedData>, data:List<NestedData>) {
            parentData[adapterPosition].isExpanded = false
            rvNested.adapter = null
            ivExpand.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
            ivExpand.setOnClickListener {
                setExpanded(parentData, data)
                setNestedData(parentData, data)
            }
        }
    }
}