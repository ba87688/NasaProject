package com.example.roomapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.astroidnasa.retrofitmodels.X20150907
import com.example.roomapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var r: List<X20150907>? =null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)




//        lifecycleScope.launchWhenCreated {
//
//            Log.i("TAG", "onCreate1: ")
//
//            val re = RetrofitInstance.api.getAstroids2()
//            Log.i("TAG", "onCreate2:${(re.body())} ")
//            val list = re.body()?.near_earth_objects?.`2015-09-07`
//            r = list!!
//            val adapt = AstroidAdapter2(r!!, this@MainActivity)
//            binding.recyclerview.adapter = adapt
//
//            Log.i("TAGE", "onCreate: $r")
////            Log.i("TAG", "onCreate2:${(re.body()?.near_earth_objects.`2015-09-07`[0].absolute_magnitude_h)} ")
//
//
//            if (re.isSuccessful){
//                Log.i("TAG", "onCreate: ${(re.body())}")
//
//
//            }
//
//        }

        setContentView(binding.root)
    }

//    override fun onItemClick(position: Int) {
//        Log.i(TAG, "onItemClick: $position")
//        Log.i(TAG, "onItemClick: ${r?.get(position)}")
//
//    }
}