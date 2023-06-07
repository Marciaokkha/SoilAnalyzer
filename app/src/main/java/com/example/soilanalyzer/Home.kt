package com.example.soilanalyzer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.soilanalyzer.databinding.FragmentHomeBinding

class Home : Fragment() {

    private lateinit var databind: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        databind = FragmentHomeBinding.bind(view)

        databind.buttonToDataScreen.setOnClickListener {
            findNavController().navigate(R.id.action_HomeFragment_to_SoilAnalyzeScreenFragment)
        }
    }
}