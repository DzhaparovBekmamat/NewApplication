package com.chihuahua.pokereducation.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.template.newapplication.R
import com.template.newapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var controller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.host_fragment) as NavHostFragment
        controller = navHostFragment.navController

        binding.bottomNavigation.setupWithNavController(controller)

        controller.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.onBoardFragment) {
                binding.bottomNavigation.visibility = View.GONE
            } else {
                binding.bottomNavigation.visibility = View.VISIBLE
            }
        }

        binding.bottomNavigation.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.historyFragment -> {
                    controller.navigate(R.id.historyFragment)
                    true
                }

                R.id.casesFragment -> {
                    controller.navigate(R.id.casesFragment)
                    true
                }

                R.id.choicesFragment -> {
                    controller.navigate(R.id.choicesFragment)
                    true
                }

                R.id.mathsFragment -> {
                    controller.navigate(R.id.mathsFragment)
                    true
                }

                R.id.memoryFragment -> {
                    controller.navigate(R.id.memoryFragment)
                    true
                }

                else -> false
            }
        }
    }
}
