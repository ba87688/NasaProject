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


        binding.saftey.text = args.re.name




        return binding.root
    }


}