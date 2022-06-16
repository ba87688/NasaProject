package com.example.roomapp.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomapp.R
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

setHasOptionsMenu(true)
        binding.updateAddButton.setOnClickListener {
            updateItem()
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


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteUser()
        }

        return super.onOptionsItemSelected(item)
    }
    private fun deleteUser(){

        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_->

            userViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(),"You deleted ${args.currentUser.firstName} from database",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No"){_,_->

        }
        builder.setTitle("Delete ${args.currentUser.firstName}?")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.firstName}?")
        builder.create().show()
    }
}