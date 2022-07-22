package com.example.astroidnasa.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.example.roomapp.R
import com.example.roomapp.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {
   private lateinit var binding: FragmentDetailBinding

   val args: DetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail,container,false)


        val astroid = args.re

        if (args.re.isPotentiallyHazardous){
            binding.ivHazordousOrNo.setImageResource(R.drawable.hazardous)
        }else{
            binding.ivHazordousOrNo.setImageResource(R.drawable.not_hazardous)

        }
        binding.tvCloseApproachDate.text = args.re.closeApproachDate.toString()
        binding.tvAbsoluteMagnitude.text = args.re.absoluteMagnitude.toString()
        binding.rvEstimatedDiameter.text = args.re.estimatedDiameter.toString()
        binding.tvRelativeVelocity.text = args.re.relativeVelocity.toString()
        binding.tvDistanceFromEarth.text = args.re.distanceFromEarth.toString()




        return binding.root
    }


}