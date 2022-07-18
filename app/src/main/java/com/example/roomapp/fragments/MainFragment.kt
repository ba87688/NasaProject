package com.example.astroidnasa.fragments

import android.app.Application
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
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.astroidnasa.adapter.AstroidAdapter2
import com.example.astroidnasa.network.RetrofitInstance
import com.example.astroidnasa.retrofitmodels.X20150907
import com.example.roomapp.R
import com.example.roomapp.adapter.AstroMadeAdapter
import com.example.roomapp.api.parseAstroid
import com.example.roomapp.database.AstroidRoomDatabase
import com.example.roomapp.databinding.FragmentMainBinding
import com.example.roomapp.model.Ass
import com.example.roomapp.viewModel.AstroidMainViewModel
import com.example.roomapp.viewModel.AstroidMainViewModelFactory
import kotlinx.coroutines.launch

class MainFragment : Fragment() , AstroidAdapter2.OnItemClickListener,AstroMadeAdapter.OnItemClickListener{
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


//       viewmodel reference application
        val application = requireNotNull(this.activity).application
        //reference datasource
        val dataSource = AstroidRoomDatabase.getInstance(application).assDatabaseDao
        //create viewmodel factory that takes in application and datasoruce
        val viewModelFactory = AstroidMainViewModelFactory(dataSource,application)

        //now that we have a factory, we can ask provider

        val astroidTrackerViewModel = ViewModelProvider(this,viewModelFactory).get(AstroidMainViewModel::class.java)

        binding.setLifecycleOwner(this)

        binding.astroidMainViewModel = astroidTrackerViewModel






        var v = ViewModelProvider(this).get(AstroidMainViewModel::class.java)
        Log.i("WTF", "createList: WTF tie ")

        Log.i(TAG, "Ass: ${v.createList()}")

        lifecycleScope.launch {


            v.initializeTonight()

            val re = RetrofitInstance.api.getAstroids()
            val s = re.body()!!
            val d = parseAstroid(s)
            Log.i("RETROLIST", "createList: $d")

//            var data = AstroidRoomDatabase.getInstance(this@MainFragment.requireContext())
//            data =  Room.databaseBuilder(this@MainFragment.requireContext(), AstroidRoomDatabase::class.java, "MyDatabase").allowMainThreadQueries().build()
//
//
//            var data2 = data.assDatabaseDao
//            data2.insert(Ass(11,1,"name"))
//
//            Log.i(TAG, "onCreatedView: ${data2.get(11)}")


        }

        lifecycleScope.launchWhenCreated {

            Log.i("TAG", "onCreate1: ")

            val re = RetrofitInstance.api.getAstroids2()
            Log.i("TAG", "onCreate2:${(re.body())} ")
            val list = re.body()?.near_earth_objects?.`2015-09-07`
            r = list!!
//            val adapt = AstroidAdapter2(r!!, this@MainFragment)
            val s = re.body()!!
            val d = parseAstroid(s)
            val adapt = AstroMadeAdapter(d,this@MainFragment)
            binding.recyclerview.adapter = adapt
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
        }




        return binding.root
    }

    override fun onItemClick(position: Int) {
        Log.i(ContentValues.TAG, "onItemClick: $position")
        Log.i(ContentValues.TAG, "onItemClick: ${r?.get(position)}")
        view?.findNavController()?.navigate(R.id.action_mainFragment_to_detailFragment)




    }

}