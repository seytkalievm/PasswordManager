package com.seytkalievm.passwordmanager.presentation.session

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.seytkalievm.passwordmanager.R
import com.seytkalievm.passwordmanager.databinding.ActivitySessionBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SessionActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySessionBinding

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivitySessionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.session_activity_nav_host_fragment) as NavHostFragment

        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.passwordsFragment, R.id.creditCardsFragment,
            R.id.personalInfoFragment, R.id.settingsFragment
        ))


        binding.sessionActivityToolbar.setupWithNavController(navController, appBarConfiguration)
        binding.bottomNavigationMenu.setupWithNavController(navController)

        val mainScreens = mapOf(
            Pair(R.id.passwordsFragment, true),
            Pair(R.id.creditCardsFragment, true),
            Pair(R.id.personalInfoFragment, true),
            Pair(R.id.settingsFragment, true)
        )

        navController.addOnDestinationChangedListener{_, destination, _ ->
            val height = binding.bottomNavigationMenu.height.toFloat()

            when (destination.id){
                in mainScreens -> {
                    binding.bottomNavigationMenu.animate().translationY(0f).duration = 300
                }
                else -> {
                    binding.bottomNavigationMenu.animate().translationY(height).duration = 300

                }

            }

        }
    }

}