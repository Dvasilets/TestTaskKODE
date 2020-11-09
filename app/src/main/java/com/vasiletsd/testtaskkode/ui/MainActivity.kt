package com.vasiletsd.testtaskkode.ui

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.text.HtmlCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.vasiletsd.testtaskkode.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val toolbarTitle : String ="Hi, " + "<b>" + getString(R.string.username) + "</b>" + "!"
        supportActionBar?.title = HtmlCompat.fromHtml(toolbarTitle, HtmlCompat.FROM_HTML_MODE_LEGACY)

        navView.setupWithNavController(navController)
    }
}