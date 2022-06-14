package com.example.databasetesting.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomapp.R
import com.example.roomapp.data.User
import com.example.roomapp.data.UserViewModel
import com.example.roomapp.databinding.FragmentAddingBinding
import kotlinx.android.synthetic.main.fragment_adding.view.*

class AddingFragment : Fragment() {

    //initialize viewmodel
    private lateinit var userViewModel:UserViewModel

    private var _binding: FragmentAddingBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddingBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        val view = binding.root


        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.addButton.setOnClickListener {

            insertDataToDatabase()
        }
        return  view
    }

    private fun insertDataToDatabase() {

        val firstName = binding.editFirstName.text.toString()
        val lastName = binding.editLastName.text.toString()
        val age = binding.editAge.text

        if(inputCheck(firstName,lastName,age)){
            val user = User(0, firstName,lastName,Integer.parseInt(age.toString()))

            // add user to database
            userViewModel.addUser(user)
            Toast.makeText(requireContext(),"successfully added!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addingFragment_to_listFragment2)
        }
        else{
            Toast.makeText(requireContext(),"fill out all added fields please.", Toast.LENGTH_SHORT).show()


        }

    }
    private fun inputCheck(firstName:String,lastName:String,age:Editable):Boolean{
        return !(TextUtils.isEmpty(firstName)&&TextUtils.isEmpty(lastName)&&age.isEmpty())
    }

}