package com.duv.todolist.views.newtask

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.duv.todolist.R
import com.duv.todolist.TaskApplication
import com.duv.todolist.data.TaskRepository
import com.duv.todolist.model.TaskModel
import kotlinx.android.synthetic.main.fragment_new_task.*
import kotlinx.android.synthetic.main.fragment_new_task.view.*
import kotlinx.android.synthetic.main.fragment_todo_list.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import kotlinx.android.synthetic.main.toolbar_layout.view.*

class NewTaskFragment : Fragment(), NewTaskView {

    private val presenter= NewTaskPresenter(TaskRepository(TaskApplication.instance.helperDB), this)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        newTaskToolbar.btnAddTask.visibility = View.INVISIBLE
        newTaskToolbar.txtScreenName.text = "Nova Tarefa"
        newTaskToolbar.btnReturn.visibility = View.VISIBLE

        btnReturn.setOnClickListener {
            activity?.onBackPressed()
        }

        btnSaveTask.setOnClickListener {
            presenter.addNewTask(
                edtTaskTitle.text.toString(),
                edtTaskDescription.text.toString()
            )
        }

    }

    override fun handleError(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun handleSuccess() {
        Toast.makeText(context, "Adicionado com sucesso", Toast.LENGTH_SHORT).show()
        activity?.onBackPressed()
    }

}