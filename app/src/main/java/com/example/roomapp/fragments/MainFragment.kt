package com.example.astroidnasa.fragments

import android.app.Application
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.collections.ArrayList

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
        val dataSource = AstroidMadeDatabase.getInstance(application)
        //create viewmodel factory that takes in application and datasoruce
        val viewModelFactory = AstroidMainViewModelFactory(dataSource,application)

        //now that we have a factory, we can ask provider

        val astroidTrackerViewModel = ViewModelProvider(this,viewModelFactory).get(AstroidMainViewModel::class.java)

        binding.setLifecycleOwner(this)

        binding.astroidMainViewModel = astroidTrackerViewModel






        var v = ViewModelProvider(this).get(AstroidMainViewModel::class.java)


        lifecycleScope.launch {



//            v.initializeTonight()



//            val re = RetrofitInstance.api.getAstroids()
//            val s = re.body()!!
//            val d = parseAstroid(s)
//            Log.i("RETROLIST", "createList: $d")

            var data = AstroidMadeDatabase.getInstance(this@MainFragment.requireContext())
            data =  Room.databaseBuilder(this@MainFragment.requireContext(), AstroidMadeDatabase::class.java, "astroid_history").allowMainThreadQueries().build()
//
//
            var data2 = data.assDatabaseDao
//            data2.insert(AstroidMade(11,11.2,11.33,true,33.3,33.32))
//
//            Log.i(TAG, "database item: ${data2.get(11)}")














            withContext(Dispatchers.IO) {


                val dates = v.getDates()
                Log.i(TAG, "onCreateView: dates $dates")
                val george = v.getAstroid(dates[0], dates[1])

//            val re = RetrofitInstance.api.getAstroids2()
                Log.i("TAG", "onCreate22:${(george.toString())} ")
//            val list = re.body()?.near_earth_objects?.`2015-09-07`
//            r = list!!
//            val adapt = AstroidAdapter2(r!!, this@MainFragment)
//            val s = re.body()!!
                var d: MutableList<AstroidMade>? = null
                if (george==null){
                    Log.i(TAG, "it is null bro: ")
                }
                else{
                   d= parseAstroid(george)
                }

                if(d!=null){
                    withContext(Dispatchers.Main) {
                        data2.insertList(d)
                        val adapt = AstroMadeAdapter(d.toList(), this@MainFragment)
                        binding.recyclerview.adapter = adapt
                    }

                }
//                Log.i(TAG, "database item: ${data2.get(2440012)}")
//                Log.i(TAG, "database item: ${data2.get(3713989)}")
//                Log.i(TAG, "database item: ${data2.get(3726788)}")


//                Log.i("STRONG?", "onCreateView: $d")

//

            }


//            val reposiotry = AstroidRepository(dataSource)
//            val dae = reposiotry.getAstroid(formattedDateList[0],formattedDateList[1])
//            Log.i(TAG, "format of re: ${dae.toString()}")


            var liveDa = v.getLiveData()
            liveDa.observe(viewLifecycleOwner, Observer { it ->
                Log.i(TAG, "inside live data: $it")

            })
            Log.i(TAG, "database item: ${liveDa.value}")



            //getting list of astroids for latest dates!
//            val re2 = RetrofitInstance.api.getAstroids2(formattedDateList[0],formattedDateList[1])
//            Log.i("TAG", "the king has returned:${(re.body())} ")
//            r = list!!
//            val adapt = AstroidAdapter2(r!!, this@MainFragment)
//            val s = re.body()!!


        }

        lifecycleScope.launchWhenCreated {

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