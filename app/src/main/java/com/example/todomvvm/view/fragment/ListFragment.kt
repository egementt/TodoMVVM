package com.example.todomvvm.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todomvvm.R
import com.example.todomvvm.adapter.TodoListAdapter
import com.example.todomvvm.databinding.FragmentListBinding
import com.example.todomvvm.swiper.SwipeToDelete
import com.example.todomvvm.viewmodel.TodoViewModel

class ListFragment : Fragment() {

    private lateinit var binding : FragmentListBinding
    private lateinit var viewModel: TodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)


        viewModel = ViewModelProvider(this).get(TodoViewModel::class.java)
        val adapter = initRecyclerView()
        viewModel.todoList.observe(viewLifecycleOwner, { todoList -> adapter.setData(todoList)})

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        return binding.root
    }

    private fun initRecyclerView(): TodoListAdapter{
        val adapter = TodoListAdapter(viewModel)
        val recyclerView = binding.recyclerView
        val itemTouchHelper = ItemTouchHelper(SwipeToDelete(adapter))
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        itemTouchHelper.attachToRecyclerView(recyclerView)
        return adapter
    }

}