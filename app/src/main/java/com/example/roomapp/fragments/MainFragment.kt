package com.example.astroidnasa.fragments

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.astroidnasa.adapter.AstroidAdapter2
import com.example.astroidnasa.network.RetrofitInstance
import com.example.astroidnasa.retrofitmodels.X20150907
import com.example.roomapp.R
import com.example.roomapp.databinding.FragmentMainBinding
import com.example.roomapp.viewModel.AstroidMainViewModel

class MainFragment : Fragment() , AstroidAdapter2.OnItemClickListener{
    private lateinit var binding: FragmentMainBinding
    var r: List<X20150907>? =null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        val url = "https://apod.nasa.gov/apod/image/2001/STSCI-H-p2006a-h-1024x614.jpg"
        Glide.with(this@MainFragment).load(url).centerCrop().into(binding.imageOfTheDay)


        var v = ViewModelProvider(this).get(AstroidMainViewModel::class.java)
        Log.i("WTF", "createList: WTF tie ")

        Log.i(TAG, "Ass: ${v.createList()}")

//        lifecycleScope.launchWhenCreated {
//
//            Log.i("TAG", "onCreate1: ")
//
//            val re = RetrofitInstance.api.getAstroids2()
//            Log.i("TAG", "onCreate2:${(re.body())} ")
//            val list = re.body()?.near_earth_objects?.`2015-09-07`
//            r = list!!
//            val adapt = AstroidAdapter2(r!!, this@MainFragment)
//            binding.recyclerview.adapter = adapt
//
//            Log.i("TAGE", "onCreate: $r")
//
//
//            if (re.isSuccessful){
//                Log.i("TAG", "onCreate: ${(re.body())}")
//
//
//            }
//
//        }




        return binding.root
    }

    override fun onItemClick(position: Int) {
        Log.i(ContentValues.TAG, "onItemClick: $position")
        Log.i(ContentValues.TAG, "onItemClick: ${r?.get(position)}")
        view?.findNavController()?.navigate(R.id.action_mainFragment_to_detailFragment)




    }

}