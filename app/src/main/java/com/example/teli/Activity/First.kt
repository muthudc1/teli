package com.example.teli.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.teli.Fragement.addFragment
import com.example.teli.Fragement.chatFragment
import com.example.teli.Fragement.homeFragment
import com.example.teli.Fragement.profileFragment
import com.example.teli.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class first : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var fragmentContainer: FrameLayout
    private lateinit var toolbar : Toolbar
    private lateinit var fragmentManager: FragmentManager
    private var activeFragment: Fragment? = null

    private val homeFragment = homeFragment()
    private val addFragment = addFragment()
    private val chatFragment = chatFragment()
    private val profileFragment = profileFragment()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)



        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.navigationView)

        fragmentContainer = findViewById(R.id.fragmentContainer)
        fragmentManager = supportFragmentManager


        toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()


        supportActionBar?.setDisplayHomeAsUpEnabled(true)



        // Initialize the active fragment
        activeFragment = homeFragment
        fragmentManager.beginTransaction().add(R.id.fragmentContainer, activeFragment!!).commit()

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomnavigation)

        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.fragment1 -> {
                    switchFragment(homeFragment)
                    true
                }
                R.id.fragment2 -> {
                    switchFragment(addFragment)
                    true
                }
                R.id.fragment3 -> {
                    switchFragment(chatFragment)
                    true
                }
                R.id.fragment4 -> {
                    switchFragment(profileFragment)
                    true
                }
                else -> false
            }
        }

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.map -> {
                    // Handle item 1 click
                    val  intent = Intent (this, MapActivity::class.java )
                    startActivity(intent)
                    true
                }
                R.id.setting -> {
                    // Handle item 2 click
                    val  intent = Intent (this, settingActivity2::class.java )
                    startActivity(intent)
                    true
                }
                // Add more item handling as needed
                else -> false
            }
        }
    }
    private fun switchFragment(fragment: Fragment) {
        if (fragment != activeFragment) {
            val transaction: FragmentTransaction = fragmentManager.beginTransaction()
            if (activeFragment != null) {
                transaction.hide(activeFragment!!)
            }
            if (fragment.isAdded) {
                transaction.show(fragment)
            } else {
                transaction.add(R.id.fragmentContainer, fragment)
            }
            transaction.commit()
            activeFragment = fragment
        }
    }
    }









