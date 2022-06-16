package com.example.databasetesting.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomapp.R
import com.example.roomapp.adapter.ListViewAdapter
import com.example.roomapp.data.UserViewModel
import com.example.roomapp.databinding.FragmentListBinding
import kotlinx.android.synthetic.main.fragment_list.view.*
import android.app.Activity
import android.app.AlertDialog
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat


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
setHasOptionsMenu(true)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteAllUsers()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun deleteAllUsers(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_->
            userViewModel.deleteAllUsers()
            Toast.makeText(requireContext(),"You deleted all users",Toast.LENGTH_SHORT).show()

        }
        builder.setNegativeButton("No"){
            _,_->
        }
        builder.setTitle("delete all?")
        builder.setMessage("are you sure?")
        builder.create().show()
    }


}