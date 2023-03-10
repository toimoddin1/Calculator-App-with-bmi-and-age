package com.invincible.mycalculator

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class app_version : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var facebook: ImageView
    private lateinit var instagram: ImageView
    private lateinit var linkedin: ImageView

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        if(menu != null){
            val searchItem: MenuItem = menu.findItem(R.id.nav_home)
            if(searchItem.itemId == R.id.nav_home){
                searchItem?.setVisible(false)
//                Toast.makeText(this@MainActivity,"This is Test",Toast.LENGTH_SHORT).show()
            }
        }

        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Navigation View Open and Close function
        if(toggle.onOptionsItemSelected(item)){
            return true
        }


        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_version)
        drawerLayout = findViewById(R.id.draw_app_version)
        navigationView = findViewById(R.id.navigation_main)
        facebook = findViewById(R.id.fb)
        instagram = findViewById(R.id.insta)
        linkedin = findViewById(R.id.linkedin)

        facebook.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://www.facebook.com/sohel.saikh.52")
            startActivity(openURL)
            Toast.makeText(this,"Opening Facebook", Toast.LENGTH_LONG).show()
        }
        instagram.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://www.instagram.com/sohel16756/")
            startActivity(openURL)
            Toast.makeText(this,"Opening Instagram", Toast.LENGTH_LONG).show()
        }

        linkedin.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://www.linkedin.com/in/toimoddin-saikh-a0990a1a5/")
            startActivity(openURL)
            Toast.makeText(this,"Opening Linkedin", Toast.LENGTH_LONG).show()
        }

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_bmi -> {
                    setVisible(false)
                    val intent = Intent(this@app_version, BMI::class.java)
                    startActivity(intent)
                    Toast.makeText(this,"BMI Calculator", Toast.LENGTH_SHORT).show()
                }
            }
            when(it.itemId){
                R.id.nav_home->{
                    setVisible(false)
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this,"Home", Toast.LENGTH_SHORT).show()

                }
            }
            when(it.itemId){
                R.id.nav_age->{
                    setVisible(false)
                    val intent = Intent(this,Agecal::class.java)
                    startActivity(intent)
                    Toast.makeText(this,"Age Calculator", Toast.LENGTH_SHORT).show()

                }
            }
            when(it.itemId){
                R.id.privacy->{
                    setVisible(false)
                    val openURL = Intent(Intent.ACTION_VIEW)
                    openURL.data = Uri.parse("https://pages.flycricket.io/my-calculator-3/privacy.html")
                    startActivity(openURL)
                    Toast.makeText(this,"Privacy Policy", Toast.LENGTH_SHORT).show()
                }
            }
            when(it.itemId){
                R.id.terms_cond->{
                    setVisible(false)
                    val openURL = Intent(Intent.ACTION_VIEW)
                    openURL.data = Uri.parse("https://pages.flycricket.io/my-calculator-3/terms.html")
                    startActivity(openURL)
                    Toast.makeText(this,"Terms & Condition", Toast.LENGTH_SHORT).show()

                }
            }
            when(it.itemId){
                R.id.other_app->{
                    setVisible(false)
                    val openURL = Intent(Intent.ACTION_VIEW)
                    openURL.data = Uri.parse("https://play.google.com/store/apps/developer?id=INVINCIBLE+TDN&hl=en_IN&gl=US")
                    startActivity(openURL)
                    Toast.makeText(this,"Opening Play Store", Toast.LENGTH_SHORT).show()

                }
            }
            when(it.itemId){
                R.id.version->{
                    setVisible(false)
                    val intent = Intent(this@app_version, app_version::class.java)
                    startActivity(intent)
                    Toast.makeText(this,"App Version", Toast.LENGTH_SHORT).show()

                }
            }
            true
        }

    }
}