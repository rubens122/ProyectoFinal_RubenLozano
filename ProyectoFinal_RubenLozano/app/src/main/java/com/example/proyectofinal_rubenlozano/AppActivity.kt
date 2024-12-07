package com.example.proyectofinal_rubenlozano

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.proyectofinal_rubenlozano.databinding.ActivityAppBinding
import com.example.proyectofinal_rubenlozano.fragments.EventsFragment
import com.example.proyectofinal_rubenlozano.fragments.SearchFragment
import com.example.proyectofinal_rubenlozano.fragments.FavoritesFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AppActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAppBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAppBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Usar ViewBinding para acceder a la vista principal
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setListeners()

        auth = Firebase.auth
        setSupportActionBar(findViewById(R.id.toolbar))
    }

    private fun setListeners() {
        binding.btnSearch.setOnClickListener { replaceFragment(SearchFragment()) }
        binding.btnFavorites.setOnClickListener { replaceFragment(FavoritesFragment()) }
        binding.btnEvents.setOnClickListener { replaceFragment(EventsFragment()) }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item_salir->{
                finishAffinity()
            }
            R.id.item_cerrar->{
                auth.signOut()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
