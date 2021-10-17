package com.example.todomvvm.view.fragment

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todomvvm.R
import com.example.todomvvm.databinding.FragmentAddBinding
import com.example.todomvvm.model.TodoModel
import com.example.todomvvm.viewmodel.TodoViewModel


class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding
    private lateinit var todoViewModel: TodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBinding.inflate(inflater, container, false)
        todoViewModel = ViewModelProvider(this).get(TodoViewModel::class.java)

        binding.btnAdd.setOnClickListener {
            addTodoToDatabase()
        }

        return binding.root
    }

    private fun addTodoToDatabase() {
        val title = binding.etTitle.text.toString()
        val content = binding.etContent.text.toString()
        val selectedRadioButtonId: Int = binding.radioGroup.checkedRadioButtonId
        val priorityLevel = setPriority(selectedRadioButtonId)

        if(checkInput(title, content))
        {
            val todo = TodoModel(id = 0,title = title, content = content, priorityLevel = priorityLevel)
            todoViewModel.addTodo(todo)
            Toast.makeText(requireContext(), "Todo added successfully.", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG)
                .show()
        }

    }

    private fun setPriority(radioButtonId: Int): Int {
        val selectedRadioButton = requireView().findViewById<RadioButton>(radioButtonId)
        when (selectedRadioButton.id) {
            R.id.rb_low -> return 1
            R.id.rb_mid -> return 2
            R.id.rb_high -> return 3
        }
        return 1
    }

    private fun checkInput(title: String, content: String): Boolean{
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(content))
    }

}