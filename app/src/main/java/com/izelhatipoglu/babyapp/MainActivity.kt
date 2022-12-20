package com.izelhatipoglu.babyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.izelhatipoglu.babyapp.galery.GalleryFragment
import com.izelhatipoglu.babyapp.home.HomeFragment
import com.izelhatipoglu.babyapp.landing.login.LoginFragment
import com.izelhatipoglu.babyapp.profil.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle

    lateinit var addFab: FloatingActionButton
    lateinit var outFaB: FloatingActionButton
    var fabvisible = false

    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        val navView: NavigationView = findViewById(R.id.navView)

        addFab = findViewById(R.id.floatingAdd)
        outFaB = findViewById(R.id.floatingOut)

        fabvisible = false

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        println("toggle:: " + toggle.syncState().toString())


        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {

            it.isChecked = true

            when (it.itemId) {
                R.id.galery -> replaceFragment(GalleryFragment(), it.title.toString())
                R.id.profile -> replaceFragment(ProfileFragment(), it.title.toString())
                R.id.out -> {
                    auth.signOut()
                    replaceFragment(LoginFragment(), it.title.toString())
                }
                R.id.home -> replaceFragment(HomeFragment(), it.title.toString())
            }
            true
        }


        val navController = this.findNavController(R.id.fragmentContainerView)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.loginFragment) {
                navView.visibility = View.GONE
            } else {
                navView.visibility = View.VISIBLE
            }
        }

        addFab.setOnClickListener {
            if (!fabvisible) {

                addFab.show()
                outFaB.show()

                addFab.visibility = View.VISIBLE
                outFaB.visibility = View.VISIBLE
                addFab.setImageDrawable(resources.getDrawable(R.drawable.vector_home))
                fabvisible = true

            } else {
                addFab.hide()
                outFaB.hide()

                addFab.visibility = View.GONE
                outFaB.visibility = View.GONE
                addFab.setImageDrawable(resources.getDrawable(R.drawable.vector_home))
                fabvisible = false
            }
        }

        addFab.setOnClickListener {
            Toast.makeText(this@MainActivity, "ADD clicked..", Toast.LENGTH_LONG).show()
        }

        outFaB.setOnClickListener {
            Toast.makeText(this@MainActivity, "out clicked..", Toast.LENGTH_LONG).show()
        }

    }

    private fun replaceFragment(fragment: Fragment, title: String) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment)
        fragmentTransaction.commit()
        drawerLayout.closeDrawers()
        setTitle(title)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}