package com.example.databasetesting.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomapp.R
import com.example.roomapp.adapter.ListViewAdapter
import com.example.roomapp.data.UserViewModel
import com.example.roomapp.databinding.FragmentListBinding
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding?=null
    private val binding get() = _binding!!


    //viewmodel
    private lateinit var userViewModel :UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater,container,false)
        val view = binding.root

        val adapter = ListViewAdapter()
        //recyclerview
        val rv = view.recyclerView
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(requireContext())

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.readAllData.observe(viewLifecycleOwner, Observer{ user ->
            adapter.setData(user)

        })



        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addingFragment2)
        }
        return view
    }

}