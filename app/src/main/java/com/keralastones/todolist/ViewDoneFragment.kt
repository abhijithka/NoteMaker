package com.keralastones.todolist

import android.app.Fragment
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_view_done.doneTasksRecylerView

class ViewDoneFragment : Fragment(), TaskStatusChangedListener {
    lateinit var doneList : List<TodoItem>
    lateinit var doneTasksRecyclerViewAdapter: ViewDoneRecyclerViewAdapter
    lateinit var mainActivity: MainActivity


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val root = inflater?.inflate(R.layout.fragment_view_done, container, false)
        return root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity = (activity as MainActivity)
        mainActivity.doneTasksStatusChangedListener = this
        doneList = getDoneInitialList()
        doneTasksRecyclerViewAdapter = ViewDoneRecyclerViewAdapter(context, doneList, this)
        doneTasksRecylerView.adapter = doneTasksRecyclerViewAdapter
        doneTasksRecylerView.layoutManager = LinearLayoutManager(activity)
    }

    fun getDoneInitialList() : List<TodoItem> {
        val doneList = ArrayList<TodoItem>()
        return doneList
    }

    fun undoTask(index: Int) {
        mainActivity.markTaskAsUndone(index)
    }

    override fun onTaskDone(taskList: List<TodoItem>) {
       doneTasksRecyclerViewAdapter.updateDoneList(taskList)
    }

    override fun onTaskUnDone(taskList: List<TodoItem>) {
        doneTasksRecyclerViewAdapter.updateDoneList(taskList)
    }

    override fun onNewTaskAdded(doneTasksList: List<TodoItem>) {

    }

}