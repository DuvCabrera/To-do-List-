package com.duv.todolist.views.detail

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.duv.todolist.R
import com.duv.todolist.TASK_ID_KEY
import com.duv.todolist.TaskApplication
import com.duv.todolist.data.TaskRepository
import com.duv.todolist.model.TaskModel
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.view.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import kotlinx.android.synthetic.main.toolbar_layout.view.*

class DetailFragment : Fragment(),  DetailTaskView {

    private val presenter: DetailTaskPresenter = DetailTaskPresenter(this, TaskRepository(
        TaskApplication.instance.helperDB))
    private var taskId: Int = -1
    private var taskDetail = TaskModel (taskId, "","", 0)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailTaskToolbar.txtScreenName.text = "Edite sua Tarefa"
        detailTaskToolbar.btnAddTask.visibility = View.INVISIBLE
        detailTaskToolbar.btnReturn.visibility = View.VISIBLE

        arguments?.let {
            taskId = it.getInt(TASK_ID_KEY)
        }

        presenter.getSingleDetail(taskId)

        taskDetail = (TaskModel(view.id, view.txtTitleDetail.text.toString(), view.txtDescriptionDetail.text.toString(), null))


        btnReturn.setOnClickListener {
            activity?.onBackPressed()
        }

    }

    override fun handleError(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun handleSuccess(task: TaskModel) {
        txtTitleDetail.text = task.title
        txtDescriptionDetail.text = task.description
    }
}


