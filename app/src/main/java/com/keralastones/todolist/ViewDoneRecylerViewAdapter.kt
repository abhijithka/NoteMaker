package com.keralastones.todolist

import android.content.Context
import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton


class ViewDoneRecyclerViewAdapter(private val context: Context, private var doneList: List<TodoItem>,
                                  val viewDoneFragment: ViewDoneFragment) : RecyclerView.Adapter<ViewDoneRecyclerViewAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return doneList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.todo_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todoItem = doneList[position]
        holder.toDoRadioButton.apply {
            text = todoItem.title
            isChecked = true
            setOnClickListener({
                viewDoneFragment.undoTask(position)
            })
        }.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var toDoRadioButton: RadioButton

        init {
            itemView.apply {
                toDoRadioButton = itemView.findViewById<View>(R.id.todo_radio_btn) as RadioButton
            }

        }

    }

    fun updateDoneList(updatedDoneList: List<TodoItem>) {
        doneList = updatedDoneList
        notifyDataSetChanged()
    }
}