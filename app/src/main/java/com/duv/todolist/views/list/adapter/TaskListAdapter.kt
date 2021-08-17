package com.duv.todolist.views.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.duv.todolist.R
import com.duv.todolist.model.TaskModel
import com.duv.todolist.views.list.TaskClickItemListener
import kotlinx.android.synthetic.main.task_list_item.view.*

class TaskListAdapter (private val clickListener: TaskClickItemListener , private val data: List<TaskModel>) : RecyclerView.Adapter<TaskListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.task_list_item, parent, false)
        )
    }
    private val taskDelete = arrayListOf<TaskModel>()

    var listenerComplete: (TaskModel) -> Unit = {}
    var listenerDelete: (TaskModel) -> Unit = {}

    override fun onBindViewHolder(holder: TaskListAdapter.ViewHolder, position: Int) {
        holder.bindItem(data[position])

    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(item: TaskModel) {
            itemView.txtTaskTitle.text = item.title
            itemView.iv_more.setOnClickListener {
                showPopup(item)
            }
            itemView.setOnClickListener {
                    clickListener.onClickListener(item.id ?: -1)
            }
        }

        private fun showPopup(item: TaskModel) {
            val ivMore = itemView.iv_more
            val popupMenu = PopupMenu(ivMore.context, ivMore)
            popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_complete -> listenerComplete (item)
                    R.id.action_delete -> listenerDelete (item)
                }
                return@setOnMenuItemClickListener true
            }
            popupMenu.show()
        }
    }


}





