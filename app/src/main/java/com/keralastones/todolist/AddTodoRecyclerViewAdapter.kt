package com.keralastones.todolist

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton


class AddTodoRecyclerViewAdapter(private val context: Context, private var todoList: List<TodoItem>,
                                 private val addTodoFragment: AddTodoFragment) : RecyclerView.Adapter<AddTodoRecyclerViewAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.todo_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todoItem = todoList[position]
        holder.apply {
            toDoRadioButton.apply {
                text = todoItem.title
                isChecked = false
                toDoRadioButton.setOnClickListener({
                    addTodoFragment.finishTask(position)
                })
            }
        }
    }

    fun updateToDoList(updatedTodoList: List<TodoItem>) {
        todoList = updatedTodoList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var toDoRadioButton: RadioButton

        init {
            itemView.apply {
                toDoRadioButton = itemView.findViewById<View>(R.id.todo_radio_btn) as RadioButton
            }

        }
    }
}