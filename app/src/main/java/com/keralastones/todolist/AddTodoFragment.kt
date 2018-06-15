package com.keralastones.todolist

import android.app.Fragment
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_add_todo.addToDoRecyclerView
import kotlinx.android.synthetic.main.fragment_add_todo.addTodoButton
import kotlinx.android.synthetic.main.fragment_add_todo.categorySelector
import kotlinx.android.synthetic.main.fragment_add_todo.todoTitle


class AddTodoFragment : Fragment(), TaskStatusChangedListener {

    var categories: Map<Int, String>? = null
    var todoList = ArrayList<TodoItem>()
    lateinit var addToRecyclerViewAdapter: AddTodoRecyclerViewAdapter
    lateinit var mainActivity: MainActivity

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val root = inflater?.inflate(R.layout.fragment_add_todo, container, false)
        return root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpCategorySpinner()
        mainActivity = (activity as MainActivity)
        mainActivity.todoTaskStatusChangedListener = this
        todoList = mainActivity.getTodoList()
        addToRecyclerViewAdapter = AddTodoRecyclerViewAdapter(context, todoList, this)
        addToDoRecyclerView.adapter = addToRecyclerViewAdapter
        addToDoRecyclerView.layoutManager = LinearLayoutManager(activity)
        addTodoButton.setOnClickListener({
            hideKeyboard(it, context)
            mainActivity.addNewTask(TodoItem(todoTitle.text.toString(), categorySelector.selectedItemPosition))
        })
    }

    private fun hideKeyboard(it: View, context: Context) {
        val imm = context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(it.windowToken, 0)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setUpCategorySpinner() {
        categories = getCategoriesMap()
        val categorySelectorAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_item,
                ArrayList(categories?.values))
        categorySelectorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySelector.adapter = categorySelectorAdapter
    }

    private fun getCategoriesMap(): Map<Int, String> {
        val categories = HashMap<Int, String>()
        categories.put(1, "Groceries")
        categories.put(0, "Category")
        return categories
    }

    fun finishTask(index: Int) {
        mainActivity.markTaskAsDone(index)
    }

    override fun onTaskDone(taskList: List<TodoItem>) {
        todoList = taskList as ArrayList<TodoItem>
        addToRecyclerViewAdapter.updateToDoList(todoList)
    }

    override fun onTaskUnDone(taskList: List<TodoItem>) {
        todoList = taskList as ArrayList<TodoItem>
        addToRecyclerViewAdapter.updateToDoList(todoList)
    }

    override fun onNewTaskAdded(doneTasksList: List<TodoItem>) {
        todoList = doneTasksList as ArrayList<TodoItem>
        addToRecyclerViewAdapter.updateToDoList(todoList)
    }
}