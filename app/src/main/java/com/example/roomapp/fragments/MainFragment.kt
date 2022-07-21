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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.astroidnasa.adapter.AstroidAdapter2
import com.example.astroidnasa.network.RetrofitInstance
import com.example.astroidnasa.retrofitmodels.AstroidApiModel
import com.example.astroidnasa.retrofitmodels.X20150907
import com.example.roomapp.R
import com.example.roomapp.adapter.AstroMadeAdapter
import com.example.roomapp.api.AstroidMade
import com.example.roomapp.api.Constants
import com.example.roomapp.api.parseAstroid
import com.example.roomapp.database.AstroidMadeDatabase
import com.example.roomapp.database.AstroidRoomDatabase
import com.example.roomapp.databinding.FragmentMainBinding
import com.example.roomapp.model.Ass
import com.example.roomapp.repository.AstroidRepository
import com.example.roomapp.viewModel.AstroidMainViewModel
import com.example.roomapp.viewModel.AstroidMainViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.log

class MainFragment : Fragment(), AstroidAdapter2.OnItemClickListener,
    AstroMadeAdapter.OnItemClickListener {
    private lateinit var binding: FragmentMainBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)


//       viewmodel reference application
        val application = requireNotNull(this.activity).application
        //reference datasource
        val dataSource = AstroidMadeDatabase.getInstance(application)
        //create viewmodel factory that takes in application and datasoruce
        val viewModelFactory = AstroidMainViewModelFactory(dataSource, application)

        //now that we have a factory, we can ask provider

        val astroidTrackerViewModel =
            ViewModelProvider(this, viewModelFactory).get(AstroidMainViewModel::class.java)

        binding.setLifecycleOwner(this)

        binding.astroidMainViewModel = astroidTrackerViewModel


        var v = ViewModelProvider(this).get(AstroidMainViewModel::class.java)
        var url: String = ""
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                url = v.getUrl()
            }
        }

        v.url.observe(viewLifecycleOwner, Observer { it ->
            // if count time finished it set the value
            Log.i(TAG, "onCreateView: $it")


//            Glide.with(this@MainFragment).load(it).centerCrop().into(binding.imageOfTheDay)

            Picasso.get()
                .load(it)
                .fit()
                .placeholder(R.drawable.ic_baseline_add_24)
                .into(binding.imageOfTheDay)

        }
        )

        v.title.observe(viewLifecycleOwner, Observer { it ->
            Log.i(TAG, "title: $it")

            binding.myImageViewText.text = it.toString()
            binding.imageOfTheDay.contentDescription = it.toString()
            }


        )
        v.explination.observe(viewLifecycleOwner, Observer { it ->
            Log.i(TAG, "explain: $it")



        }
        )


        var adapter: AstroMadeAdapter
        binding.apply {

            v.restaurants.observe(lifecycleOwner!!) { result ->
                Log.i(TAG, "Checking live data: ${result.data}")

                adapter = AstroMadeAdapter(result.data!!, this@MainFragment)
                recyclerview.adapter = adapter
            }
        }

        lifecycleScope.launch {

//            val d = v.getAstroid("2015-09-07","2015-09-08")
//            val parsedData = v.parseData(d!!)
//            val adapter = AstroMadeAdapter(parsedData,this@MainFragment)
//
//            withContext(Dispatchers.Main){
//                binding.recyclerview.adapter = adapter
//            }


//            var data = AstroidMadeDatabase.getInstance(this@MainFragment.requireContext())
//            data =  Room.databaseBuilder(this@MainFragment.requireContext(), AstroidMadeDatabase::class.java, "astroid_history").allowMainThreadQueries().build()
//
//
//
//            var liveDa = v.getLiveData()
//            liveDa.observe(viewLifecycleOwner, Observer { it ->
//                Log.i(TAG, "inside live data: $it")
//
//            })
//            Log.i(TAG, "database item: ${liveDa.value}")
//


        }





        return binding.root
    }

    override fun onItemClick(position: Int) {
//        Log.i(ContentValues.TAG, "onItemClick: $position")
//        Log.i(ContentValues.TAG, "onItemClick: ${r?.get(position)}")
        view?.findNavController()?.navigate(R.id.action_mainFragment_to_detailFragment)


    }

}