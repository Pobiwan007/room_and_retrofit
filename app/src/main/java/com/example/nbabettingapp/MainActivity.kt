package com.example.nbabettingapp

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.browser.customtabs.CustomTabsIntent
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_view) as NavHostFragment

        NavigationUI.setupWithNavController(
            findViewById<BottomNavigationView>(R.id.bottom_nav_view),
            navHostFragment.navController)
    }

    fun privacyClick(item: MenuItem) {
        val builder = CustomTabsIntent.Builder().build()
        builder.launchUrl(this, Uri.parse("https://google.ru"))
    }
}