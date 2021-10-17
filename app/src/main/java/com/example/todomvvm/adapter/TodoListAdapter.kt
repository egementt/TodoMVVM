package com.example.todomvvm.adapter

import android.content.res.Resources
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todomvvm.R
import com.example.todomvvm.databinding.ItemRowBinding
import com.example.todomvvm.model.TodoModel
import com.example.todomvvm.viewmodel.TodoViewModel

class TodoListAdapter(private val viewModel: TodoViewModel) : RecyclerView.Adapter<TodoListAdapter.MyViewHolder>() {

    private var todoList = emptyList<TodoModel>()
    private lateinit var view: View


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemRowBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        view = parent.rootView
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = todoList[position]
        with(holder) {
            binding.twTodoTitle.text = currentItem.title
            binding.twTodoContent.text = currentItem.content
            setPriorityColor(binding, currentItem)
            Log.d("TODOMODEL", "ID: ${currentItem.id}, title: ${currentItem.title}")
        }
    }



    override fun getItemCount(): Int {
        return todoList.size
    }

    fun setData(todoModel: List<TodoModel>) {
        this.todoList = todoModel
        notifyDataSetChanged()
    }


    private fun setPriorityColor(binding: ItemRowBinding, todoModel: TodoModel) {
        when (todoModel.priorityLevel) {
            1 -> binding.todoPriority.setCardBackgroundColor(
                view.resources.getColor(
                    R.color.low_priority,
                    Resources.getSystem().newTheme()
                )
            )
            2 -> binding.todoPriority.setCardBackgroundColor(
                view.resources.getColor(
                    R.color.middle_priority,
                    Resources.getSystem().newTheme()
                )
            )
            3 -> binding.todoPriority.setCardBackgroundColor(
                view.resources.getColor(
                    R.color.high_priority,
                    Resources.getSystem().newTheme()
                )
            )
        }
    }

    fun deleteTodo(pos: Int) {
        val model = todoList[pos]
        viewModel.deleteTodo(model)
        notifyItemRemoved(pos)
    }
}