package com.duv.todolist.views.list

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.duv.todolist.R
import com.duv.todolist.TASK_ID_KEY
import com.duv.todolist.TaskApplication
import com.duv.todolist.data.TaskRepository
import com.duv.todolist.model.TaskModel
import com.duv.todolist.views.list.adapter.TaskListAdapter
import kotlinx.android.synthetic.main.fragment_todo_list.*
import kotlinx.android.synthetic.main.task_list_item.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import kotlinx.android.synthetic.main.toolbar_layout.view.*

class TodoListFragment : Fragment(), TaskListView, TaskClickItemListener {

    private val presenter: TaskListPresenter = TaskListPresenter(this, TaskRepository(TaskApplication.instance.helperDB))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.getTaskList()


        taskToolbar.txtScreenName.text = "Tarefas"


        btnAddTask.setOnClickListener {
            findNavController().navigate(R.id.action_TodoListFragment_to_NewTaskFragment)
        }
    }

    override fun initTaskList(list: List<TaskModel>) {
        val adapter = TaskListAdapter(this, list)
        recyclerTask.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerTask.adapter = adapter

        adapter.listenerComplete = {
            txtTaskTitle.setTextColor(Color.RED)
            Toast.makeText(context, "Tarefa Concluida", Toast.LENGTH_SHORT).show()
        }

        adapter.listenerDelete = {
            presenter.deleteTask(it.id!!)
            presenter.updateTask(it)
        }
    }

    override fun onClickListener(id: Int) {
        findNavController().navigate(R.id.action_todoListFragment_to_DetailFragment, Bundle().apply {
            putInt(TASK_ID_KEY, id)
        })
    }
}

