package com.example.soilanalyzer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.soilanalyzer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var databind: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(databind.root)

        navController = Navigation.findNavController(this, R.id.navHostFragment)
        NavigationUI.setupWithNavController(databind.navView, navController)
        NavigationUI.setupWithNavController(databind.bottomNavigationView, navController)
        NavigationUI.setupActionBarWithNavController(this, navController, databind.root)
    }
    override fun onSupportNavigateUp() = NavigationUI.navigateUp(navController, databind.root)
}