package com.keralastones.todolist

interface TaskStatusChangedListener {

    fun onTaskDone(taskList: List<TodoItem>)

    fun onTaskUnDone(taskList: List<TodoItem>)

    fun onNewTaskAdded(doneTasksList: List<TodoItem>)
}
