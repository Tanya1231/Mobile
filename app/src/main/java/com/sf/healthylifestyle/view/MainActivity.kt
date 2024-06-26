package com.sf.healthylifestyle.view

import android.content.pm.ActivityInfo
import android.content.res.ColorStateList
import android.os.Bundle
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.switchmaterial.SwitchMaterial
import com.sf.healthylifestyle.R
import com.sf.healthylifestyle.databinding.ActivityMainBinding
import com.sf.healthylifestyle.utils.uiextensions.hide
import com.sf.healthylifestyle.utils.uiextensions.show
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val navController by lazy {
        NavHostFragment.findNavController(supportFragmentManager.findFragmentById(R.id.fragment_placeholder) as NavHostFragment)
    }

    private val fragmentsWithoutToolbars = listOf(
        R.id.authFragment,
        R.id.registerFragment,
        R.id.onboardingOneFragment,
        R.id.onboardingTwoFragment,
        R.id.onboardingThreeFragment,
        R.id.onboardingFourFragment,
        R.id.descriptionDishFragment
    )

    private val fragmentsWithoutSwitch = listOf(
        R.id.authFragment,
        R.id.registerFragment,
        R.id.onboardingOneFragment,
        R.id.onboardingTwoFragment,
        R.id.onboardingThreeFragment,
        R.id.onboardingFourFragment,
        R.id.mydishFragment,
        R.id.basketFragment,
        R.id.profileFragment,
        R.id.descriptionDishFragment
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setupWithNavController(navController)

        initMenu()
        initShowOrHideMainBottomBar()
        initShowOrHideSwitch()

        val btn = findViewById<Switch>(R.id.switchOne)

        btn.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_YES
            } else {
                delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO
            }
        }

//        binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.img_dish2))
//        binding.fab.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.img_dish))
//        binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_dish3))
//        binding.fab.setImageResource(R.drawable.ic_dish3)
    }

        private fun initMenu() {
            with(binding) {
                fab.setOnClickListener {
                    navController.navigate(R.id.mydishFragment)
                }
                bottomNavigation.setOnItemSelectedListener {
                    when (it.itemId) {
                        R.id.homeFragment -> {
                            navController.navigate(R.id.homeFragment)
                            true
                        }

                        R.id.bookFragment -> {
                            navController.navigate(R.id.bookFragment)
                            true
                        }

                        R.id.mydishFragment -> {
                            navController.navigate(R.id.mydishFragment)
                            true
                        }

                        R.id.catalogueFragment -> {
                            navController.navigate(R.id.catalogueFragment)
                            true
                        }

                        R.id.profileFragment -> {
                            navController.navigate(R.id.profileFragment)
                            true
                        }

                        R.id.basketFragment -> {
                            navController.navigate(R.id.basketFragment)
                            true
                        }

                        else -> false
                    }
                }
            }

        }

        private fun initShowOrHideMainBottomBar() {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    navController.addOnDestinationChangedListener { _, destination, _ ->
                        with(binding) {
                            if (fragmentsWithoutToolbars.contains(destination.id)) {
                                bottomNavigation.hide()
                                fab.hide()
                                fabUpper.hide()
                            } else {
                                bottomNavigation.show()
                                fab.show()
                                fabUpper.show()
                            }
                        }
                    }
                }

            }
        }

    private fun initShowOrHideSwitch() {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    navController.addOnDestinationChangedListener { _, destination, _ ->
                        with(binding) {
                            if (fragmentsWithoutSwitch.contains(destination.id)) {
                                switchOne.hide()
                            } else {
                                switchOne.show()
                            }
                        }
                    }
                }

            }
        }
    }