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
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import com.example.roomapp.R
import com.example.roomapp.adapters.AstroMadeAdapter
import com.example.roomapp.adapters.AdapterClassic
import com.example.roomapp.api.*
import com.example.roomapp.database.AstroidsDatabase
import com.example.roomapp.databinding.FragmentMainBinding
import com.example.roomapp.viewmodels.MainAstroidsViewModel
import com.example.roomapp.viewmodels.AstroidsViewModelFactory
import com.squareup.picasso.Picasso

import java.util.*

class MainFragment : Fragment(), AdapterClassic.OnItemClickListener,
    AstroMadeAdapter.OnItemClickListener {

    var list2:List<Astroid> = mutableListOf()
//    var list:List<AstroidMade> = mutableListOf()
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
        val dataSource = AstroidsDatabase.getInstance(application)
//        val dataSource = AstroidMadeDatabase.getInstance(application)
        //create viewmodel factory that takes in application and datasoruce
        val viewModelFactory = AstroidsViewModelFactory(dataSource, application)
//        val viewModelFactory = AstroidMainViewModelFactory(dataSource, application)

        //now that we have a factory, we can ask provider

//        val astroidTrackerViewModel =
//            ViewModelProvider(this, viewModelFactory).get(AstroidMainViewModel::class.java)
        val astroidTrackerViewModel =
            ViewModelProvider(this, viewModelFactory).get(MainAstroidsViewModel::class.java)

        binding.setLifecycleOwner(this)

        binding.astroidsViewModel = astroidTrackerViewModel


        var v = ViewModelProvider(this).get(MainAstroidsViewModel::class.java)





        var adapter: AdapterClassic
        binding.apply {


            v.restaurants.observe(lifecycleOwner!!) { result ->
                Log.i(TAG, "Checking live data: ${result.data}")
                list2 = result.data!!

                adapter = AdapterClassic(result.data!!,this@MainFragment)
                recyclerview.adapter = adapter
            }


            v.dailyImage.observe(lifecycleOwner!!) { result ->
                val image = result.data?.url
                val title = result.data?.title
                if (image==""){

                }
                else{
                    Picasso.get()
                        .load(image)
                        .fit()
                        .placeholder(R.drawable.ic_baseline_add_24)
                        .into(imageOfTheDay)

                }
                myImageViewText.text = title.toString()
                imageOfTheDay.contentDescription = title.toString()
            }

            }






        return binding.root
    }

    override fun onItemClick(position: Int) {
        Log.i(ContentValues.TAG, "onItemClick: $position")
//        Log.i(ContentValues.TAG, "onItemClick: ${r?.get(position)}")

        var str = "edit the string $position"

        var astrpod = list2.get(position)
        val action = MainFragmentDirections.actionMainFragmentToDetailFragment(astrpod)
        view?.findNavController()?.navigate(action)


    }

}