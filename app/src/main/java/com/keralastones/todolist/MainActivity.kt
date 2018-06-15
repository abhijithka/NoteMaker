package com.keralastones.todolist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.viewDoneFrameLayout

class MainActivity : AppCompatActivity() {

    var tasksCompleted = ArrayList<TodoItem>()
    var todoTasks = ArrayList<TodoItem>()
    lateinit var doneTasksStatusChangedListener: TaskStatusChangedListener
    lateinit var todoTaskStatusChangedListener: TaskStatusChangedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    companion object {
        const val VIEW_DONE_MIN_SIZE = 1
    }

    fun addNewTask(todoItem: TodoItem) {
        todoTasks.add(todoItem)
        showHideDoneList()
        todoTaskStatusChangedListener.onNewTaskAdded(todoTasks)
    }

    fun markTaskAsDone(indexOfTaskInTodoList: Int) {
        tasksCompleted.add(todoTasks[indexOfTaskInTodoList])
        todoTasks.removeAt(indexOfTaskInTodoList)
        showHideDoneList()
        todoTaskStatusChangedListener.onTaskDone(todoTasks)
        doneTasksStatusChangedListener.onTaskDone(tasksCompleted)
    }

    fun markTaskAsUndone(indexOfTaskInCompletedTasks: Int) {
        todoTasks.add(tasksCompleted.get(indexOfTaskInCompletedTasks))
        tasksCompleted.removeAt(indexOfTaskInCompletedTasks)
        showHideDoneList()
        todoTaskStatusChangedListener.onTaskUnDone(todoTasks)
        doneTasksStatusChangedListener.onTaskUnDone(tasksCompleted)
    }

    fun showHideDoneList() {
        if (tasksCompleted.size == VIEW_DONE_MIN_SIZE) {
            viewDoneFrameLayout.visibility = View.VISIBLE
        }
        if (tasksCompleted.size == VIEW_DONE_MIN_SIZE - 1) {
            viewDoneFrameLayout.visibility = View.GONE
        }
    }

    fun getTodoList(): ArrayList<TodoItem> {
        val todoList = ArrayList<TodoItem>()
        return todoList
    }

    fun getDoneList() : List<TodoItem> {
        val doneList = ArrayList<TodoItem>()
        return doneList
    }
}
