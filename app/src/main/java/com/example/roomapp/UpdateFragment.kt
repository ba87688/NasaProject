package com.example.roomapp

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomapp.data.User
import com.example.roomapp.data.UserViewModel
import com.example.roomapp.databinding.FragmentUpdateBinding

class UpdateFragment:Fragment(R.layout.fragment_update) {

    private var _binding:FragmentUpdateBinding?=null
    private val binding get() = _binding!!

    private lateinit var userViewModel:UserViewModel

    private val args by navArgs<UpdateFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateBinding.inflate(layoutInflater,container,false)
        val view = binding.root

        userViewModel= ViewModelProvider(this).get(UserViewModel::class.java)

        binding.updateFirstName.setText(args.currentUser.firstName)
        binding.updateLastName.setText(args.currentUser.lastName)
        binding.updateAge.setText(args.currentUser.age.toString())


        binding.addButton.setOnClickListener {

        }


        return view

    }
    private fun updateItem(){
        val firstName = binding.updateFirstName.text.toString()
        val lastName = binding.updateLastName.text.toString()
        val age = Integer.parseInt(binding.updateAge.text.toString())

        if(inputCheck(firstName,lastName,binding.updateAge.text)){

            val updateUser = User(args.currentUser.id,firstName,lastName,age)

            userViewModel.updateUser(updateUser)

            Toast.makeText(requireContext(),"updated success", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"updated UNsuccess", Toast.LENGTH_SHORT).show()

        }
    }

    private fun inputCheck(firstName:String,lastName:String,age: Editable):Boolean{
        return !(TextUtils.isEmpty(firstName)&& TextUtils.isEmpty(lastName)&&age.isEmpty())
    }
}