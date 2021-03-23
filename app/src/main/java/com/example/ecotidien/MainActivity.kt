 package com.example.ecotidien

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.core.text.HtmlCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navBar = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController(R.id.nav_host_frag)

        val appBarConfiguration = AppBarConfiguration(setOf(R.id.navigation_friends, R.id.navigation_tri, R.id.navigation_dashboard, R.id.navigation_profil, R.id.navigation_FAQ))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navBar.setupWithNavController(navController)
    }

    override fun onStart(){
        super.onStart()

        if (GoogleSignIn.getLastSignedInAccount(this) == null) {
            val intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            startActivity(intent)
        }
        val intent = Intent(this, HelloService::class.java)
        startService(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.layout_menu, menu)
        return true
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.logout_item -> {
                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build()

                val googleClient = GoogleSignIn.getClient(this, gso)
                googleClient.signOut().addOnCompleteListener {
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                    startActivity(intent)
                }
                true
            }
            R.id.share_item -> {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/html"
                intent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml("<p> Hello World ! </p>", HtmlCompat.FROM_HTML_MODE_LEGACY))
                startActivity(Intent.createChooser(intent, "share with "))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}