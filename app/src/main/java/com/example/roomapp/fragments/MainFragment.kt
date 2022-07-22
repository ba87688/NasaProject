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
import com.example.roomapp.adapter.AstroidsClassAdapter
import com.example.roomapp.adapters.AdapterClassic
import com.example.roomapp.api.*
import com.example.roomapp.database.AstroidMadeDatabase
import com.example.roomapp.database.AstroidRoomDatabase
import com.example.roomapp.database.AstroidsDatabase
import com.example.roomapp.databinding.FragmentMainBinding
import com.example.roomapp.model.Ass
import com.example.roomapp.repository.AstroidRepository
import com.example.roomapp.repository.AstroidsRepository
import com.example.roomapp.viewModel.AstroidMainViewModel
import com.example.roomapp.viewModel.AstroidMainViewModelFactory
import com.example.roomapp.viewmodels.AstroidsViewModel
import com.example.roomapp.viewmodels.AstroidsViewModelFactory
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.log

class MainFragment : Fragment(), AstroidAdapter2.OnItemClickListener,
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
            ViewModelProvider(this, viewModelFactory).get(AstroidsViewModel::class.java)

        binding.setLifecycleOwner(this)

        binding.astroidsViewModel = astroidTrackerViewModel
//        binding.astroidMainViewModel = astroidTrackerViewModel


        var v = ViewModelProvider(this).get(AstroidsViewModel::class.java)
//        var v = ViewModelProvider(this).get(AstroidMainViewModel::class.java)
        var url: String = ""
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                url = v.getUrl()
            }
        }

        v.url.observe(viewLifecycleOwner, Observer { it ->
            // if count time finished it set the value
            Log.i(TAG, "obs class url: $it")


//            Glide.with(this@MainFragment).load(it).centerCrop().into(binding.imageOfTheDay)

            if(it==""){}
            else{
            Picasso.get()
                .load(it)
                .fit()
                .placeholder(R.drawable.ic_baseline_add_24)
                .into(binding.imageOfTheDay)}

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


        var adapter: AdapterClassic
//        var adapter: AstroMadeAdapter
        binding.apply {

//
//            v.restaurants.observe(lifecycleOwner!!) { result ->
//                Log.i(TAG, "Checking live data: ${result.data}")
//                list = result.data!!
//
//                adapter = AstroMadeAdapter(result.data!!, this@MainFragment)
//                recyclerview.adapter = adapter
//            }
            //add

            v.restaurants.observe(lifecycleOwner!!) { result ->
                Log.i(TAG, "Checking live data: ${result.data}")
                list2 = result.data!!

                adapter = AdapterClassic(list2)
                recyclerview.adapter = adapter
            }


        }



lifecycleScope.launch {
        withContext(Dispatchers.IO){

            val formattedDateList = ArrayList<String>()

            val calendar = Calendar.getInstance()
            for (i in 0..Constants.DEFAULT_END_DATE_DAYS) {
                val currentTime = calendar.time
                val dateFormat = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
                formattedDateList.add(dateFormat.format(currentTime))
                calendar.add(Calendar.DAY_OF_YEAR, 1)
            }

            Log.i(ContentValues.TAG, "onCreateView1: $formattedDateList")
            Log.i(ContentValues.TAG, "onCreateView1: ${formattedDateList.first()}")
            Log.i(ContentValues.TAG, "onCreateView1: ${formattedDateList.last()}")

            val red= RetrofitInstance.api1.getAstroids3(formattedDateList.first(),formattedDateList.last())
            val red1= RetrofitInstance.api1.getAstroids2()
            Log.i(ContentValues.TAG, "onCreateView1: fragment 2 ${red}")
            val you= Gson().toJson(red)
            val you1 = JSONObject(you)



            val pased = parseAsteroidsJsonResult(you1)

            Log.i(ContentValues.TAG, "onCreateView1: fragment 2 ${pased.toString()}")






            val database =  Room.databaseBuilder(requireContext(), AstroidsDatabase::class.java, "astroids_data").allowMainThreadQueries().build()

            //adding
            val rep = AstroidsRepository(database)

            val item = Astroid(111,"RED","ree",44.44,33.3,44.44,55.55,true)

            rep.insert(item)
            val crazy = database.assDatabaseDao.get(111)

            Log.i(TAG, "getRealParsedResponse: ${crazy.id} ")
        }


    }






        return binding.root
    }

    override fun onItemClick(position: Int) {
//        Log.i(ContentValues.TAG, "onItemClick: $position")
//        Log.i(ContentValues.TAG, "onItemClick: ${r?.get(position)}")

        var str = "edit the string $position"

        var astrpod = list2.get(position)

//        val action = MainFragmentDirections.actionMainFragmentToDetailFragment(astrpod)
//        view?.findNavController()?.navigate(R.id.action_mainFragment_to_detailFragment)
//        view?.findNavController()?.navigate(action)


    }

}